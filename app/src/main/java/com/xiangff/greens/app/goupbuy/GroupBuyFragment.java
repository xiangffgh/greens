package com.xiangff.greens.app.goupbuy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wang.avi.AVLoadingIndicatorView;
import com.xiangff.greens.app.MainActivity;
import com.xiangff.greens.app.R;
import com.xiangff.greens.app.data.groupbuy.GBModel;
import com.xiangff.greens.app.goupbuy.adapter.GroupBuyAdatper;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GroupBuyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GroupBuyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupBuyFragment extends Fragment implements GroupBuyContract.View {
    private static final String TAG = "GroupBuyFragment";
    private View rootView;//缓存 Fragment view

    private GroupBuyContract.Presenter presenter;

    private boolean loading=false;

    /*=======视图组件=======*/
    private AVLoadingIndicatorView avi;
    private SwipeRefreshLayout srl;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private List<GBModel> gbModels;
    private GroupBuyAdatper gbAdatper;
    @Override
    public void setPresenter(GroupBuyContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private OnFragmentInteractionListener mListener;


    public GroupBuyFragment() {

    }


    public static GroupBuyFragment newInstance() {
        GroupBuyFragment fragment = new GroupBuyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {

            Log.i(TAG, "onCreateView");
            // Inflate the layout for this fragment
            rootView = inflater.inflate(R.layout.fragment_group_buy, container, false);
            avi= (AVLoadingIndicatorView) rootView.findViewById(R.id.avi_group_buy);
            srl= (SwipeRefreshLayout) rootView.findViewById(R.id.srl_group_buy);
            recyclerView= (RecyclerView) rootView.findViewById(R.id.rv_group_buy);
            linearLayoutManager=new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);
            this.gbModels=new ArrayList<>();
            this.gbAdatper=new GroupBuyAdatper(getActivity(),this.gbModels);
            recyclerView.setAdapter(this.gbAdatper);
            initListener();
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    /**
     * 初始化监听器
     */
    private void initListener() {
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*上拉刷新数据*/
                presenter.initDatas();
            }
        });
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!srl.isRefreshing()){
                    int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem+1==gbAdatper.getItemCount()){
                        //调用Adapter里的changeMoreStatus方法来改变加载脚View的显示状态为：正在加载...
                        gbAdatper.changeMoreStatus(GroupBuyAdatper.ISLOADING);
                        /*加载更多数据*/
                        presenter.loadMoreDatas();
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        /*
         * 开始请求数据
         */
        if (this.presenter != null)
            this.presenter.start();
        else Log.e(TAG,"GroupBuyFragment view`s Prenster should not be null !");
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        Log.i(TAG,"GroupBuyFragment-onAttach");
        if (context instanceof MainActivity){
            ((MainActivity)context).setGroupBuyView(this);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showLoadingIndicator() {
        avi.show();
    }

    @Override
    public void hideLoadingIndicator() {
        avi.hide();
    }

    @Override
    public void setDatas(List<GBModel> gbModels) {
        if (gbModels!=null){
            this.gbModels.clear();
            this.gbModels.addAll(gbModels);
            this.gbAdatper.notifyDataSetChanged();
            this.srl.setRefreshing(false);
        }
    }

    @Override
    public void addDatas(List<GBModel> gbModels) {
        if (gbModels!=null){
            this.gbModels.addAll(gbModels);
            this.gbAdatper.notifyDataSetChanged();
        }
    }

    @Override
    public void loadMoreFinished() {
        //当加载完数据后，再恢复加载脚View的显示状态为：上拉加载更多
        gbAdatper.changeMoreStatus(gbAdatper.PULLUP_LOAD_MORE);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

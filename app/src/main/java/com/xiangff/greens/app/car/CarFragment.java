package com.xiangff.greens.app.car;

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
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;
import com.xiangff.greens.app.MainActivity;
import com.xiangff.greens.app.R;
import com.xiangff.greens.app.car.adapter.CarAdatper;
import com.xiangff.greens.app.data.car.Car;
import com.xiangff.greens.app.goupbuy.adapter.GroupBuyAdatper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarFragment extends Fragment implements CarContract.View{

    private static final String TAG="CarFragment";
    private View rootView;//缓存 Fragment view

    private OnFragmentInteractionListener mListener;

    /*=======视图组件=======*/

    @BindView(R.id.tv_car_bottom_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.avi_car)
     AVLoadingIndicatorView avi;
    @BindView(R.id.srl_car)
     SwipeRefreshLayout srl;
    private LinearLayoutManager linearLayoutManager;
    @BindView(R.id.rv_car)
     RecyclerView recyclerView;
    private CarAdatper carAdatper;

    private CarContract.Presenter presenter;

    @Override
    public void setPresenter(CarContract.Presenter presenter) {
        this.presenter=presenter;
    }



    public CarFragment() {
        // Required empty public constructor
    }

    public static CarFragment newInstance() {
        CarFragment fragment = new CarFragment();
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
            rootView=inflater.inflate(R.layout.fragment_car, container, false);
            ButterKnife.bind(this,rootView);
            linearLayoutManager=new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);
            this.carAdatper=new CarAdatper(getActivity());
            recyclerView.setAdapter(this.carAdatper);
            initListener();

        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
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
                presenter.loadCarDatas();
            }
        });
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
        Log.i(TAG, "CarFragment-onAttach");
        if (context instanceof MainActivity){
            ((MainActivity)context).setCarView(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
        if (this.presenter!=null){
            //加载购物车数据
            this.presenter.start();
        }else{
            Log.e(TAG,"carView对应的presenter不能为空 !");
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
    public void showCarDatas(Car car) {
        this.carAdatper.notifyDataSetChanged();
        this.srl.setRefreshing(false);
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

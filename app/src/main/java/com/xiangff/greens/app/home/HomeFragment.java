package com.xiangff.greens.app.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.wang.avi.AVLoadingIndicatorView;
import com.xiangff.greens.app.MainActivity;
import com.xiangff.greens.app.R;
import com.xiangff.greens.app.data.Product;
import com.xiangff.greens.app.data.adv.Adv;
import com.xiangff.greens.app.home.adapter.HomeBargainPriceAdapter;
import com.xiangff.greens.app.home.greens.GreensActivity;
import com.yyydjk.library.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;


public class HomeFragment extends Fragment implements HomeContract.View {
    private static final String TAG = "HomeFragment";
    private View rootView;//缓存 Fragment view

    private HomeContract.Presenter presenter;

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.avi_home)
    AVLoadingIndicatorView avi;
    @BindView(R.id.rl_home_tools_garden)
    RelativeLayout rlGardenTool;
    @BindView(R.id.rl_home_tools_greens)
    RelativeLayout rlGreensTool;
    @BindView(R.id.rl_home_tools_cookbook)
    RelativeLayout rlCookbookTool;
    @BindView(R.id.rl_home_tools_health)
    RelativeLayout rlHealthTool;


    private List<Adv> advs = new ArrayList<Adv>();
    @BindView(R.id.banner_home)
    BannerLayout bannerLayout;
    @BindView(R.id.rv_home_bargains)
    RecyclerView bargainsRecyclerView;
    private List<Product> bargainProducts;
    private RecyclerView.Adapter homeBargainPriceAdapter;

    // 使用DisplayImageOptions.Builder()创建DisplayImageOptions
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_launcher) // 设置图片下载期间显示的图片
            .showImageForEmptyUri(R.mipmap.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片
            .showImageOnFail(R.mipmap.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片
            .cacheInMemory(false) // 设置下载的图片是否缓存在内存中
            .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
//    .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
            .build(); // 构建完成

    public HomeFragment() {
    }

    @Override
    public void setPresenter(@NonNull HomeContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter, "HomePresenter not be null");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "HomeFragment-onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            Log.i(TAG, "onCreateView");
            // Inflate the layout for this fragment
            rootView = inflater.inflate(R.layout.fragment_home, container, false);
            ButterKnife.bind(this, rootView);
//            avi = (AVLoadingIndicatorView) rootView.findViewById(R.id.avi_home);
//            bannerLayout= (BannerLayout) rootView.findViewById(R.id.banner_home);
            //添加监听事件
            bannerLayout.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
                @Override
                public void onItemClick(int position) {
//                    Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "点击了公告:" + position);
                }
            });
//            bargainsRecyclerView= (RecyclerView) rootView.findViewById(R.id.rv_home_bargains);
            bargainsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            bargainProducts = new ArrayList<Product>();
            for (int i = 0; i < 10; i++) {
                Product p = new Product();
                p.setId(i + 1);
                p.setTitle("新鲜" + i);
                p.setPrice("1.5");
                p.setPriceUnit("元");
                p.setWeigh("500");
                p.setWeighUnit("g");
                p.setUrl("http://10.0.2.2:8080/androidHtml5/greens" + (i + 1) + ".jpg");
                bargainProducts.add(p);
            }
            homeBargainPriceAdapter = new HomeBargainPriceAdapter(getActivity(), bargainProducts);
            bargainsRecyclerView.setAdapter(homeBargainPriceAdapter);
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        return rootView;
    }
    @OnClick({R.id.rl_home_tools_garden,R.id.rl_home_tools_greens,R.id.rl_home_tools_cookbook,R.id.rl_home_tools_health})
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.rl_home_tools_garden:
                Log.i(TAG,"to Garden");

                break;
            case R.id.rl_home_tools_greens:
                Log.i(TAG,"toGreens");
                intent=new Intent(getActivity(),GreensActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_home_tools_health:
                Log.i(TAG,"toHealth");
                break;
            case R.id.rl_home_tools_cookbook:
                Log.i(TAG,"toCookBook");
                break;
        }
    }

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
        Log.i(TAG, "HomeFragment-onAttach:" + this);
        if (context instanceof MainActivity) {
            ((MainActivity) context).setHomeView(this);
        }
    }

    private boolean hasLoaded = false;

    @Override
    public void onResume() {
        super.onResume();
        if (!hasLoaded) {
            //开始请求数据
            if (presenter != null) {
                presenter.start();
            } else
                Log.e(TAG, "presenter cannot bu null!");
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
        hasLoaded = true;
    }

    /**
     * 设置广告
     *
     * @param advs
     */
    @Override
    public void setAdvs(List<Adv> advs) {
        this.advs.clear();
        this.advs.addAll(advs);
        List<String> urls = new ArrayList<String>();
        for (Adv adv :
                advs) {
            urls.add(adv.getUrl());
        }
        bannerLayout.setViewUrls(urls);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

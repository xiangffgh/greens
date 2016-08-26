package com.xiangff.greens.app;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.xiangff.greens.app.base.App;
import com.xiangff.greens.app.base.BaseActivity;
import com.xiangff.greens.app.base.BasePresenter;
import com.xiangff.greens.app.car.CarFragment;
import com.xiangff.greens.app.common.receiver.AppExitRecevier;
import com.xiangff.greens.app.data.ProductRepository;
import com.xiangff.greens.app.data.local.ProductLocalDataSource;
import com.xiangff.greens.app.data.remote.ProductRemoteDataSource;
import com.xiangff.greens.app.find.FindFragment;
import com.xiangff.greens.app.goupbuy.GroupBuyFragment;
import com.xiangff.greens.app.home.HomeFragment;
import com.xiangff.greens.app.home.HomePresenter;
import com.xiangff.greens.app.my.MyFragment;

import java.util.List;

/**
 * 主窗口
 * <p/>
 * 相非非
 * 2016 08 09
 * V1.0
 */
public class MainActivity extends BaseActivity implements HomeFragment.OnFragmentInteractionListener, GroupBuyFragment.OnFragmentInteractionListener, CarFragment.OnFragmentInteractionListener, FindFragment.OnFragmentInteractionListener, MyFragment.OnFragmentInteractionListener {
    private static  final String TAG="MainActivity";
    private FragmentTabHost tabHost;
    private LayoutInflater layoutInflater;
    private Class fragmentArray[] = {HomeFragment.class, GroupBuyFragment.class, CarFragment.class, FindFragment.class, MyFragment.class};
    private String frgmentTags[]={"TAG_HOME","TAG_GROUPBUY","TAG_CAR","TAG_FIND","TAG_MY"};
    private BasePresenter homePresenter;
    private int tabImageArray[] = {R.mipmap.home_tab_1_p, R.mipmap.home_tab_2_p, R.mipmap.home_tab_3_p, R.mipmap.home_tab_4_p, R.mipmap.home_tab_5_p};
    private String tabNameArray[] = {"有货", "团货", "购物车", "发现", "我的"};

    private HomeFragment homeView;

    public HomeFragment getHomeView() {
        return homeView;
    }

    public void setHomeView(HomeFragment homeView) {
        this.homeView = homeView;
        Log.i(TAG, "MainActivity-setHomeView-homeView:" + homeView);
        /*
         *初始化 MP
         */
        homePresenter=new HomePresenter(ProductRepository.getInstance(ProductRemoteDataSource.getInstance(), ProductLocalDataSource.getInstance(this)),homeView);
    }

    private AppExitRecevier appExitRecevier = new AppExitRecevier() {
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
            MainActivity.this.finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");
        initView();

        initRecevier();
        Log.i(TAG,"onCreateFinished");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }
    private void initRecevier() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(AppExitRecevier.ACTION_EXIT);
        this.registerReceiver(appExitRecevier, filter);
    }

    /**
     * 初始化组件
     */
    private void initView() {
        layoutInflater = LayoutInflater.from(this);

        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.main_realtabcontent);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.i(TAG, "onTabChanged:" + tabId);
            }
        });
        for (int i = 0; i < fragmentArray.length; i++) {
            //为每一个Tab设置图标、文字和内容
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(frgmentTags[i]).setIndicator(getTabItemView(i));
            //将Tab添加到Tab选项卡中
            tabHost.addTab(tabSpec, fragmentArray[i], null);
            //设置Tab按钮背景颜色
//            tabHost.getTabWidget().getChildAt(i).setBackgroundResource();
        }
    }


    private View getTabItemView(int i) {
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_tab_item_view);
        iv.setImageResource(tabImageArray[i]);
        TextView tv = (TextView) view.findViewById(R.id.tv_tab_item_view);
        tv.setText(tabNameArray[i]);
        return view;
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(appExitRecevier);
    }
}

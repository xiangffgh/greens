package com.xiangff.greens.app.home.healthy;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.xiangff.greens.app.R;
import com.xiangff.greens.app.base.BasePresenter;
import com.xiangff.greens.app.data.healthy.HealthyModel;
import com.xiangff.greens.app.home.greens.GreensPresenter;
import com.xiangff.greens.app.home.healthy.adapter.HealthyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HealthiesActivity extends AppCompatActivity implements HealthiesContract.View {
    private static final String TAG = "HealthiesActivity";
    @BindView(R.id.ll_healthies_back)
    LinearLayout back;
    @BindView(R.id.srl_healthies)
    SwipeRefreshLayout srl;
    @BindView(R.id.rv_healthies)
    RecyclerView rvHealthies;
    LinearLayoutManager linearLayoutManager;
    HealthyAdapter healthyAdapter;
    List<HealthyModel> healthyModels;

    private HealthiesContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthies);
        ButterKnife.bind(this);

        initViews();
        initVP();
        initListener();
    }

    private void initViews() {
        linearLayoutManager = new LinearLayoutManager(this);
        rvHealthies.setLayoutManager(linearLayoutManager);
        healthyModels = new ArrayList<>();
        healthyAdapter = new HealthyAdapter(this, healthyModels);
        rvHealthies.setAdapter(healthyAdapter);
    }

    private void initVP() {
        this.presenter = new HealthiesPresenter(this);
    }

    private void initListener() {
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*下拉刷新数据*/
                presenter.initDatas();
                Log.i(TAG, "下拉刷新数据");
            }
        });
        rvHealthies.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!srl.isRefreshing()) {
                    int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    Log.i(TAG, "lastVisibleItem:" + lastVisibleItem);
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == healthyAdapter.getItemCount()) {
                        //调用Adapter里的changeMoreStatus方法来改变加载脚View的显示状态为：正在加载...
                        healthyAdapter.changeMoreStatus(healthyAdapter.ISLOADING);
                        /*加载更多数据*/
                        presenter.loadMoreDatas();
                    }
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.start();
        }
    }

    @Override
    public void setDatas(List<HealthyModel> healthyModels) {
        healthyAdapter.setDatas(healthyModels);
        this.srl.setRefreshing(false);
    }

    @Override
    public void addDatas(List<HealthyModel> healthyModels) {
        healthyAdapter.addDatas(healthyModels);
    }

    @Override
    public void loadMoreFinished() {
        //当加载完数据后，再恢复加载脚View的显示状态为：上拉加载更多
        healthyAdapter.changeMoreStatus(healthyAdapter.PULLUP_LOAD_MORE);
    }


    @Override
    public void setPresenter(HealthiesContract.Presenter presenter) {

    }
}

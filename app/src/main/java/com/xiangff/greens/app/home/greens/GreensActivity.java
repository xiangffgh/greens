package com.xiangff.greens.app.home.greens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wang.avi.AVLoadingIndicatorView;
import com.xiangff.greens.app.R;
import com.xiangff.greens.app.base.BasePresenter;
import com.xiangff.greens.app.data.Product;
import com.xiangff.greens.app.home.greens.adatper.GreensAdatper;
import com.xiangff.greens.app.home.greens.greendetail.GreenDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GreensActivity extends AppCompatActivity implements GreensContract.View {
    private static final String TAG = "GreensActivity";

    /*=======视图组件=======*/
    @BindView(R.id.avi_greens)
    AVLoadingIndicatorView avi;
    @BindView(R.id.srl_greens)
    SwipeRefreshLayout srl;
    GridLayoutManager gridLayoutManager;
    @BindView(R.id.rv_greens)
    RecyclerView recyclerView;
    private List<Product> products;
    private GreensAdatper productAdatper;

    private BasePresenter presenter;

    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greens);
        ButterKnife.bind(this);

        initViews();
        initVP();
        initListener();
    }

    private void initVP() {
        presenter = new GreensPresenter(this);
    }

    private void initViews() {
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        products = new ArrayList<>();
        productAdatper = new GreensAdatper(this, products);
        recyclerView.setAdapter(productAdatper);
    }

    /**
     * 初始化监听器
     */
    private void initListener() {
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*下拉刷新数据*/
                if (presenter instanceof GreensPresenter){
                    ((GreensPresenter)presenter).initDatas();
                    Log.i(TAG,"下拉刷新数据");
                }

            }
        });
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!srl.isRefreshing()) {
                    int lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
                    Log.i(TAG, "lastVisibleItem:" + lastVisibleItem);
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == productAdatper.getItemCount()) {
                        //调用Adapter里的changeMoreStatus方法来改变加载脚View的显示状态为：正在加载...
                        productAdatper.changeMoreStatus(productAdatper.ISLOADING);
                        /*加载更多数据*/
                        if (presenter instanceof GreensPresenter) {
                            ((GreensPresenter) presenter).loadMoreDatas();
                        }
                    }
                }
            }
        });
        productAdatper.setOnItemClickLitener(new GreensAdatper.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(GreensActivity.this, GreenDetailActivity.class);
                if (position<productAdatper.getDatas().size()){
                    intent.putExtra("green",productAdatper.getDatas().get(position));
                    startActivity(intent);
                }
            }
        });
    }

    private boolean hasLoaded = false;

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
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
    public void loadMoreFinished() {
        //当加载完数据后，再恢复加载脚View的显示状态为：上拉加载更多
        productAdatper.changeMoreStatus(productAdatper.PULLUP_LOAD_MORE);
    }

    @Override
    public void setDatas(List<Product> datas) {
        Log.i(TAG,"view setDatas");
        productAdatper.setDatas(datas);
        this.srl.setRefreshing(false);
        Log.i(TAG,"设置下拉刷新图片消失");
    }

    @Override
    public void addDatas(List<Product> datas) {
        productAdatper.addDatas(datas);
    }

}

package com.xiangff.greens.app.goupbuy;

import android.util.Log;

import com.xiangff.greens.app.data.api.ApiServiceManager;
import com.xiangff.greens.app.data.groupbuy.GBModel;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * Created by xiangff on 2016/8/29.
 */
public class GroupBuyPresenter implements GroupBuyContract.Presenter {

    private static final String TAG = "GroupBuyPresenter";

    private GroupBuyContract.View view;

    public GroupBuyPresenter(GroupBuyContract.View view) {
        this.view = checkNotNull(view, "GroupBuyView not bu null");
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        if (this.currentPage == 0) {
            initDatas();
        } else {
            loadMoreDatas();
        }
    }

    /**
     * 记录当前分页
     */
    private static int currentPage = 0;

    @Override
    public void initDatas() {
        Log.i(TAG, "初始加载数据");
        //初始化页数
        currentPage = 0;
        getDatas(currentPage);
    }

    @Override
    public void loadMoreDatas() {
        Log.i(TAG, "下拉加载数据");
        currentPage += 1;
        getDatas(currentPage);
    }

    public void getDatas(final int currentPage) {
        view.showLoadingIndicator();

        ApiServiceManager.getInstance().getApiService().getGbModels(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GBModel>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                        view.hideLoadingIndicator();
                    }

                    @Override
                    public void onError(Throwable e) {
                        GroupBuyPresenter.currentPage -=1;
                        view.hideLoadingIndicator();
                        Log.e(TAG, e.getMessage(), e);
                    }

                    @Override
                    public void onNext(List<GBModel> gbModels) {
                        Log.i(TAG,"更新View的数据");
                        if (currentPage <= 0)
                            view.setDatas(gbModels);
                        else view.addDatas(gbModels);
                    }
                });
    }
}

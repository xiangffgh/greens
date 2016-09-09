package com.xiangff.greens.app.home.healthy;

import android.util.Log;

import com.xiangff.greens.app.base.BasePresenter;
import com.xiangff.greens.app.data.api.ApiService;
import com.xiangff.greens.app.data.api.ApiServiceManager;
import com.xiangff.greens.app.data.healthy.HealthyModel;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xiangff on 2016/9/9.
 */
public class HealthiesPresenter implements HealthiesContract.Presenter {

    private static final String TAG = "HealthiesPresenter";
    private HealthiesContract.View view;

    public HealthiesPresenter(HealthiesContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        initDatas();
    }

    int currentPage = 1;

    @Override
    public void initDatas() {
        currentPage = 1;
        loadDatas(currentPage);
    }

    @Override
    public void loadMoreDatas() {
        currentPage += 1;

        loadDatas(currentPage);
    }

    private void loadDatas(int page) {
        if (page<0){
            currentPage=0;
        }
        ApiServiceManager.getInstance().getApiService().getHealthies(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<HealthyModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage(), e);
                        currentPage-=1;
                    }

                    @Override
                    public void onNext(List<HealthyModel> healthyModels) {
                        if (currentPage==1){
                            view.setDatas(healthyModels);
                        }else {
                            view.addDatas(healthyModels);
                        }
                        view.setDatas(healthyModels);
                    }
                });
    }


}

package com.xiangff.greens.app.home;

import android.support.annotation.NonNull;
import android.util.Log;

import static com.google.common.base.Preconditions.checkNotNull;

import com.xiangff.greens.app.base.BasePresenter;
import com.xiangff.greens.app.data.ProductRepository;
import com.xiangff.greens.app.data.adv.Adv;
import com.xiangff.greens.app.data.api.ApiServiceManager;
import com.xiangff.greens.app.data.api.ResultModel;

import java.util.List;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xiangff on 2016/8/19.
 */
public class HomePresenter implements HomeContract.Presenter {
    private static final String TAG = "HomePresenter";
    private final ProductRepository productRepository;
    private final HomeContract.View homeView;

    public HomePresenter(@NonNull ProductRepository repository, @NonNull HomeContract.View homeView) {
        this.productRepository = checkNotNull(repository, "productRepository cannot be null");
        this.homeView = checkNotNull(homeView, "HomeView not be null");

        this.homeView.setPresenter(this);
    }

    /**
     * 记录请求数，用于加载指示的显示
     */
    private int count = 0;

    @Override
    public void start() {
        count = 2;
        homeView.showLoadingIndicator();
        getTitle();
        loadRollingAdvertisement();
    }

    @Override
    public void loadRollingAdvertisement() {
        ApiServiceManager.getInstance().getApiService().getAdvs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Adv>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG,"loadAdvs-onCompleted");
                        computHideLoadingIndicator();
                    }

                    @Override
                    public void onError(Throwable e) {
                        computHideLoadingIndicator();;
                        Log.e(TAG,e.getMessage(),e);
                    }

                    @Override
                    public void onNext(List<Adv> advs) {
                        homeView.setAdvs(advs);
                    }
                });
    }

    @Override
    public void loadBargainPriceProduct() {

    }

    @Override
    public void getTitle() {
        homeView.setTitle("");
        ApiServiceManager.getInstance().getApiService().getLoginInfo("z", "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResultModel>() {
                    @Override
                    public void onCompleted() {
//                        homeView.hideLoadingIndicator();
                        computHideLoadingIndicator();
                        Log.i(TAG, "getTitle-onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
//                        homeView.hideLoadingIndicator();
                        computHideLoadingIndicator();
                        Log.e(TAG, e.getMessage(), e);
                    }

                    @Override
                    public void onNext(ResultModel resultModel) {
                        Log.i(TAG, "getTitle - onNext-s:" + resultModel.toString());
                        homeView.setTitle(resultModel.toString());
                    }
                });

    }

    /**
     * 判断是否隐藏加载指示视图
     */
    private void computHideLoadingIndicator() {
        count--;
        if (count <= 0) {
            homeView.hideLoadingIndicator();
        }
    }
}
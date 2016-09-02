package com.xiangff.greens.app.home.greens;

import android.util.Log;

import com.xiangff.greens.app.data.Product;
import com.xiangff.greens.app.data.api.ApiServiceManager;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 * Created by xiangff on 2016/9/2.
 */
public class GreensPresenter implements GreensContract.Presenter {
    private static String TAG="GreensPresenter";
    private GreensContract.View view;

    /**
     * 构造P 传入V
     * @param view
     */
    public GreensPresenter(GreensContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        initDatas();
    }
    /**
     * 记录当前分页
     */
    private static int currentPage = 0;
    @Override
    public void initDatas(){
        //初始化页数
        currentPage = 0;
        loadDatas(currentPage);
    }
    @Override
    public void loadMoreDatas(){
        currentPage += 1;
        loadDatas(currentPage);
    }

    public void loadDatas(int page) {
        view.showLoadingIndicator();
        if (page<0){
            currentPage=0;
        }
        ApiServiceManager.getInstance().getApiService().getGreens(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Product>>() {
                    @Override
                    public void onCompleted() {
                        view.hideLoadingIndicator();
                    }

                    @Override
                    public void onError(Throwable e) {
                        currentPage-=1;
                        view.hideLoadingIndicator();
                        Log.e(TAG,e.getMessage(),e);
                    }

                    @Override
                    public void onNext(List<Product> products) {
                        Log.i(TAG, products.toString());
                        if (currentPage>0){
                            view.setDatas(products);
                        }else{
                            view.addDatas(products);
                        }
                        view.hideLoadingIndicator();
                    }
                });
    }
}

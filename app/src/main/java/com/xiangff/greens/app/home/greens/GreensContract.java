package com.xiangff.greens.app.home.greens;

import com.xiangff.greens.app.base.BasePresenter;
import com.xiangff.greens.app.base.BaseView;
import com.xiangff.greens.app.data.Product;

import java.util.List;

/**
 *
 * Created by xiangff on 2016/9/2.
 */
public class GreensContract {

    interface View extends BaseView<BasePresenter>{
        void showLoadingIndicator();
        void hideLoadingIndicator();
        void setDatas(List<Product> datas);
        void addDatas(List<Product> datas);
        void loadMoreFinished();
    }


    interface Presenter extends BasePresenter{
        void initDatas();
        void loadMoreDatas();
    }
}

package com.xiangff.greens.app.home;

import com.xiangff.greens.app.base.BasePresenter;
import com.xiangff.greens.app.base.BaseView;
import com.xiangff.greens.app.data.adv.Adv;

import java.util.List;

/**
 * Created by xiangff on 2016/8/19.
 */
public class HomeContract {
    interface View extends BaseView<Presenter> {

        void showLoadingIndicator();
        void hideLoadingIndicator();
        void setTitle(String title);
        void setAdvs(List<Adv> advs);
    }

    interface Presenter extends BasePresenter {
            /**加载滚动广告*/
            void loadRollingAdvertisement();
            /**加载特价产品*/
            void loadBargainPriceProduct();
            void getTitle();
    }
}

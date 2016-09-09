package com.xiangff.greens.app.home.healthy;

import com.xiangff.greens.app.base.BasePresenter;
import com.xiangff.greens.app.base.BaseView;
import com.xiangff.greens.app.data.healthy.HealthyModel;

import java.util.List;

/**
 * Created by xiangff on 2016/9/9.
 */
public class HealthiesContract {

    interface View extends BaseView<Presenter> {
        void setDatas(List<HealthyModel> healthyModels);

        void addDatas(List<HealthyModel> healthyModels);

        void loadMoreFinished();
    }

    interface Presenter extends BasePresenter {
        void initDatas();

        void loadMoreDatas();
    }
}

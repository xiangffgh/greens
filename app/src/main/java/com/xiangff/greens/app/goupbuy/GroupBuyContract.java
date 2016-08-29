package com.xiangff.greens.app.goupbuy;

import com.xiangff.greens.app.base.BasePresenter;
import com.xiangff.greens.app.base.BaseView;
import com.xiangff.greens.app.data.groupbuy.GBModel;

import java.util.List;

/**
 *
 * Created by xiangff on 2016/8/29.
 */
public class GroupBuyContract {

    interface View extends BaseView<Presenter>{
        void showLoadingIndicator();
        void hideLoadingIndicator();
        void setDatas(List<GBModel> gbModels);
        void addDatas(List<GBModel> gbModels);
        void loadMoreFinished();
    }

    interface  Presenter extends BasePresenter{
        void initDatas();
        void loadMoreDatas();
    }
}

package com.xiangff.greens.app.car;

import com.xiangff.greens.app.base.BasePresenter;
import com.xiangff.greens.app.base.BaseView;
import com.xiangff.greens.app.data.car.Car;
import com.xiangff.greens.app.data.car.CarItem;

/**
 *
 * Created by xiangff on 2016/8/30.
 */
public class CarContract {

    interface View extends BaseView<Presenter>{
        void showLoadingIndicator();
        void hideLoadingIndicator();
        void showCarDatas(Car car);
    }

    interface Presenter extends BasePresenter{
        void loadCarDatas();
        void addItemNum(CarItem item);
        void subtractItemNum(CarItem item);
        void removeItem(CarItem item);
    }

}

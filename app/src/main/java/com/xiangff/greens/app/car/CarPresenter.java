package com.xiangff.greens.app.car;

import android.os.Handler;

import com.xiangff.greens.app.data.car.Car;
import com.xiangff.greens.app.data.car.CarItem;

/**
 * Created by xiangff on 2016/8/30.
 */
public class CarPresenter implements CarContract.Presenter {

    private CarContract.View view;

    public CarPresenter(CarContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        loadCarDatas();
    }

    @Override
    public void loadCarDatas() {
        //加载购物车数据
        CarPresenter.this.view.showCarDatas(Car.getInstance());
    }

    @Override
    public void addItemNum(CarItem item) {
        Car.getInstance().addItemNum(item);
        this.view.showCarDatas(Car.getInstance());
    }

    @Override
    public void subtractItemNum(CarItem item) {
        Car.getInstance().subtractItemNum(item);
        this.view.showCarDatas(Car.getInstance());
    }

    @Override
    public void removeItem(CarItem item) {
        Car.getInstance().removeItem(item);
        this.view.showCarDatas(Car.getInstance());
    }


}

package com.xiangff.greens.app.car;

import android.os.Handler;

import com.xiangff.greens.app.data.car.Car;
import com.xiangff.greens.app.data.car.CarItem;

/**
 *
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
        new Handler().postAtTime(new Runnable() {
            @Override
            public void run() {
                Car.getInstance().initDatas();
                //查询数据库
                for (int i = 0; i < 3; i++) {
                    CarItem item = new CarItem();
                    item.setItemNum((i + 1));
                    item.setProductId((i + 1));
                    item.setProductPrice("12.5");
                    item.setItemNum(1);
                    Car.getInstance().addItem(item);
                }

                //加载购物车数据
                CarPresenter.this.view.showCarDatas(Car.getInstance());
            }
        },3000);

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

package com.xiangff.greens.app.data.car;

import android.util.Log;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangff on 2016/8/30.
 */
public class Car {

    private static final String TAG = "Car";

    private static class CarHolder {
        public static final Car car = new Car();
    }

    private List<CarItem> items;
    private String totalPrice;
    private String freightPrice = "0";//运费

    private Car() {
        if (items == null)
            items = new ArrayList<CarItem>();
    }

    public static Car getInstance() {
        return CarHolder.car;
    }

    public List<CarItem> getItems() {
        return items;
    }

    public void addItem(CarItem item) {
        if (items.contains(item)) {
            for (CarItem carItem :
                    items) {
                if (carItem.equals(item)) {
                    carItem.setItemNum(carItem.getItemNum() + 1);
                    computTotalPrice();
                    return;
                }
            }
        }
        item.setItemNum(1);
        this.items.add(item);
        computTotalPrice();
    }

    public void initDatas() {
        if (this.items != null)
            this.items.clear();
    }

    public void addItemNum(CarItem item) {
        if (items.contains(item)) {
            for (CarItem carItem :
                    items) {
                if (carItem.equals(item)) {
                    carItem.setItemNum(item.getItemNum() + 1);
                    computTotalPrice();
                    return;
                }
            }
        }
    }

    public void subtractItemNum(CarItem item) {
        if (items.contains(item)) {
            if (item.getItemNum() > 0) {
                for (CarItem carItem :
                        items) {
                    if (carItem.equals(item)) {
                        carItem.setItemNum(item.getItemNum() - 1);
                        computTotalPrice();
                    }
                }
            }
        }
    }

    public void setItemNum(CarItem item, int num) {
        if (items.contains(item)) {
            if (item.getItemNum() > 0) {
                for (CarItem carItem :
                        items) {
                    if (carItem.equals(item)) {
                        Log.i(TAG,"Car - setItemNum:"+num);
                        carItem.setItemNum(num);
                        computTotalPrice();
                    }
                }
            }
        }
    }

    public void removeItem(CarItem item) {
        if (items.contains(item)) {
            items.remove(item);
            computTotalPrice();
        }
    }

    public void computTotalPrice() {
        BigDecimal bd = new BigDecimal("0");
        if (items != null && items.size() > 0) {
            for (CarItem item :
                    items) {
                BigDecimal bdItemPrice = new BigDecimal(item.getProductPrice());
                BigDecimal bdItemNum = new BigDecimal(item.getItemNum());
                BigDecimal bdItemTotal = bdItemPrice.multiply(bdItemNum);
                bd = bd.add(bdItemTotal);
            }
        } else {
            totalPrice = "0";
            freightPrice = "0";
        }
        totalPrice = bd.toString();
    }

    public int getItemsSize() {
        return items.size();
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(String freightPrice) {
        this.freightPrice = freightPrice;
    }
}

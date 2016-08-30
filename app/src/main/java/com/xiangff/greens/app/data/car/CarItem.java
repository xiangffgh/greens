package com.xiangff.greens.app.data.car;

/**
 *
 * Created by xiangff on 2016/8/30.
 */
public class CarItem {
    private int itemId;
    private int productId;
    private String productTitle;
    private String productPrice;
    private int itemNum;

    public CarItem() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }
}

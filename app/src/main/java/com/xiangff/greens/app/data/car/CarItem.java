package com.xiangff.greens.app.data.car;

/**
 *
 * Created by xiangff on 2016/8/30.
 */
public class CarItem {
    private int itemId;
    private int productId;
    private String productTitle;
    private String productUrl;
    private String productPrice;

    private int itemNum;

    public CarItem() {
    }

    @Override
    public boolean equals(Object o) {
        if (o==null) return false;
        if (o==this) return true;
        if (o instanceof CarItem){
            CarItem item= (CarItem) o;
            if (item.getProductId()==this.getProductId()){
                return true;
            }
        }
        return false;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
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

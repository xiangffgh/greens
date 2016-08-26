package com.xiangff.greens.app.data;

/**
 *
 * Created by xiangff on 2016/8/19.
 */
public class Product {

    private int id;
    private String name;
    private String title;
    private String price;
    private String priceUnit;
    private String weigh;
    private String weighUnit;
    private String url;
    private String createDate;
    private boolean isBargain;//是否特价

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isBargain() {
        return isBargain;
    }

    public void setIsBargain(boolean isBargain) {
        this.isBargain = isBargain;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getWeigh() {
        return weigh;
    }

    public void setWeigh(String weigh) {
        this.weigh = weigh;
    }

    public String getWeighUnit() {
        return weighUnit;
    }

    public void setWeighUnit(String weighUnit) {
        this.weighUnit = weighUnit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

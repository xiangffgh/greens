package com.xiangff.greens.app.data;

import java.io.Serializable;

/**
 *
 * Created by xiangff on 2016/8/19.
 */
public class Product implements Serializable{

    private int id;
    private String name;
    private String title;
    private String description;
    private String address;
    private String price;

    private String originPrice;//原价
    private String discount;//折扣 如:7.2折

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(String originPrice) {
        this.originPrice = originPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", price='" + price + '\'' +
                ", originPrice='" + originPrice + '\'' +
                ", discount='" + discount + '\'' +
                ", priceUnit='" + priceUnit + '\'' +
                ", weigh='" + weigh + '\'' +
                ", weighUnit='" + weighUnit + '\'' +
                ", url='" + url + '\'' +
                ", createDate='" + createDate + '\'' +
                ", isBargain=" + isBargain +
                '}';
    }
}

package com.xiangff.greens.app.data.groupbuy;

import java.util.List;

/**
 *
 * Created by xiangff on 2016/8/29.
 */
public class GBModel {
    private int id;
    private String gbId;
    /**
     * 标题
     */
    private String title;
    /**
     * 简要描述
     */
    private String description;
    /**
     * 图片Url
     */
    private String imgUrl;
    /**
     * 产品详细地址
     */
    private String address;
    /**
     * 市场价
     */
    private String marketPrice;
    /**
     * 秒购价
     */
    private String gbPrice;

    /**
     * 单位
     */
    private String gbUnit;
    /**
     * 收割时间
     */
    private String harvestTime;
    /**
     * 剩余收割时间
     */
    private String remainingTime;
    /**
     * 当前团购人数
     */
    private int currentGroupPerson;
    /**
     * 当前订单量
     */
    private String currentOrders;
    /**
     * 总订单量
     */
    private String totalOrders;
    /**
     * 订单阶梯价格描述
     */
    private String differentialPricing;
    /**
     * 当前状态
     * 0 未收割
     * 1 正在收割
     * 2 运货中
     * 3 已结束
     */
    private int status;

    public  enum GBStatus {

        NO_HARVEST {
            public String getName() {
                return "未收割";
            }
        },
        HARVESTING {
            public String getName() {
                return "正在收割";
            }
        },
        CAR_GO_IN {
            public String getName() {
                return "运货中";
            }
        },
        FINISHED {
            public String getName() {
                return "已结束";
            }
        };
        public abstract String getName();
    }

    /**
     * 目前照片集
     */
    private List<String> noHarvestPictrues;
    /**
     * 正在收割照片集
     */
    private List<String> harvestingPictrues;
    /**
     * 运货中照片集合
     */
    private List<String> carGoInPrictrues;

    public GBModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGbId() {
        return gbId;
    }

    public void setGbId(String gbId) {
        this.gbId = gbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getGbPrice() {
        return gbPrice;
    }

    public void setGbPrice(String gbPrice) {
        this.gbPrice = gbPrice;
    }

    public String getGbUnit() {
        return gbUnit;
    }

    public void setGbUnit(String gbUnit) {
        this.gbUnit = gbUnit;
    }

    public String getHarvestTime() {
        return harvestTime;
    }

    public void setHarvestTime(String harvestTime) {
        this.harvestTime = harvestTime;
    }

    public String getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(String remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getCurrentOrders() {
        return currentOrders;
    }

    public void setCurrentOrders(String currentOrders) {
        this.currentOrders = currentOrders;
    }

    public String getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(String totalOrders) {
        this.totalOrders = totalOrders;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDifferentialPricing() {
        return differentialPricing;
    }

    public void setDifferentialPricing(String differentialPricing) {
        this.differentialPricing = differentialPricing;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getNoHarvestPictrues() {
        return noHarvestPictrues;
    }

    public void setNoHarvestPictrues(List<String> noHarvestPictrues) {
        this.noHarvestPictrues = noHarvestPictrues;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getCurrentGroupPerson() {
        return currentGroupPerson;
    }

    public void setCurrentGroupPerson(int currentGroupPerson) {
        this.currentGroupPerson = currentGroupPerson;
    }

    public List<String> getHarvestingPictrues() {
        return harvestingPictrues;
    }

    public void setHarvestingPictrues(List<String> harvestingPictrues) {
        this.harvestingPictrues = harvestingPictrues;
    }

    public List<String> getCarGoInPrictrues() {
        return carGoInPrictrues;
    }

    public void setCarGoInPrictrues(List<String> carGoInPrictrues) {
        this.carGoInPrictrues = carGoInPrictrues;
    }
}

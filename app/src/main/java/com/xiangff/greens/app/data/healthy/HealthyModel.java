package com.xiangff.greens.app.data.healthy;

/**
 * Created by xiangff on 2016/9/9.
 */
public class HealthyModel {
    private int id;
    private String title;
    private String createDate;
    private String imgUrl;

    public HealthyModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "HealthyModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createDate='" + createDate + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}

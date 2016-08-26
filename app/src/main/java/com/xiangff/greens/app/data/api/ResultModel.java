package com.xiangff.greens.app.data.api;

/**
 * Created by xiangff on 2016/8/24.
 */
public class ResultModel {
    private String stat;


    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    private UserModel user;

    public UserModel getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "ResultModel{" +
                "stat='" + stat + '\'' +
                ", user=" + user +
                '}';
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}

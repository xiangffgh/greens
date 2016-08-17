package com.xiangff.greens.app.base;

import android.app.Application;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by xiangff on 2016/8/17.
 */
public class App extends Application {
    /**保存应用程序缓存数据*/
    Map<String,Object> caches=new HashMap<String,Object>();

    /*
    单例对象
     */
    public App(){}
    private static class  AppHolder{
        private static final App instance=new App();
    }
    public static  App getInstance(){
        return  AppHolder.instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }



}

package com.xiangff.greens.app.base;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 应用程序上下文
 *
 * Created by xiangff on 2016/8/17.
 */
public class App extends Application {
    private static final String TAG="App";

    /**保存缓存数据*/
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
        /*第三个参数 开发时设置为true  发布时设置为false*/
        CrashReport.initCrashReport(getApplicationContext(), "900048180", true);
        initUniversalImageLoader();
    }

    private void initUniversalImageLoader() {
        ImageLoaderConfiguration config = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(config);
    }

    /**
     * 内存不足时释放资源
     * @param level 内存告急级别
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level) {
            case TRIM_MEMORY_UI_HIDDEN:
                /*释放资源*/
                break;
        }
    }
}

package com.xiangff.greens.app.base;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.xiangff.greens.app.common.receiver.AppExitRecevier;

/**
 * Created by xiangff on 2016/8/17.
 */
public class BaseActivity extends AppCompatActivity {

//    public static final String ACTION_NETWORK_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
//    public static final String ACTION_PUSH_DATA = "fm.data.push.action";
//    public static final String ACTION_NEW_VERSION = "apk.update.action";

    private AppExitRecevier appExitRecevier=new AppExitRecevier(){
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
            BaseActivity.this.finish();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        IntentFilter filter=new IntentFilter();
        filter.addAction(AppExitRecevier.ACTION_EXIT);
        this.registerReceiver(appExitRecevier,filter );
    }

    @Override
    protected void onResume() {
            super.onResume();
        //还可能发送统计数据，比如第三方的SDK 做统计需求

    }
    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(appExitRecevier);
    }

    // 横竖屏切换，键盘等
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        try {
            super.onRestoreInstanceState(savedInstanceState);
        } catch (Exception e) {
        }
    }
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

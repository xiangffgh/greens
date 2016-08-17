package com.xiangff.greens.app.base;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by xiangff on 2016/8/17.
 */
public class BaseActivity extends AppCompatActivity{

//    public static final String ACTION_NETWORK_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
//    public static final String ACTION_PUSH_DATA = "fm.data.push.action";
//    public static final String ACTION_NEW_VERSION = "apk.update.action";

    @Override
    protected void onResume() {
        super.onResume();

        //还可能发送统计数据，比如第三方的SDK 做统计需求
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}

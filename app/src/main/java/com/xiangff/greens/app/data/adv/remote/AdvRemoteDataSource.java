package com.xiangff.greens.app.data.adv.remote;

import android.support.annotation.NonNull;

import com.xiangff.greens.app.data.adv.Adv;
import com.xiangff.greens.app.data.adv.AdvDataSource;

/**
 * Created by xiangff on 2016/8/22.
 */
public class AdvRemoteDataSource implements AdvDataSource {


    @Override
    public void getAdvs(@NonNull LoadAdvCallback callback) {
        //发起网络请求，得到数据

        if (callback != null) {

        }
    }

    @Override
    public void getAdv(@NonNull GetAdvCallback callback) {
        //发起网络请求

        if (callback != null) {

        }
    }

    @Override
    public void saveAdv(@NonNull Adv adv) {

    }
}

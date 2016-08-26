package com.xiangff.greens.app.data.adv;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * 保持最新的广告图片和链接
 * 联网之后更新广告滚动条，获得之后替换数据库数据
 * Created by xiangff on 2016/8/22.
 */
public interface AdvDataSource {

    interface LoadAdvCallback {
        void onAdvLoaded(List<Adv> advs);

        void onDataNotAvaliable();
    }

    interface GetAdvCallback {
        void onAdvLoaded(Adv adv);

        void onDataNotAvaliable();
    }

    void getAdvs(@NonNull LoadAdvCallback callback);

    void getAdv(@NonNull GetAdvCallback callback);

    void saveAdv(@NonNull Adv adv);

}

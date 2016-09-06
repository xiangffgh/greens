package com.xiangff.greens.app.goupbuy.groupbuydetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.xiangff.greens.app.R;
import com.xiangff.greens.app.data.groupbuy.GBModel;
import com.yyydjk.library.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupbuyDetailActivity extends AppCompatActivity {
    private static final String TAG="GroupbuyDetailActivity";
    @BindView(R.id.banner_groupbuy_detail)
    BannerLayout bannerDetail;
    @BindView(R.id.banner_groupbuy_detail_no_harvest)
    BannerLayout bannerNoHarvest;
    @BindView(R.id.banner_groupbuy_detail_harvesting)
    BannerLayout bannerHarvesting;

    @BindView(R.id.ll_groupbuy_detail_title_back)
    LinearLayout back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupbuy_detail);
        ButterKnife.bind(this);

        init();
        addLinstenr();
    }

    private void init() {
        Intent intent=getIntent();
        GBModel gbModel= (GBModel) intent.getSerializableExtra("gb");
        Log.i(TAG,"init gbModel:"+gbModel);

        List<String> urls=new ArrayList<>();
        List<String> noHarvestUrls=new ArrayList<>();
        List<String> harvestingUrls=new ArrayList<>();
        urls.add("http://img1.sc115.com/uploads/sc/jpg/HD/33/4090.jpg");
        urls.add("http://pic31.nipic.com/20130803/8821914_172601136000_2.jpg");
        urls.add("http://picapi.ooopic.com/10/18/46/78b1OOOPICd7.jpg");
        urls.add("http://pic32.nipic.com/20130827/8952533_150707601000_2.jpg");
        urls.add("http://img2.3lian.com/img2007/22/28/223.jpg");
        urls.add("http://picapi.ooopic.com/10/18/12/95b1OOOPIC89.jpg");

        noHarvestUrls.add("http://static6.photo.sina.com.cn/bmiddle/413394b1x70b115629845&690");
        noHarvestUrls.add("http://www.mlnews.gov.cn/uploadfile/2012/0413/20120413034930491.jpg");
        noHarvestUrls.add("http://s13.sinaimg.cn/mw690/7be74953gx6CjBbNzwoac&690");

        harvestingUrls.add("http://gl.lszxc.cn/uploads/allimg/140905/1-140Z5103340314.jpg");
        harvestingUrls.add("http://heilongjiang.sinaimg.cn/2014/1016/U10326P1274DT20141016135449.jpg");
        harvestingUrls.add("http://img.daimg.com/uploads/allimg/110722/3-110H2201505210.jpg");

        bannerDetail.setViewUrls(urls);
        bannerNoHarvest.setViewUrls(noHarvestUrls);
        bannerHarvesting.setViewUrls(harvestingUrls);
    }

    private void addLinstenr() {

    }
    @OnClick(R.id.ll_groupbuy_detail_title_back)
    public void onClick(){
        this.finish();
    }
}

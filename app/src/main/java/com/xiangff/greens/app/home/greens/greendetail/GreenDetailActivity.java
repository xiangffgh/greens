package com.xiangff.greens.app.home.greens.greendetail;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.xiangff.greens.app.R;
import com.xiangff.greens.app.data.Product;
import com.xiangff.greens.app.data.comment.Comment;
import com.xiangff.greens.app.home.greens.greendetail.adapter.GreenDetailCommentAdapter;
import com.yyydjk.library.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDetailActivity extends AppCompatActivity {

    private final static String TAG = "GreenDetailActivity";

    @BindView(R.id.ll_greens_back)
    LinearLayout back;
    @BindView(R.id.sv_green_detail_content)
    ScrollView svContent;

    @BindView(R.id.banner_green_detail)
    BannerLayout bannerLayout;


    @BindView(R.id.tv_green_detail_title)
    TextView tvTitle;
    @BindView(R.id.tv_green_detail_price)
    TextView tvPrice;
    @BindView(R.id.tv_green_detail_originPrice_sign)
    TextView tvOriginPrice;
    @BindView(R.id.tv_green_detail_discount)
    TextView tvDisscount;
    @BindView(R.id.tv_green_detail_description)
    TextView tvDescription;

    @BindView(R.id.rl_green_detail_telphone)
    RelativeLayout rlTelphone;
    @BindView(R.id.rl_green_detail_collection)
    RelativeLayout rlCollection;
    @BindView(R.id.rl_green_detail_car)
    RelativeLayout rlCar;

    @BindView(R.id.rcv_green_detail_comments)
    RecyclerView rcvComments;
    List<Comment> comments;
    int totalCount = 159;
    GreenDetailCommentAdapter commentAdapter;

    // 使用DisplayImageOptions.Builder()创建DisplayImageOptions
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_launcher) // 设置图片下载期间显示的图片
            .showImageForEmptyUri(R.mipmap.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片
            .showImageOnFail(R.mipmap.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片
            .cacheInMemory(false) // 设置下载的图片是否缓存在内存中
            .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
//    .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
            .build(); // 构建完成
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Product green = (Product) intent.getSerializableExtra("green");
        Log.i(TAG, green.toString());

        rcvComments.setFocusableInTouchMode(false);
        tvOriginPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        init();
    }

    private void init() {
        comments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Comment c = new Comment();
            c.setId(i + 1);
            c.setContent("不错" + i);
            c.setCreateTime("2016090" + i);
            c.setAuthorName("1396**" + i);
            c.setAuthorId(String.valueOf(i + 5));
            c.setBelongId(String.valueOf(i + 10));
            comments.add(c);
        }
        rcvComments.setLayoutManager(new LinearLayoutManager(this));
        commentAdapter = new GreenDetailCommentAdapter(this, comments, String.valueOf(totalCount));
        rcvComments.setAdapter(commentAdapter);

        /*
         *初始化图片数据
         */
        List<String> urls=new ArrayList<>();
        urls.add("http://img1.sc115.com/uploads/sc/jpg/HD/33/4090.jpg");
        urls.add("http://pic31.nipic.com/20130803/8821914_172601136000_2.jpg");
        urls.add("http://picapi.ooopic.com/10/18/46/78b1OOOPICd7.jpg");
        urls.add("http://pic32.nipic.com/20130827/8952533_150707601000_2.jpg");
        urls.add("http://img2.3lian.com/img2007/22/28/223.jpg");
        urls.add("http://picapi.ooopic.com/10/18/12/95b1OOOPIC89.jpg");
        bannerLayout.setViewUrls(urls);

        initListener();
    }

    private void initListener() {
        commentAdapter.setShowMoreListener(new GreenDetailCommentAdapter.ShowMoreListener() {
            @Override
            public void showMoreComment() {
                //查看更多评论
                Log.i(TAG, "查看更多评论");
            }
        });
    }

    @OnClick({R.id.ll_greens_back, R.id.rl_green_detail_telphone, R.id.rl_green_detail_collection, R.id.rl_green_detail_car})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_greens_back:
                this.finish();
                break;
            case R.id.rl_green_detail_telphone:

                break;
            case R.id.rl_green_detail_collection:

                break;
            case R.id.rl_green_detail_car:

                break;
        }
    }

}

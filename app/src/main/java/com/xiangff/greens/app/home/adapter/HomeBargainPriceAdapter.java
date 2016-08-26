package com.xiangff.greens.app.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.base.Optional;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xiangff.greens.app.R;
import com.xiangff.greens.app.data.Product;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by xiangff on 2016/8/25.
 */
public class HomeBargainPriceAdapter extends RecyclerView.Adapter<HomeBargainPriceViewHolder> {

    List<Product> products;
    LayoutInflater layoutInflater;

    public HomeBargainPriceAdapter(Context context, List<Product> products) {
        super();
        layoutInflater = LayoutInflater.from(context);
        if (products == null) {
            this.products = new ArrayList<Product>();
        } else {
            this.products = products;
        }

    }

    /**
     * 创建Holder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public HomeBargainPriceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_home_bargain, parent, false);
        HomeBargainPriceViewHolder viewHolder = new HomeBargainPriceViewHolder(view);
        return viewHolder;
    }

    /**
     * 数据绑定
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(HomeBargainPriceViewHolder holder, int position) {
        Product p = this.products.get(position);
        ImageLoader.getInstance().displayImage(p.getUrl(), holder.ivPic, options);
        if (TextUtils.isEmpty(p.getTitle())) p.setTitle("");
        holder.tvTitle.setText(p.getTitle());
        if (TextUtils.isEmpty(p.getPrice())) p.setPrice("0");
        if (TextUtils.isEmpty(p.getPriceUnit())) p.setPriceUnit("元");
        if (TextUtils.isEmpty(p.getWeigh())) p.setWeigh("0");
        if (TextUtils.isEmpty(p.getWeighUnit())) p.setWeighUnit("g");
        holder.tvPrice.setText("￥"+p.getPrice()+p.getPriceUnit()+"/");
        holder.tvWeigth.setText(p.getWeigh()+p.getWeighUnit());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    // 使用DisplayImageOptions.Builder()创建DisplayImageOptions
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_launcher) // 设置图片下载期间显示的图片
            .showImageForEmptyUri(R.mipmap.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片
            .showImageOnFail(R.mipmap.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片
            .cacheInMemory(false) // 设置下载的图片是否缓存在内存中
            .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
//    .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
            .build(); // 构建完成
}

package com.xiangff.greens.app.car.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xiangff.greens.app.R;
import com.xiangff.greens.app.data.car.Car;
import com.xiangff.greens.app.data.car.CarItem;

import org.w3c.dom.Text;

/**
 * Created by xiangff on 2016/8/30.
 */
public class CarAdatper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * 普通视图
     */
    private static final int VIEW_TYPE_ITEM = 1;
    /**
     * 底部总信息视图
     */
    private static final int VIEW_TYPE_FOOTER = 2;

    private LayoutInflater inflater;


    public CarAdatper(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = inflater.inflate(R.layout.item_car, parent, false);
            return new CarItemViewHolder(view);
        } else if (viewType == VIEW_TYPE_FOOTER) {
            View view = inflater.inflate(R.layout.item_car_footer, parent, false);
            return new FooterViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {//最后一条数据显示FooterView
            return VIEW_TYPE_FOOTER;
        }
        return VIEW_TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CarItemViewHolder) {

            CarItem carItem = Car.getInstance().getItems().get(position);
            CarItemViewHolder viewHolder = (CarItemViewHolder) holder;
            if (TextUtils.isEmpty(carItem.getProductTitle())) carItem.setProductTitle("");
            if (TextUtils.isEmpty(carItem.getProductPrice())) carItem.setProductPrice("");
            viewHolder.tvTitle.setText(carItem.getProductTitle());
            viewHolder.tvPrice.setText(carItem.getProductPrice());
            if (!TextUtils.isEmpty(carItem.getProductUrl()))
                ImageLoader.getInstance().displayImage(carItem.getProductUrl(), viewHolder.ivUrl);
        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            if (!TextUtils.isEmpty(Car.getInstance().getTotalPrice()))
                footerViewHolder.tvTotalPrice.setText(Car.getInstance().getTotalPrice());
            if (!TextUtils.isEmpty(Car.getInstance().getFreightPrice()))
                footerViewHolder.tvFreightPrice.setText(Car.getInstance().getFreightPrice());
        }
    }

    @Override
    public int getItemCount() {
        return Car.getInstance().getItemsSize() + 1;
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

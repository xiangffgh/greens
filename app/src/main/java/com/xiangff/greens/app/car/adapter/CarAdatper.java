package com.xiangff.greens.app.car.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xiangff.greens.app.R;
import com.xiangff.greens.app.data.car.Car;
import com.xiangff.greens.app.data.car.CarItem;
import com.xiangff.greens.app.view.NumberButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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
    private static final String TAG = "CarAdapter";

    private LayoutInflater inflater;

    private List<CarItem> toBeDeleted = new ArrayList<>();

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof CarItemViewHolder) {

            CarItem carItem = Car.getInstance().getItems().get(position);
            final CarItemViewHolder viewHolder = (CarItemViewHolder) holder;
            if (TextUtils.isEmpty(carItem.getProductTitle())) carItem.setProductTitle("");
            if (TextUtils.isEmpty(carItem.getProductPrice())) carItem.setProductPrice("");
            viewHolder.tvTitle.setText(carItem.getProductTitle());
            viewHolder.tvPrice.setText(carItem.getProductPrice());
            if (!TextUtils.isEmpty(carItem.getProductUrl()))
                ImageLoader.getInstance().displayImage(carItem.getProductUrl(), viewHolder.ivUrl);
            viewHolder.nb.setCurrentNumber(carItem.getItemNum());
            viewHolder.nb.setTag(position);
            viewHolder.nb.setTextChangeListener(new NumberButton.TextChangeListener() {
                @Override
                public void afterTextChanged(int position, int value) {
                    try {
                        if (carTotalValueChangeListener != null) {
                            carTotalValueChangeListener.carTotalValueChanged();
                        }
                    } catch (RuntimeException e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
                }
            });
            viewHolder.cb.setTag(position);
            CarItem item = Car.getInstance().getItems().get(position);
            if (toBeDeleted.contains(item)) {
                viewHolder.cb.setChecked(true);
            } else {
                viewHolder.cb.setChecked(false);
            }
            viewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int tag = (int) buttonView.getTag();
                    CarItem carItem = Car.getInstance().getItems().get(tag);
                    if (isChecked) {
                        if (!toBeDeleted.contains(carItem)) {
                            toBeDeleted.add(carItem);
                        }
                    } else {
                        if (toBeDeleted.contains(carItem)) {
                            toBeDeleted.remove(carItem);
                        }
                    }
                }
            });

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

    public List<CarItem> getToBeDeleted() {
        return toBeDeleted;
    }

    public List<CarItem> getDatas() {
        return Car.getInstance().getItems();
    }

    private CarTotalValueChangeListener carTotalValueChangeListener;

    public void setCarTotalValueChangeListener(CarTotalValueChangeListener carTotalValueChangeListener) {
        this.carTotalValueChangeListener = carTotalValueChangeListener;
    }

    public interface CarTotalValueChangeListener {
        public void carTotalValueChanged();
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

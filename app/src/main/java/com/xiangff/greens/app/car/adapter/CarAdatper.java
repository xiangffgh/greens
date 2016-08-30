package com.xiangff.greens.app.car.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiangff.greens.app.R;
import com.xiangff.greens.app.data.car.Car;

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
            //TODO
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
}

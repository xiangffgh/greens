package com.xiangff.greens.app.home.healthy.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiangff.greens.app.R;

/**
 * Created by xiangff on 2016/9/9.
 */
public class HealthyNormalViewHolder extends RecyclerView.ViewHolder {

    ImageView ivPic;
    TextView tvTitle;
    TextView tvDate;

    public HealthyNormalViewHolder(View itemView) {
        super(itemView);
        ivPic = (ImageView) itemView.findViewById(R.id.iv_item_healthy);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_item_healthy_title);
        tvDate = (TextView) itemView.findViewById(R.id.tv_item_healthy_date);
    }
}

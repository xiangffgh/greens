package com.xiangff.greens.app.car.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiangff.greens.app.R;

import ren.qinc.numberbutton.NumberButton;

/**
 *
 * Created by xiangff on 2016/8/30.
 */
public class CarItemViewHolder extends RecyclerView.ViewHolder{

    CheckBox cb;
    ImageView ivUrl;
    TextView tvTitle;
    TextView tvPrice;
    NumberButton nb;

    public CarItemViewHolder(View itemView) {
        super(itemView);
        cb= (CheckBox) itemView.findViewById(R.id.cb_item_car_check);
        ivUrl= (ImageView) itemView.findViewById(R.id.iv_item_car);
        tvTitle= (TextView) itemView.findViewById(R.id.tv_item_car_title);
        tvPrice= (TextView) itemView.findViewById(R.id.tv_item_car_price);
        nb= (NumberButton) itemView.findViewById(R.id.nb_item_car);
        nb.setCurrentNumber(1);
    }
}

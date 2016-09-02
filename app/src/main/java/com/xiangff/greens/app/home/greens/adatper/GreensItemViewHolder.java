package com.xiangff.greens.app.home.greens.adatper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiangff.greens.app.R;

/**
 * Created by xiangff on 2016/9/2.
 */
public class GreensItemViewHolder extends RecyclerView.ViewHolder {

    ImageView ivPic;
    TextView tvTitle;
    TextView tvPrice;
    TextView tvWeigth;
    ImageButton btnAdd;

    public GreensItemViewHolder(View itemView) {
        super(itemView);
        ivPic= (ImageView) itemView.findViewById(R.id.iv_item_home_bargain);
        tvTitle= (TextView) itemView.findViewById(R.id.tv_item_home_bargain_title);
        tvPrice= (TextView) itemView.findViewById(R.id.tv_item_home_bargain_price);
        tvWeigth= (TextView) itemView.findViewById(R.id.tv_item_home_bargain_price_weigth);
        btnAdd= (ImageButton) itemView.findViewById(R.id.btn_item_home_bargain_buy);
    }
}


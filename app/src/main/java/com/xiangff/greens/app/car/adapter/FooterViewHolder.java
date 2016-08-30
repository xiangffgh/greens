package com.xiangff.greens.app.car.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xiangff.greens.app.R;

/**
 *
 * Created by xiangff on 2016/8/30.
 */
public class FooterViewHolder extends RecyclerView.ViewHolder {

    TextView tvTotalPrice;
    TextView tvFreightPrice;

    public FooterViewHolder(View itemView) {
        super(itemView);
        tvTotalPrice = (TextView) itemView.findViewById(R.id.tv_item_car_footer_totalPrice);
        tvFreightPrice = (TextView) itemView.findViewById(R.id.tv_item_car_footer_freightPrice);
    }
}

package com.xiangff.greens.app.goupbuy.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiangff.greens.app.R;

/**
 *
 * Created by xiangff on 2016/8/29.
 */
public class GroupBuyViewHolder extends RecyclerView.ViewHolder {

    ImageView ivProduct;
    TextView tvTitle;
    TextView tvStatus;
    TextView tvAddress;
    TextView tvHarvestTime;
    TextView tvRemainingTime;
    TextView tvCurrentOrders;
    TextView tvGbUnitCurrentOrder;
    TextView tvTotalOrders;
    TextView tvGbUnitTotalOrder;
    TextView tvDifferentialPricing;
    TextView tvPrice;
    TextView tvPriceUnit;
    ImageButton btn;


    public GroupBuyViewHolder(View itemView) {
        super(itemView);
        ivProduct = (ImageView) itemView.findViewById(R.id.iv_item_group_buy);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_item_group_buy_title);
        tvStatus= (TextView) itemView.findViewById(R.id.tv_item_group_buy_status);
        tvAddress = (TextView) itemView.findViewById(R.id.tv_item_group_buy_address);
        tvHarvestTime = (TextView) itemView.findViewById(R.id.tv_item_group_buy_harvest_time);
        tvRemainingTime = (TextView) itemView.findViewById(R.id.tv_item_group_buy_remaining_time);
        tvCurrentOrders = (TextView) itemView.findViewById(R.id.tv_item_group_buy_current_order);
        tvGbUnitCurrentOrder = (TextView) itemView.findViewById(R.id.tv_item_group_buy_gbUnit_current_order);
        tvTotalOrders = (TextView) itemView.findViewById(R.id.tv_item_group_buy_total_order);
        tvGbUnitTotalOrder = (TextView) itemView.findViewById(R.id.tv_item_group_buy_gbUnit_total_order);
        tvDifferentialPricing = (TextView) itemView.findViewById(R.id.tv_item_group_buy_differential_pricing);
        tvPrice = (TextView) itemView.findViewById(R.id.tv_item_group_buy_price);
        tvPriceUnit = (TextView) itemView.findViewById(R.id.tv_item_group_buy_price_unit);
        btn = (ImageButton) itemView.findViewById(R.id.btn_item_group_buy);

    }

}

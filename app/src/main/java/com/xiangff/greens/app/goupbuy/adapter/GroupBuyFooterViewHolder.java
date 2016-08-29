package com.xiangff.greens.app.goupbuy.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xiangff.greens.app.R;

/**
 * Created by xiangff on 2016/8/29.
 */
public class GroupBuyFooterViewHolder  extends RecyclerView.ViewHolder {

    TextView tvLoadMore;

    public GroupBuyFooterViewHolder(View itemView) {
        super(itemView);
        tvLoadMore= (TextView) itemView.findViewById(R.id.tv_load_more);
    }
}

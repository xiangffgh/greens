package com.xiangff.greens.app.home.greens.adatper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xiangff.greens.app.R;

/**
 * Created by xiangff on 2016/9/2.
 */
public class GreensFooterViewHolder   extends RecyclerView.ViewHolder {

    TextView tvLoadMore;

    public GreensFooterViewHolder(View itemView) {
        super(itemView);
        tvLoadMore= (TextView) itemView.findViewById(R.id.tv_load_more);
    }
}

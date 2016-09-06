package com.xiangff.greens.app.home.greens.greendetail.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiangff.greens.app.R;

/**
 * Created by xiangff on 2016/9/6.
 */
public class GreenDetailCommentFooterViewHolder extends RecyclerView.ViewHolder {

    TextView tvMoreCount;
    RelativeLayout rlMore;

    public GreenDetailCommentFooterViewHolder(View itemView) {
        super(itemView);
        tvMoreCount= (TextView) itemView.findViewById(R.id.tv_item_green_detail_comment_footer_count);
        rlMore= (RelativeLayout) itemView.findViewById(R.id.rl_item_green_detail_comment_footer_more);
    }
}

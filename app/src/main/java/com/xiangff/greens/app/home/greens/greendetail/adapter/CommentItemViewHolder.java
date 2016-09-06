package com.xiangff.greens.app.home.greens.greendetail.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xiangff.greens.app.R;

/**
 * 评论 item ` ViewHolder
 * Created by xiangff on 2016/9/6.
 */
public class CommentItemViewHolder extends RecyclerView.ViewHolder{

     TextView tvContent;
     TextView tvName;
     TextView tvCreateTime;

    public CommentItemViewHolder(View itemView) {
        super(itemView);
        tvContent= (TextView) itemView.findViewById(R.id.tv_item_comment_content);
        tvName= (TextView) itemView.findViewById(R.id.tv_item_comment_author);
        tvCreateTime= (TextView) itemView.findViewById(R.id.tv_item_comment_create_time);
    }
}

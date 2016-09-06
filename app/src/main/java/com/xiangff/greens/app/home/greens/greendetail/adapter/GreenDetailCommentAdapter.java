package com.xiangff.greens.app.home.greens.greendetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiangff.greens.app.R;
import com.xiangff.greens.app.data.comment.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangff on 2016/9/6.
 */
public class GreenDetailCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG = "GreenDetailCommentAdapter";
    /**
     * 普通视图
     */
    private static final int VIEW_NORMAL_ITEM = 1;
    /**
     * 查看更多视图
     */
    private static final int VIEW_FOOTER_ITEM = 2;

    private List<Comment> comments;
    private String totalCount;
    private LayoutInflater inflater;

    public GreenDetailCommentAdapter(Context context, List<Comment> comments, String totalCount) {
        if (comments != null) {
            this.comments = comments;
        } else this.comments = new ArrayList<>();
        this.totalCount = totalCount;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return VIEW_FOOTER_ITEM;
        }
        return VIEW_NORMAL_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_NORMAL_ITEM) {
            View view = inflater.inflate(R.layout.item_comment, parent, false);
            CommentItemViewHolder viewHolder = new CommentItemViewHolder(view);
            return viewHolder;
        } else if (viewType == VIEW_FOOTER_ITEM) {
            View view = inflater.inflate(R.layout.item_green_detail_comment_footer, parent, false);
            GreenDetailCommentFooterViewHolder viewHolder = new GreenDetailCommentFooterViewHolder(view);
            return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommentItemViewHolder) {
            Comment comment = this.comments.get(position);
            CommentItemViewHolder viewHolder = (CommentItemViewHolder) holder;
            if (TextUtils.isEmpty(comment.getContent())) comment.setContent("");
            viewHolder.tvContent.setText(comment.getContent());
            if (TextUtils.isEmpty(comment.getAuthorName())) comment.setAuthorName("");
            viewHolder.tvName.setText(comment.getAuthorName());
            if (TextUtils.isEmpty(comment.getCreateTime())) comment.setCreateTime("");
            viewHolder.tvCreateTime.setText(comment.getCreateTime());

        } else if (holder instanceof GreenDetailCommentFooterViewHolder) {
            GreenDetailCommentFooterViewHolder viewHolder = (GreenDetailCommentFooterViewHolder) holder;
            if (TextUtils.isEmpty(totalCount)) totalCount = "0";
            viewHolder.tvMoreCount.setText(totalCount);
            viewHolder.rlMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (showMoreListener != null){
                        showMoreListener.showMoreComment();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (this.comments.size() == 0) {
            return 0;
        }
        return this.comments.size() + 1;
    }

    public void setDatas(List<Comment> comments) {
        if (comments != null) {
            this.comments = comments;
            this.notifyDataSetChanged();
        }
    }

    public void addDatas(List<Comment> comments) {
        if (comments != null) {
            this.comments.addAll(comments);
            this.notifyDataSetChanged();
            ;
        }
    }
    public List<Comment> getDatas(){
        return this.comments;
    }

    public interface ShowMoreListener{
        void showMoreComment();
    }
    private ShowMoreListener showMoreListener;

    public void setShowMoreListener(ShowMoreListener showMoreListener) {
        this.showMoreListener = showMoreListener;
    }
}

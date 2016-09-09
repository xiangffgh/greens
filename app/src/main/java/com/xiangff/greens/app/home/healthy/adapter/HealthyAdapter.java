package com.xiangff.greens.app.home.healthy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xiangff.greens.app.R;
import com.xiangff.greens.app.data.healthy.HealthyModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangff on 2016/9/9.
 */
public class HealthyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * 普通视图
     */
    private static final int VIEW_TYPE_ITEM = 1;
    /**
     * 上拉加载更多
     */
    private static final int VIEW_TYPE_FOOTER = 2;
    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载...
    public static final int ISLOADING = 1;
    //上拉加载的显示状态，初始为0
    private int load_more_status = 0;

    /**
     * 改变加载状态来显示不同的加载信息（上拉加载更多，加载中…）
     *
     * @param status
     */
    public void changeMoreStatus(int status) {
        load_more_status = status;
        notifyDataSetChanged();
    }

    List<HealthyModel> healthyModels;

    LayoutInflater inflater;

    public HealthyAdapter(Context context, List<HealthyModel> healthyModels) {
        if (healthyModels != null)
            this.healthyModels = healthyModels;
        else this.healthyModels = new ArrayList<>();

        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {//最后一条数据显示FooterView
            return VIEW_TYPE_FOOTER;
        }
        return VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = inflater.inflate(R.layout.item_healthy, parent, false);
            HealthyNormalViewHolder viewHolder = new HealthyNormalViewHolder(view);
            return viewHolder;
        } else if (viewType == VIEW_TYPE_FOOTER) {
            View view = inflater.inflate(R.layout.view_load_more, parent, false);
            HealthyFooterViewHolder viewHolder = new HealthyFooterViewHolder(view);
            return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HealthyNormalViewHolder) {
            HealthyNormalViewHolder viewHolder = (HealthyNormalViewHolder) holder;
            HealthyModel healthyModel = healthyModels.get(position);
            ImageLoader.getInstance().displayImage(healthyModel.getImgUrl(), viewHolder.ivPic);
            if (TextUtils.isEmpty(healthyModel.getTitle())) healthyModel.setTitle("");
            viewHolder.tvTitle.setText(healthyModel.getTitle());
            if (TextUtils.isEmpty(healthyModel.getCreateDate())) healthyModel.setCreateDate("");
            viewHolder.tvDate.setText(healthyModel.getCreateDate());

        } else if (holder instanceof HealthyFooterViewHolder) {
            HealthyFooterViewHolder viewHolder = (HealthyFooterViewHolder) holder;
            switch (load_more_status) {
                case PULLUP_LOAD_MORE:
                    viewHolder.tvLoadMore.setText("上拉加载更多");
                    break;
                case ISLOADING:
                    viewHolder.tvLoadMore.setText("正在加载...");
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (healthyModels.size() > 0) return healthyModels.size() + 1;
        return 0;
    }

    public void addDatas(List<HealthyModel> datas) {
        if (datas != null) {
            this.healthyModels.addAll(datas);
            this.notifyDataSetChanged();
        }
    }

    public void setDatas(List<HealthyModel> datas) {
        if (datas != null) {
            this.healthyModels = datas;
            this.notifyDataSetChanged();
        }

    }

    // 使用DisplayImageOptions.Builder()创建DisplayImageOptions
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_launcher) // 设置图片下载期间显示的图片
            .showImageForEmptyUri(R.mipmap.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片
            .showImageOnFail(R.mipmap.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片
            .cacheInMemory(false) // 设置下载的图片是否缓存在内存中
            .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
//    .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
            .build(); // 构建完成
}

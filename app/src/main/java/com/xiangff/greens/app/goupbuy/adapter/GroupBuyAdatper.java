package com.xiangff.greens.app.goupbuy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xiangff.greens.app.R;
import com.xiangff.greens.app.data.groupbuy.GBModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangff on 2016/8/29.
 */
public class GroupBuyAdatper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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

    List<GBModel> gbModels;
    LayoutInflater inflater;

    public GroupBuyAdatper(Context context, List<GBModel> gbModels) {
        if (gbModels == null) this.gbModels = new ArrayList<GBModel>();
        else this.gbModels = gbModels;
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
            View view = inflater.inflate(R.layout.item_group_buy, parent, false);
            GroupBuyViewHolder viewHolder = new GroupBuyViewHolder(view);
            return viewHolder;
        } else if (viewType == VIEW_TYPE_FOOTER) {
            View view=inflater.inflate(R.layout.view_load_more,parent,false);
            GroupBuyFooterViewHolder viewHolder=new GroupBuyFooterViewHolder(view);
            return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof GroupBuyViewHolder){
            final GroupBuyViewHolder holder= (GroupBuyViewHolder) viewHolder;
            GBModel gbModel = this.gbModels.get(position);
            if (!TextUtils.isEmpty(gbModel.getImgUrl()))
                ImageLoader.getInstance().displayImage(gbModel.getImgUrl(), holder.ivProduct, options);
            if (TextUtils.isEmpty(gbModel.getTitle())) gbModel.setTitle("");
            holder.tvTitle.setText(gbModel.getTitle());

            GBModel.GBStatus status = GBModel.GBStatus.NO_HARVEST;
            for (GBModel.GBStatus item :
                    GBModel.GBStatus.values()) {
                if (item.ordinal() == gbModel.getStatus())
                    status = item;
            }
            switch (status) {
                case NO_HARVEST:
                    holder.tvStatus.setText(GBModel.GBStatus.NO_HARVEST.getName());
                    break;
                case HARVESTING:
                    holder.tvStatus.setText(GBModel.GBStatus.HARVESTING.getName());
                    break;
                case CAR_GO_IN:
                    holder.tvStatus.setText(GBModel.GBStatus.CAR_GO_IN.getName());
                    break;
                case FINISHED:
                    holder.tvStatus.setText(GBModel.GBStatus.FINISHED.getName());
                    break;
            }

            if (TextUtils.isEmpty(gbModel.getAddress())) gbModel.setAddress("");
            holder.tvAddress.setText(gbModel.getAddress());
            if (TextUtils.isEmpty(gbModel.getHarvestTime())) gbModel.setHarvestTime("");
            holder.tvHarvestTime.setText(gbModel.getHarvestTime());
            if (TextUtils.isEmpty(gbModel.getRemainingTime())) gbModel.setRemainingTime("");
            holder.tvRemainingTime.setText(gbModel.getRemainingTime());
            if (TextUtils.isEmpty(gbModel.getCurrentOrders())) gbModel.setCurrentOrders("0");
            holder.tvCurrentOrders.setText(gbModel.getCurrentOrders());
            if (TextUtils.isEmpty(gbModel.getGbUnit())) gbModel.setGbUnit("");
            holder.tvGbUnitCurrentOrder.setText(gbModel.getGbUnit());
            if (TextUtils.isEmpty(gbModel.getTotalOrders())) gbModel.setTotalOrders("");
            holder.tvTotalOrders.setText(gbModel.getTotalOrders());
            holder.tvGbUnitTotalOrder.setText(gbModel.getGbUnit());
            if (TextUtils.isEmpty(gbModel.getDifferentialPricing())) gbModel.setDifferentialPricing("");
            holder.tvDifferentialPricing.setText(gbModel.getDifferentialPricing());
            if (TextUtils.isEmpty(gbModel.getGbPrice())) gbModel.setGbPrice("");
            holder.tvPrice.setText(gbModel.getGbPrice());
            holder.tvPriceUnit.setText(gbModel.getGbUnit());

            //绑定itemView的单击事件
            // 如果设置了回调，则设置点击事件
            if (mOnItemClickLitener != null)
            {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(holder.itemView, pos);
                    }
                });
            }

        }else if(viewHolder instanceof  GroupBuyFooterViewHolder){
            GroupBuyFooterViewHolder footer = (GroupBuyFooterViewHolder) viewHolder;
            switch(load_more_status){
                case PULLUP_LOAD_MORE:
                    footer.tvLoadMore.setText("上拉加载更多");
                    break;
                case ISLOADING:
                    footer.tvLoadMore.setText("正在加载...");
                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return this.gbModels.size() + 1;
    }
    public List<GBModel> getDatas(){
        return this.gbModels;
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

    /*
     * ==========点击事件=========
     */
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}

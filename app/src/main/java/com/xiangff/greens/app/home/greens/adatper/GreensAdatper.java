package com.xiangff.greens.app.home.greens.adatper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xiangff.greens.app.R;
import com.xiangff.greens.app.data.Product;
import com.xiangff.greens.app.goupbuy.adapter.GroupBuyFooterViewHolder;
import com.xiangff.greens.app.goupbuy.adapter.GroupBuyViewHolder;
import com.xiangff.greens.app.home.adapter.HomeBargainPriceViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by xiangff on 2016/9/2.
 */
public class GreensAdatper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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

    List<Product> products;
    LayoutInflater layoutInflater;

    public GreensAdatper(Context context, List<Product> products) {
        super();
        layoutInflater = LayoutInflater.from(context);
        if (products == null) {
            this.products = new ArrayList<Product>();
        } else {
            this.products = products;
        }

    }
    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {//最后一条数据显示FooterView
            return VIEW_TYPE_FOOTER;
        }
        return VIEW_TYPE_ITEM;
    }
    /**
     * 创建Holder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = layoutInflater.inflate(R.layout.item_home_bargain, parent, false);
            GreensItemViewHolder viewHolder = new GreensItemViewHolder(view);
            return viewHolder;
        } else if (viewType == VIEW_TYPE_FOOTER) {
            View view=layoutInflater.inflate(R.layout.view_load_more,parent,false);
            GreensFooterViewHolder viewHolder=new GreensFooterViewHolder(view);
            return viewHolder;
        }
        return null;
    }

    /**
     * 数据绑定
     *
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof GreensItemViewHolder) {
            GreensItemViewHolder holder = (GreensItemViewHolder) viewHolder;
            Product p = this.products.get(position);
            ImageLoader.getInstance().displayImage(p.getUrl(), holder.ivPic, options);
            if (TextUtils.isEmpty(p.getTitle())) p.setTitle("");
            holder.tvTitle.setText(p.getTitle());
            if (TextUtils.isEmpty(p.getPrice())) p.setPrice("0");
            if (TextUtils.isEmpty(p.getPriceUnit())) p.setPriceUnit("元");
            if (TextUtils.isEmpty(p.getWeigh())) p.setWeigh("0");
            if (TextUtils.isEmpty(p.getWeighUnit())) p.setWeighUnit("g");
            holder.tvPrice.setText("￥" + p.getPrice() + p.getPriceUnit() + "/");
            holder.tvWeigth.setText(p.getWeigh() + p.getWeighUnit());
        }else if(viewHolder instanceof GroupBuyFooterViewHolder){
            GreensFooterViewHolder footer = (GreensFooterViewHolder) viewHolder;
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
        return products.size()+1;
    }

    public void setDatas(List<Product> datas) {
        if (datas != null) {
            this.products = datas;
            notifyDataSetChanged();
        }
    }

    public void addDatas(List<Product> datas) {
        if (datas != null) {
            this.products.addAll(datas);
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

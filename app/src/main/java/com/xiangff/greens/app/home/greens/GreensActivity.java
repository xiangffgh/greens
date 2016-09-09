package com.xiangff.greens.app.home.greens;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wang.avi.AVLoadingIndicatorView;
import com.xiangff.greens.app.MainActivity;
import com.xiangff.greens.app.R;
import com.xiangff.greens.app.base.BasePresenter;
import com.xiangff.greens.app.data.Product;
import com.xiangff.greens.app.home.greens.adatper.GreensAdatper;
import com.xiangff.greens.app.home.greens.greendetail.GreenDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreensActivity extends AppCompatActivity implements GreensContract.View {
    private static final String TAG = "GreensActivity";

    /*=======视图组件=======*/
    @BindView(R.id.avi_greens)
    AVLoadingIndicatorView avi;
    @BindView(R.id.srl_greens)
    SwipeRefreshLayout srl;
    GridLayoutManager gridLayoutManager;
    @BindView(R.id.rv_greens)
    RecyclerView recyclerView;
    private List<Product> products;
    private GreensAdatper productAdatper;

    private BasePresenter presenter;

    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greens);
        ButterKnife.bind(this);

        initViews();
        initVP();
        initListener();
    }

    private void initVP() {
        presenter = new GreensPresenter(this);
    }

    private void initViews() {
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        products = new ArrayList<>();
        productAdatper = new GreensAdatper(this, products);
        recyclerView.setAdapter(productAdatper);
    }

    /**
     * 初始化监听器
     */
    private void initListener() {
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*下拉刷新数据*/
                if (presenter instanceof GreensPresenter) {
                    ((GreensPresenter) presenter).initDatas();
                    Log.i(TAG, "下拉刷新数据");
                }

            }
        });
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!srl.isRefreshing()) {
                    int lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
                    Log.i(TAG, "lastVisibleItem:" + lastVisibleItem);
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == productAdatper.getItemCount()) {
                        //调用Adapter里的changeMoreStatus方法来改变加载脚View的显示状态为：正在加载...
                        productAdatper.changeMoreStatus(productAdatper.ISLOADING);
                        /*加载更多数据*/
                        if (presenter instanceof GreensPresenter) {
                            ((GreensPresenter) presenter).loadMoreDatas();
                        }
                    }
                }
            }
        });
        productAdatper.setOnItemClickLitener(new GreensAdatper.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(GreensActivity.this, GreenDetailActivity.class);
                if (position < productAdatper.getDatas().size()) {
                    intent.putExtra("green", productAdatper.getDatas().get(position));
                    startActivity(intent);
                }
            }
        });
        productAdatper.setShopingCarListenerAnim(new GreensAdatper.ShopingCarAnimListener() {
            @Override
            public void showShopingCar(ImageView shopView) {

                addCart(shopView);
            }
        });
    }
    @OnClick({R.id.ll_greens_back,R.id.ib_greens_car})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_greens_back:
                this.finish();
                break;
            case R.id.ib_greens_car:
                Intent intent=new Intent(this,MainActivity.class);
                intent.putExtra(MainActivity.ToTab.Intent_Key,MainActivity.ToTab.Intent_Value.CAR);
                startActivity(intent);
                break;
        }
    }
    private boolean hasLoaded = false;

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void showLoadingIndicator() {
        avi.show();
    }

    @Override
    public void hideLoadingIndicator() {
        avi.hide();
    }
    @Override
    public void loadMoreFinished() {
        //当加载完数据后，再恢复加载脚View的显示状态为：上拉加载更多
        productAdatper.changeMoreStatus(productAdatper.PULLUP_LOAD_MORE);
    }

    @Override
    public void setDatas(List<Product> datas) {
        Log.i(TAG,"view setDatas");
        productAdatper.setDatas(datas);
        this.srl.setRefreshing(false);
        Log.i(TAG, "设置下拉刷新图片消失");
    }

    @Override
    public void addDatas(List<Product> datas) {
        productAdatper.addDatas(datas);
    }
    // ============购物移动效果相关=============
    //=============参考https://github.com/AndroidStudy2015/BezierCurveAnimater
    @BindView(R.id.rl_greens_content)
    RelativeLayout rl;//最外层视图
    @BindView(R.id.ib_greens_car)
    ImageView cart;
    private PathMeasure mPathMeasure;
    /**
     * 贝塞尔曲线中间过程的点的坐标
     */
    private float[] mCurrentPosition = new float[2];
    /**
     * ★★★★★把商品添加到购物车的动画效果★★★★★
     *
     * @param iv
     */

    private void addCart(ImageView iv) {
//      一、创造出执行动画的主题---imageview
        //代码new一个imageview，图片资源是上面的imageview的图片
        // (这个图片就是执行动画的图片，从开始位置出发，经过一个抛物线（贝塞尔曲线），移动到购物车里)
        final ImageView goods = new ImageView(this);
        // 设置移动的图片的图片
//        goods.setImageDrawable(iv.getDrawable());
        goods.setImageResource(R.mipmap.car);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);

        rl.addView(goods, params);

//        二、计算动画开始/结束点的坐标的准备工作
        //得到父布局的起始点坐标（用于辅助计算动画开始/结束时的点的坐标）
        int[] parentLocation = new int[2];
        rl.getLocationInWindow(parentLocation);

        //得到商品图片的坐标（用于计算动画开始的坐标）
        int startLoc[] = new int[2];
        iv.getLocationInWindow(startLoc);

        //得到购物车图片的坐标(用于计算动画结束后的坐标)
        int endLoc[] = new int[2];
        //设置移动图片最终的坐标
        cart.getLocationInWindow(endLoc);

//        三、正式开始计算动画开始/结束的坐标
        //开始掉落的商品的起始点：商品起始点-父布局起始点+该商品图片的一半
        float startX = startLoc[0] - parentLocation[0] + iv.getWidth() / 2;
        float startY = startLoc[1] - parentLocation[1] + iv.getHeight() / 2;

        //商品掉落后的终点坐标：购物车起始点-父布局起始点+购物车图片的1/5
        float toX = endLoc[0] - parentLocation[0] + cart.getWidth() / 5;
        float toY = endLoc[1] - parentLocation[1];

//        四、计算中间动画的插值坐标（贝塞尔曲线）（其实就是用贝塞尔曲线来完成起终点的过程）
        //开始绘制贝塞尔曲线
        Path path = new Path();
        //移动到起始点（贝塞尔曲线的起点）
        path.moveTo(startX, startY);
        //使用二次萨贝尔曲线：注意第一个起始坐标越大，贝塞尔曲线的横向距离就会越大，一般按照下面的式子取即可
        path.quadTo((startX + toX) / 2, startY, toX, toY);
        //mPathMeasure用来计算贝塞尔曲线的曲线长度和贝塞尔曲线中间插值的坐标，
        // 如果是true，path会形成一个闭环
        mPathMeasure = new PathMeasure(path, false);

        //★★★属性动画实现（从0到贝塞尔曲线的长度之间进行插值计算，获取中间过程的距离值）
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(1000);
        // 匀速线性插值器
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 当插值计算进行时，获取中间的每个值，
                // 这里这个值是中间过程中的曲线长度（下面根据这个值来得出中间点的坐标值）
                float value = (Float) animation.getAnimatedValue();
                // ★★★★★获取当前点坐标封装到mCurrentPosition
                // boolean getPosTan(float distance, float[] pos, float[] tan) ：
                // 传入一个距离distance(0<=distance<=getLength())，然后会计算当前距
                // 离的坐标点和切线，pos会自动填充上坐标，这个方法很重要。
                mPathMeasure.getPosTan(value, mCurrentPosition, null);//mCurrentPosition此时就是中间距离点的坐标值
                // 移动的商品图片（动画图片）的坐标设置为该中间点的坐标
                goods.setTranslationX(mCurrentPosition[0]);
                goods.setTranslationY(mCurrentPosition[1]);
            }
        });
//      五、 开始执行动画
        valueAnimator.start();

//      六、动画结束后的处理
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            //当动画结束后：
            @Override
            public void onAnimationEnd(Animator animation) {
                // 购物车的数量加1

                // 把移动的图片imageview从父布局里移除
                rl.removeView(goods);
                //添加购物车
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}

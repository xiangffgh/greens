package com.xiangff.greens.app.data.api;


import com.xiangff.greens.app.data.Product;
import com.xiangff.greens.app.data.adv.Adv;
import com.xiangff.greens.app.data.groupbuy.GBModel;
import com.xiangff.greens.app.data.healthy.HealthyModel;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 *
 * Created by xiangff on 2016/8/23.
 */
public interface ApiService {

    @GET("login.do")
    Observable<ResultModel> getLoginInfo(@Query("username") String username,@Query("password") String password);

    @GET("adv/advs.do")
    Observable<List<Adv>> getAdvs();

    @GET("groupbuy/gbs.do")
    Observable<List<GBModel>> getGbModels(@Query("currentPage") int currentPage);

    @GET("greens/list.do")
    Observable<List<Product>> getGreens(@Query("currentPage") int currentPage);

    @GET("healthy/list.do")
    Observable<List<HealthyModel>> getHealthies(@Query("currentPage") int currentPage);

}

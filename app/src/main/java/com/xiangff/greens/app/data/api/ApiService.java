package com.xiangff.greens.app.data.api;


import com.xiangff.greens.app.data.adv.Adv;

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
}

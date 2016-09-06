package com.xiangff.greens.app.data.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 用于向外提供数据的入口
 * <p/>
 * Created by xiangff on 2016/8/23.
 */
public class ApiServiceManager {            //http://10.0.2.2:8080
    private static final String ENDPOINT = "http://www.jccna.com/androidHtml5/";

    private ApiServiceManager() {
    }

    private static class ApiServiceManagerHolder {
        private static final ApiServiceManager instance = new ApiServiceManager();
    }

    public static final ApiServiceManager getInstance() {
        return ApiServiceManagerHolder.instance;
    }

    private ApiService apiService = null;

    public ApiService getApiService() {
        if (apiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }

}

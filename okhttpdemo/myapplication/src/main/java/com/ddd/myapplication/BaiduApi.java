package com.ddd.myapplication;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

//1，创建网络请求接口
public interface BaiduApi {
    @GET("qq")
    Call<Data> getBaidu(@QueryMap HashMap<String, String> params);
}

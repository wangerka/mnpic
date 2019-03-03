package com.ddd.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    //http://japi.juhe.cn/qqevaluate/qq?key=96efc220a4196fafdfade0c9d1e897ac&qq=917609824

    TextView  text ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);

        //2,创建retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://japi.juhe.cn/qqevaluate/") // 设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
//                .addConverterFactory(ScalarsConverterFactory.create()) // 设置数据解析器
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 支持RxJava平台
                .build();
        //3，网络请求实例
        BaiduApi api = retrofit.create(BaiduApi.class);
        HashMap<String, String> params = new HashMap<>();
        params.put("key","96efc220a4196fafdfade0c9d1e897ac");
        params.put("qq","917609824");
        Call<Data> result =  api.getBaidu(params);
        //4，发送请求
        result.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                try {
                    Log.i("gejun","1111"+response.body().toString());
                    text.setText(response.body().getResult().getData().getAnalysis());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("gejun",""+e.toString());
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.i("gejun","22222"+t.getMessage());
            }
        });
    }
}

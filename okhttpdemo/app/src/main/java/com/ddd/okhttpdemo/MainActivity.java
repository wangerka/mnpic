package com.ddd.okhttpdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    //https://github.com/wangerka/mnpic/blob/master/Screenshot_20190302-223042.png

    private ImageView iv_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_pic = findViewById(R.id.text);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://www.baidu.com/")//请求接口。如果需要传参拼接到接口后面。
                .build();//创建Request 对象
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){

                }
            }
        });
    }

    Handler h = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            Bitmap bitmap = msg.getData().getParcelable("bitmap");
            Bitmap bm = (Bitmap)msg.obj;
            Log.i("gejun",""+bm);
            iv_pic.setImageBitmap(bm);
        }
    };
}

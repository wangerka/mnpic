package com.ddd.mnpic;

import android.nfc.Tag;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ddd.mnpic.adapter.PicAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String url = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/30/";
    List<String> urls = new ArrayList<>();
    private static final String TAG = "gejun";

    private RecyclerView picListView;
    SwipeRefreshLayout refreshLayout;
    int currentPage = 1;
    PicAdapter adapte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        picListView = (RecyclerView)findViewById(R.id.recyclerview);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//解决item跳动
        picListView.setLayoutManager(manager);
        adapte = new PicAdapter(urls, MainActivity.this);
        picListView.setAdapter(adapte);

        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPics(url);
            }
        });

        getPics(url);

    }

    public void getPics(String url){
        url = url+currentPage;
        currentPage++;
        RequestQueue mQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            refreshLayout.setRefreshing(false);
                            urls.clear();
                            JSONArray pics = response.getJSONArray("results");
                            for(int i=0;i<pics.length();i++){
                                JSONObject pic = pics.getJSONObject(i);
                                String url = pic.getString("url");
                                Log.i(TAG,"url = " + url);
                                urls.add(url);
                            }
                            adapte.setPics(urls);
                            adapte.notifyDataSetChanged();
//                            PicAdapter adapte = new PicAdapter(urls, MainActivity.this);
//                            picListView.setAdapter(adapte);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        });
        mQueue.add(jsonObjectRequest);
    }
}

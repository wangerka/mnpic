package com.ddd.mnpic.presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ddd.mnpic.base.BasePresenter;
import com.ddd.mnpic.util.LogUtil;
import com.ddd.mnpic.view.MainBaseView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter extends BasePresenter<MainBaseView> {

    private int currentPage = 1;
    private List<String> urls = new ArrayList<>();

    public void getPics(Context context, String url){
        url = url+currentPage;
        currentPage++;
        RequestQueue mQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            view.setRefreshing(false);
                            urls.clear();
                            JSONArray pics = response.getJSONArray("results");
                            for(int i=0;i<pics.length();i++){
                                JSONObject pic = pics.getJSONObject(i);
                                String url = pic.getString("url");
                                Log.i(LogUtil.TAG,"url = " + url);
                                urls.add(url);
                            }
                            view.updateRecyclerView(urls);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LogUtil.TAG, error.getMessage(), error);
            }
        });
        mQueue.add(jsonObjectRequest);
    }
}

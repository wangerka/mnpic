package com.ddd.mnpic.util;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ddd.mnpic.bean.Pic;
import com.ddd.mnpic.bean.PicJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NetUitl {
    public static void parsePicsPaths(Context context, String url, final RequestListener listener){
        final List<String> urls = new ArrayList<>();
        RequestQueue mQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            urls.clear();
                            JSONArray pics = response.getJSONArray(PicJson.OBJECT_RESULTS);
                            for(int i=0;i<pics.length();i++){
                                JSONObject pic = pics.getJSONObject(i);
                                String url = pic.getString(Pic.OBJECT_URL);
                                Log.i(LogUtil.TAG,"url = " + url);
                                urls.add(url);
                            }
                            listener.onSuccess(urls);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LogUtil.TAG, error.getMessage(), error);
                listener.onError(error.getMessage());
            }
        });
        mQueue.add(jsonObjectRequest);
    }

    public interface RequestListener{
        void onSuccess(List<String> paths);
        void onError(String error);
    }
}

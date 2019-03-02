package com.ddd.mnpic.presenter;

import android.content.Context;
import android.util.Log;

import com.ddd.mnpic.base.BasePresenter;
import com.ddd.mnpic.util.LogUtil;
import com.ddd.mnpic.util.NetUitl;
import com.ddd.mnpic.view.MainBaseView;

import java.util.List;

public class MainPresenter extends BasePresenter<MainBaseView> {

    private int currentPage = 1;

    public void getPics(Context context, String url){
        url = url+currentPage;
        currentPage++;
        NetUitl.parsePicsPaths(context, url, new NetUitl.RequestListener() {
            @Override
            public void onSuccess(List<String> paths) {
                view.setRefreshing(false);
                view.updateRecyclerView(paths);
            }

            @Override
            public void onError(String error) {
                Log.i(LogUtil.TAG, error);
            }
        });
    }
}

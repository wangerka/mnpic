package com.ddd.mnpic;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.ddd.mnpic.adapter.PicAdapter;
import com.ddd.mnpic.bean.Picurl;
import com.ddd.mnpic.presenter.MainPresenter;
import com.ddd.mnpic.view.MainBaseView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainBaseView {

    List<String> urls = new ArrayList<>();
    private static final String TAG = "gejun";

    private RecyclerView picListView;
    SwipeRefreshLayout refreshLayout;

    PicAdapter adapte;

    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter();
        presenter.attachView(this);

        picListView = findViewById(R.id.recyclerview);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//解决item跳动
        picListView.setLayoutManager(manager);
        adapte = new PicAdapter(urls, MainActivity.this);
        picListView.setAdapter(adapte);
        showPic();

        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showPic();
            }
        });
    }

    private void showPic(){
        presenter.getPics(this,Picurl.URL);
    }

    @Override
    public void updateRecyclerView(List<String> paths) {
        adapte.setPics(paths);
        adapte.notifyDataSetChanged();
    }

    public void setRefreshing(boolean refreshing){
        refreshLayout.setRefreshing(refreshing);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detatch();
    }
}

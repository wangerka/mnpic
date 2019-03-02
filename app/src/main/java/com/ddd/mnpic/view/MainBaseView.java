package com.ddd.mnpic.view;

import com.ddd.mnpic.base.BaseView;

import java.util.List;

public interface MainBaseView extends BaseView {
    void updateRecyclerView(List<String> paths);
    void setRefreshing(boolean refreshing);
}

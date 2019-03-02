package com.ddd.mnpic.base;

public class BasePresenter<T extends BaseView> {

    public T view;

    public void attachView(T view){
        this.view = view;
    }

    public void detatch(){
        this.view = null;
    }
}

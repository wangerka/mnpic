package com.ddd.myapplication;

public class Result {
    Content data;

    public Content getData() {
        return data;
    }

    public void setData(Content data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data.toString() +
                '}';
    }
}

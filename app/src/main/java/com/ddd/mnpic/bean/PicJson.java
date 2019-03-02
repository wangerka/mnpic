package com.ddd.mnpic.bean;

public class PicJson {
    String error;
    Pic[] pics;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Pic[] getPics() {
        return pics;
    }

    public void setPics(Pic[] pics) {
        this.pics = pics;
    }
}

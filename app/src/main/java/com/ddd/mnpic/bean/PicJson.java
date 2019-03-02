package com.ddd.mnpic.bean;

public class PicJson {
    public static final String OBJECT_RESULTS = "results";

    private String error;
    private Pic[] pics;

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

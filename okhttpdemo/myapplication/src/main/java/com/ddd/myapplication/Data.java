package com.ddd.myapplication;

public class Data {
    int error_code;
    String reason;
    Result result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Data{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", result=" + result.toString() +
                '}';
    }
}

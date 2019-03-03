package com.ddd.myapplication;

public class Content {
    String conclusion;
    String analysis;

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    @Override
    public String toString() {
        return "Content{" +
                "conclusion='" + conclusion + '\'' +
                ", analysis='" + analysis + '\'' +
                '}';
    }
}

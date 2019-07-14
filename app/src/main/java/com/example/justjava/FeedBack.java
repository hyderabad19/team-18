package com.example.justjava;

public class FeedBack {
    String issue;
    String improve;
    float feed;

    public  FeedBack(){

    }

    public FeedBack(String issue,String improve,float feed)
    {
        this.issue=issue;
        this.improve=improve;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getImprove() {
        return improve;
    }

    public void setImprove(String improve) {
        this.improve = improve;
    }
}

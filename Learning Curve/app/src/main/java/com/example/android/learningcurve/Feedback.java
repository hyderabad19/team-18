package com.example.android.learningcurve;

public class Feedback {
    String issue;
    String improve;
    float feed;

    public  Feedback(){

    }

    public Feedback(String issue,String improve,float feed)
    {
        this.issue=issue;
        this.improve=improve;
        this.feed=feed;
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

    public float getFeed() {
        return feed;
    }

    public void setFeed(float feed) {
        this.feed = feed;
    }
}
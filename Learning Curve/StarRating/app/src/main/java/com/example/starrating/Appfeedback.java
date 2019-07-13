package com.example.starrating;

public class Appfeedback {
    float stars;
    String comments;
    public Appfeedback(float star,String feedback)
    {
        this.stars=star;
        this.comments=feedback;
    }
    public float getRatings()
    {
        return stars;
    }
    public void setRatings(float ratings)
    {
        this.stars=ratings;
    }
    public String getfeedback()
    {
        return comments;
    }
    public void setfeedback(String comment){
        this.comments=comment;
    }

}

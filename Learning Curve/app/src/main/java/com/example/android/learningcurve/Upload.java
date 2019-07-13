package com.example.android.learningcurve;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Upload {
    public String name;
    public String url;
    public int likes;
    private int id;
    private ArrayList<String> comments;

    public Upload(String name, String url, int likes, int id, ArrayList<String> comments) {
        this.name = name;
        this.url = url;
        this.likes = likes;
        this.id = id;
        this.comments = comments;
    }

    public Upload() {
    }

    public Upload(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

package com.example.android.learningcurve;

public class Upload {
    public String name;
    public String url;

    public Upload() {
    }

    public Upload(String name, String url) {
        this.name = name;
        this.url= url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}

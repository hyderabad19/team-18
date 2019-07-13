package com.example.android.learningcurve.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.android.learningcurve.ContentData.Content;

public class ContentAdapter extends ArrayAdapter<Content> {
    public ContentAdapter(Context context, int resource) {
        super(context, resource);
    }
}

package com.example.android.learningcurve.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.learningcurve.R;
import com.example.android.learningcurve.Upload;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {
    String url;
    private Context context;
    private List<Upload> uploads;
    private ArrayList<String> videotypes = new ArrayList<>(Arrays.asList("mov", "flv", "avi", "mkv", "mp4"));
    private ArrayList<String> imgtypes = new ArrayList<>(Arrays.asList("tiff", "jpeg", "gif", "png", "jpg"));

    public ContentAdapter(Context context, List<Upload> uploads) {
        this.uploads = uploads;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);



        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Upload upload = uploads.get(position);


        int image = 0;
        holder.link.setText(upload.getName());
        String x = upload.getUrl();
        Log.d("url",x);
        for (int i = 0; i < imgtypes.size(); i++) {
            if (x.contains(imgtypes.get(i))) {
                image = 1;
                break;
            }
        }

        if (image == 1) {
            Glide.with(context).load(Uri.parse(upload.getUrl())).into(holder.image);
        } else {
            holder.image.setVisibility(View.GONE);
            holder.link.setText(x);
        }
    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView link;
        public ImageView image;
        public Button comment;
        public Button like;
        public Button share;

        public ViewHolder(View itemView) {
            super(itemView);

            link = (TextView) itemView.findViewById(R.id.link);
            image = (ImageView) itemView.findViewById(R.id.image);

            comment = (Button) itemView.findViewById(R.id.comment);
            share = (Button) itemView.findViewById(R.id.share);
            like = (Button) itemView.findViewById(R.id.like);

        }
    }
}

package com.sesikova.android.kanjicard.Adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sesikova.android.kanjicard.R;

public class TopicViewHolder extends RecyclerView.ViewHolder{

    public TextView topicName;
    public TextView topicNameJapan;

    public ImageView topicImage;

    public View itemView;

    public TopicViewHolder(View itemView) {
        super(itemView);

        this.itemView = itemView;
        topicName = (TextView)itemView.findViewById(R.id.topic_name);
        topicNameJapan = (TextView)itemView.findViewById(R.id.topic_name_japan);
        topicImage = (ImageView)itemView.findViewById(R.id.topic_image);
    }
}

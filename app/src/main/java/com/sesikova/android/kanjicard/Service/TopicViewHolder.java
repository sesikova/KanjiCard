package com.sesikova.android.kanjicard.Service;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sesikova.android.kanjicard.R;

public class TopicViewHolder extends RecyclerView.ViewHolder{

    public TextView topicKanjiTextView;
    public TextView topicEnglishTextView;
    public ImageView topicImageView;

    public View topicView;

    public TopicViewHolder(View itemView) {
        super(itemView);

        topicView = itemView;
        topicKanjiTextView = (TextView)itemView.findViewById(R.id.topicKanjiTextView);
        topicEnglishTextView = (TextView)itemView.findViewById(R.id.topicEnglishTextView);
        topicImageView = (ImageView)itemView.findViewById(R.id.topicImageView);
    }
}

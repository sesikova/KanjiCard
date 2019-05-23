package com.sesikova.android.kanjicard.Service;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sesikova.android.kanjicard.R;


public class ReportViewHolder extends RecyclerView.ViewHolder{

    public TextView cardIndexTextView;
    public TextView kanjiTextView;
    public TextView englishTextView;
    public ImageView markUserImageView;
    public TextView englishUserTextView;




    public ReportViewHolder(View itemView) {
        super(itemView);
        cardIndexTextView = (TextView)itemView.findViewById(R.id.cardIndexTextView);
        kanjiTextView = (TextView)itemView.findViewById(R.id.kanjiTextView);
        englishTextView = (TextView)itemView.findViewById(R.id.englishTextView);
        englishUserTextView = (TextView)itemView.findViewById(R.id.englishUserTextView);
        markUserImageView = (ImageView)itemView.findViewById(R.id.markUserImageView);
    }
}

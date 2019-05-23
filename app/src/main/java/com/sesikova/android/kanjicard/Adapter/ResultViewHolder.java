package com.sesikova.android.kanjicard.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sesikova.android.kanjicard.R;


public class ResultViewHolder extends RecyclerView.ViewHolder{

    public ImageView imageMark;

    public TextView quizIndex;

    public TextView mainQuestion;

    public TextView yourAnswer;

    public TextView correctAnswer;


    public ResultViewHolder(View itemView) {
        super(itemView);

        imageMark = (ImageView)itemView.findViewById(R.id.question_icon);
        quizIndex = (TextView)itemView.findViewById(R.id.quiz_index);
        mainQuestion = (TextView)itemView.findViewById(R.id.real_question);
        yourAnswer = (TextView)itemView.findViewById(R.id.your_answer);
        correctAnswer = (TextView)itemView.findViewById(R.id.correct_answer);
    }
}
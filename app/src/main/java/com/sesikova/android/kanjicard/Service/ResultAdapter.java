package com.sesikova.android.kanjicard.Service;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sesikova.android.kanjicard.R;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder>{
    private Context context;
    private List<Card> cardList;


    public ResultAdapter(Context context, List<Card> cardList) {
        this.context = context;
        this.cardList = cardList;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report, parent, false);
        ResultViewHolder quizResultHolder = new ResultViewHolder(layoutView);
        return quizResultHolder;
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        Card card = cardList.get(position);

        if(card != null){
            holder.quizIndex.setText("CARD : " + card.getInfo("Index"));
            holder.mainQuestion.setText(card.getInfo("kanji"));
            holder.correctAnswer.setText("* " + card.getInfo("english") + " *");
            holder.yourAnswer.setText("Your answer: " + card.getInfo("englishUser"));

            if(Boolean.valueOf(card.getInfo("markUser"))){
                holder.imageMark.setImageResource(R.drawable.goodmark);
            }else{
                holder.imageMark.setImageResource(R.drawable.badmark);
            }
        }else{
            Toast.makeText(context, "Error!! Quiz result report missing", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}

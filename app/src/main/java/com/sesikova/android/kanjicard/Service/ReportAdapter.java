package com.sesikova.android.kanjicard.Service;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sesikova.android.kanjicard.R;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportViewHolder>{
    private Context context;
    private List<Card> cardList;


    public ReportAdapter(Context context, List<Card> cardList) {
        this.context = context;
        this.cardList = cardList;
    }

    @Override
    public ReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report, parent, false);
        ReportViewHolder quizResultHolder = new ReportViewHolder(layoutView);
        return quizResultHolder;
    }

    @Override
    public void onBindViewHolder(ReportViewHolder holder, int position) {
        Card card = cardList.get(position);

        if(card != null){
            holder.cardIndexTextView.setText("CARD : " + card.getInfo("id"));
            holder.kanjiTextView.setText(card.getInfo("kanji"));
            holder.englishTextView.setText("* " + card.getInfo("english") + " *");
            holder.englishUserTextView.setText("YOU : " + card.getInfo("englishUser"));

            if(Boolean.valueOf(card.getInfo("markUser"))){
                holder.markUserImageView.setImageResource(R.drawable.goodmark);
            }else{
                holder.markUserImageView.setImageResource(R.drawable.badmark);
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

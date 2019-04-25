package com.sesikova.android.nihongoquiz.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;


import com.sesikova.android.nihongoquiz.R;
import com.sesikova.android.nihongoquiz.QuizActivity;
import com.sesikova.android.nihongoquiz.Entity.TopicObject;


public class TopicAdapter extends RecyclerView.Adapter<TopicViewHolder>{

    private Context context;
    private List<TopicObject> topicObjectList;


    public TopicAdapter(Context context, List<TopicObject> topicObjectList) {
        this.context = context;
        this.topicObjectList =topicObjectList;
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic, parent, false);
        TopicViewHolder quizCategoryHolder = new TopicViewHolder(layoutView);
        return quizCategoryHolder;
    }

    @Override
    public void onBindViewHolder(final TopicViewHolder holder, int position) {
        TopicObject topicObject = topicObjectList.get(position);
        final String topicName = topicObjectList.get(position).getTopicName();
        final String topicNameJapan = topicObjectList.get(position).getTopicNameJapan();
        final int id = topicObject.getTopicId();

        holder.topicName.setText(topicName);
        holder.topicNameJapan.setText(topicNameJapan);
        int imageResource = getResourseId(context, topicObject.getTopicImagePath(), "drawable", context.getPackageName());
        holder.topicImage.setImageResource(imageResource);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quizTypeIntent = new Intent(context, QuizActivity.class);
                quizTypeIntent.putExtra("TOPIC_NAME", topicName);
                quizTypeIntent.putExtra("TOPIC_ID", id);
                context.startActivity(quizTypeIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topicObjectList.size();
    }

    public static int getResourseId(Context context, String pVariableName, String pResourcename, String pPackageName) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            throw new RuntimeException("Error getting Resource ID.", e);
        }
    }
}

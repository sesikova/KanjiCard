package com.sesikova.android.kanjicard.Service;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;


import com.sesikova.android.kanjicard.R;
import com.sesikova.android.kanjicard.CardActivity;


public class TopicAdapter extends RecyclerView.Adapter<TopicViewHolder>{

    private Context context;
    private List<Topic> topicList;


    public TopicAdapter(Context context, List<Topic> topicList) {
        this.context = context;
        this.topicList =topicList;
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic, parent, false);
        TopicViewHolder topicViewHolder = new TopicViewHolder(layoutView);
        return topicViewHolder;
    }

    @Override
    public void onBindViewHolder(final TopicViewHolder topicViewHolder, int position) {
        Topic topic = topicList.get(position);

        final int id = topic.getTopicId();
        final String kanji = topic.getTopicNameJapan();
        final String english = topic.getTopicName();


        topicViewHolder.topicKanjiTextView.setText(kanji);
        topicViewHolder.topicEnglishTextView.setText(english);
        int imageResource = getResourseId(context, topic.getTopicImagePath(), "drawable", context.getPackageName());
        topicViewHolder.topicImageView.setImageResource(imageResource);

        topicViewHolder.topicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent topicIntent = new Intent(context, CardActivity.class);
                topicIntent.putExtra("TOPIC_NAME", english);
                topicIntent.putExtra("TOPIC_ID",id);
                context.startActivity(topicIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    public static int getResourseId(Context context, String pVariableName, String pResourcename, String pPackageName) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            throw new RuntimeException("Error getting Resource ID.", e);
        }
    }
}

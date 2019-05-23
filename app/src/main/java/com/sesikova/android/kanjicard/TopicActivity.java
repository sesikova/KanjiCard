package com.sesikova.android.kanjicard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;

import com.sesikova.android.kanjicard.Service.TopicAdapter;
import com.sesikova.android.kanjicard.Service.DataBaseQuery;
import com.sesikova.android.kanjicard.Service.Topic;



public class TopicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        setTitle(getString(R.string.topic));

        DataBaseQuery dbQuery = new DataBaseQuery(TopicActivity.this);
        List<Topic> topicList = dbQuery.getTopicList();

        RecyclerView topicRecyclerView = (RecyclerView)findViewById(R.id.topic_list);
        GridLayoutManager mGrid = new GridLayoutManager(this, 2);
        topicRecyclerView.setLayoutManager(mGrid);
        topicRecyclerView.setHasFixedSize(true);

        TopicAdapter topicAdapter = new TopicAdapter(TopicActivity.this, topicList);
        topicRecyclerView.setAdapter(topicAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent menuActivityIntent = new Intent(TopicActivity.this, MenuActivity.class);
        startActivity(menuActivityIntent);
    }
}

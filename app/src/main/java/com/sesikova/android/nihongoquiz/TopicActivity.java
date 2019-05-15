package com.sesikova.android.nihongoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;

import com.sesikova.android.nihongoquiz.Adapter.TopicAdapter;
import com.sesikova.android.nihongoquiz.DataBase.DataBaseQuery;
import com.sesikova.android.nihongoquiz.Entity.TopicObject;



public class TopicActivity extends AppCompatActivity {

    private static final String TAG = TopicActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        setTitle(getString(R.string.quiz_topic));

        DataBaseQuery dbQuery = new DataBaseQuery(TopicActivity.this);
        List<TopicObject> topicList = dbQuery.getTopicList();

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

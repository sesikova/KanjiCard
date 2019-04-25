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
        List<TopicObject> categoryData = dbQuery.getTopicList();

        RecyclerView quizRecyclerView = (RecyclerView)findViewById(R.id.quiz_topic);
        GridLayoutManager mGrid = new GridLayoutManager(this, 2);
        quizRecyclerView.setLayoutManager(mGrid);
        quizRecyclerView.setHasFixedSize(true);

        TopicAdapter mAdapter = new TopicAdapter(TopicActivity.this, categoryData);
        quizRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent backIntent = new Intent(TopicActivity.this, MenuActivity.class);
        startActivity(backIntent);
    }
}

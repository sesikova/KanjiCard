package com.sesikova.android.kanjicard;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sesikova.android.kanjicard.Service.ResultAdapter;
import com.sesikova.android.kanjicard.Service.Card;
import com.sesikova.android.kanjicard.Service.ScoreObject;

import java.util.List;

public class ReportActivity extends AppCompatActivity {

    private RecyclerView reportRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        setTitle(getString(R.string.reportTitle));

        reportRecyclerView = (RecyclerView)findViewById(R.id.reportRecyclerView);

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ReportActivity.this);
        reportRecyclerView.setLayoutManager(new LinearLayoutManager(ReportActivity.this));
        reportRecyclerView.setHasFixedSize(true);

        String mQuizResult = getIntent().getExtras().getString("RESULT_OBJECT");
        //GsonBuilder builder = new GsonBuilder();
        //Gson gson = new GsonBuilder().create();
        ScoreObject score = new GsonBuilder().create().fromJson(mQuizResult, ScoreObject.class);
        List<Card> cardList = score.getCardList();

        ResultAdapter resultAdapter = new ResultAdapter(ReportActivity.this, cardList);
        reportRecyclerView.setAdapter(resultAdapter);
    }
}

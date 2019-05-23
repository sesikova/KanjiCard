package com.sesikova.android.kanjicard.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.GsonBuilder;
import com.sesikova.android.kanjicard.R;
import com.sesikova.android.kanjicard.Service.ReportAdapter;
import com.sesikova.android.kanjicard.Service.Card;
import com.sesikova.android.kanjicard.Service.Outcome;

import java.util.List;

public class ReportActivity extends AppCompatActivity {

    private RecyclerView reportRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        setTitle(getString(R.string.report_title));

        reportRecyclerView = (RecyclerView)findViewById(R.id.reportRecyclerView);

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ReportActivity.this);
        reportRecyclerView.setLayoutManager(new LinearLayoutManager(ReportActivity.this));
        reportRecyclerView.setHasFixedSize(true);

        String outcomeJson = getIntent().getExtras().getString("Outcome");
        //GsonBuilder builder = new GsonBuilder();
        //Gson gson = new GsonBuilder().create();
        Outcome outcome = new GsonBuilder().create().fromJson(outcomeJson, Outcome.class);
        List<Card> cardList = outcome.getCardList();

        ReportAdapter resultAdapter = new ReportAdapter(ReportActivity.this, cardList);
        reportRecyclerView.setAdapter(resultAdapter);
    }
}

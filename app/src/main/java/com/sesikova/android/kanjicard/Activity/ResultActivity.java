package com.sesikova.android.kanjicard.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.pavlospt.CircleView;
import com.google.gson.GsonBuilder;
import com.sesikova.android.kanjicard.R;
import com.sesikova.android.kanjicard.Service.Card;
import com.sesikova.android.kanjicard.Service.Outcome;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private String outcomeJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setTitle(getString(R.string.result_title));

        outcomeJson = getIntent().getExtras().getString("OUTCOME");
        Outcome outcome = new GsonBuilder().create().fromJson(outcomeJson, Outcome.class);

        int MarkGoodCount = outcome.getMarkGoodCount();
        int MarkAllCount = outcome.getMarkAllCount();

        CircleView resultCircleView = (CircleView)findViewById(R.id.result_info);
        resultCircleView.setTitleText((MarkGoodCount * 100) / MarkAllCount + "%");
        resultCircleView.setSubtitleText(MarkGoodCount+" of "+MarkAllCount);

        Button restertButton = (Button)findViewById(R.id.restartButton);



        restertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultActivityIntent = new Intent(ResultActivity.this, TopicActivity.class);
                startActivity(resultActivityIntent);
            }
        });

        Button reportButton = (Button)findViewById(R.id.reportButton);



        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultActivityIntent = new Intent(ResultActivity.this, ReportActivity.class);
                resultActivityIntent.putExtra("Outcome", outcomeJson);
                startActivity(resultActivityIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}

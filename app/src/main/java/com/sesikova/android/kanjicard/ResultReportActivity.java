package com.sesikova.android.kanjicard;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sesikova.android.kanjicard.Adapter.ResultAdapter;
import com.sesikova.android.kanjicard.Entity.ResultObject;
import com.sesikova.android.kanjicard.Entity.ScoreObject;

import java.util.List;

public class ResultReportActivity extends AppCompatActivity {

    private static final String TAG = ResultReportActivity.class.getSimpleName();

    private RecyclerView resultRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_report);

        setTitle(getString(R.string.result_report));

        resultRecyclerView = (RecyclerView)findViewById(R.id.result_report);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ResultReportActivity.this);
        resultRecyclerView.setLayoutManager(linearLayoutManager);
        resultRecyclerView.setHasFixedSize(true);

        String mQuizResult = getIntent().getExtras().getString("RESULT_OBJECT");
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        ScoreObject storedResult = gson.fromJson(mQuizResult, ScoreObject.class);
        List<ResultObject> getStoredResults = storedResult.getResultList();
        if(getStoredResults != null){
            Log.d(TAG, " Result score " + getStoredResults.size());
        }

        ResultAdapter resultAdapter = new ResultAdapter(ResultReportActivity.this, getStoredResults);
        resultRecyclerView.setAdapter(resultAdapter);
    }
}

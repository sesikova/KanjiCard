package com.sesikova.android.nihongoquiz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.pavlospt.CircleView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setTitle(getString(R.string.quiz_result));

        final String resultObject = getIntent().getExtras().getString("RESULT_OBJECT");
        int quizScore = Integer.parseInt(getIntent().getExtras().getString("QUIZ_SCORE"));
        int quizCount = Integer.parseInt(getIntent().getExtras().getString("QUIZ_COUNT"));



        CircleView resultCircleView = (CircleView)findViewById(R.id.result_info);
        resultCircleView.setTitleText((quizScore * 100) / quizCount + "%");
        resultCircleView.setSubtitleText(quizScore+" of "+quizCount);

        Button retakeQuizButton = (Button)findViewById(R.id.retake_quiz);
        assert retakeQuizButton != null;
        retakeQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultActivityIntent = new Intent(ResultActivity.this, TopicActivity.class);
                startActivity(resultActivityIntent);
            }
        });

        Button viewQuizResultButton = (Button)findViewById(R.id.view_result);
        assert viewQuizResultButton != null;
        viewQuizResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultActivityIntent = new Intent(ResultActivity.this, ResultReportActivity.class);
                resultActivityIntent.putExtra("RESULT_OBJECT", resultObject);
                startActivity(resultActivityIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}

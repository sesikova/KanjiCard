package com.sesikova.android.nihongoquiz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar = getSupportActionBar();
        if(null != actionBar){
            actionBar.hide();
        }

        Button selectQuiz = (Button)findViewById(R.id.topic_button);
        assert selectQuiz != null;
        selectQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent topicIntent = new Intent(MenuActivity.this, TopicActivity.class);
                startActivity(topicIntent);
            }
        });


        Button quizInstruction = (Button)findViewById(R.id.instruction_button);
        assert quizInstruction != null;
        quizInstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent instructionIntent = new Intent(MenuActivity.this, InstructionActivity.class);
                startActivity(instructionIntent);
            }
        });

        Button quizAbout = (Button)findViewById(R.id.about_button);
        assert quizAbout != null;
        quizAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aboutIntent = new Intent(MenuActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
            }
        });

    }
}

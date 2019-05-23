package com.sesikova.android.kanjicard.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sesikova.android.kanjicard.R;
import com.sesikova.android.kanjicard.Service.Information;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setTitle(getString(R.string.about));

        TextView instructionText = (TextView)findViewById(R.id.about);
        instructionText.setText(Information.about);
    }
}
package com.sesikova.android.nihongoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sesikova.android.nihongoquiz.Helper.Information;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setTitle(getString(R.string.information_notice));

        TextView instructionText = (TextView)findViewById(R.id.instruction);
        instructionText.setText(Information.about);
    }
}
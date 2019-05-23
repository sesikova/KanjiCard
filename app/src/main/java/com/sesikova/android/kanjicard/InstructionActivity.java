package com.sesikova.android.kanjicard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sesikova.android.kanjicard.Service.Information;

public class InstructionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        setTitle(getString(R.string.instruction));

        TextView instructionText = (TextView)findViewById(R.id.instruction);
        instructionText.setText(Information.instruction);
    }
}
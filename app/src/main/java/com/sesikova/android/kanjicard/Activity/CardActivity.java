package com.sesikova.android.kanjicard.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sesikova.android.kanjicard.R;
import com.sesikova.android.kanjicard.Service.DataBaseQuery;
import com.sesikova.android.kanjicard.Service.Card;
import com.sesikova.android.kanjicard.Service.Outcome;

import java.util.List;

public class CardActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView cardIndexTextView, kanjiTextView;
    private RadioGroup variantRadioGroup;
    private RadioButton variant1RadioButton,variant2RadioButton,variant3RadioButton,variant4RadioButton;
    private Button nextCardButton;

    private Outcome outcome;
    private Card card;
    private List<Card> cardList;
    private int cardCount;
    private int cardIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        int topicId = getIntent().getExtras().getInt("TOPIC_ID");
        setTitle("Card : " + getIntent().getExtras().getString("TOPIC_NAME"));

        cardIndexTextView = (TextView)findViewById(R.id.cardIndexTextView);
        kanjiTextView = (TextView)findViewById(R.id.kanjiTextView);
        variantRadioGroup = (RadioGroup)findViewById(R.id.variantRadioGroup);
        variant1RadioButton = (RadioButton)findViewById(R.id.variant1RadioButton);
        variant2RadioButton = (RadioButton)findViewById(R.id.variant2RadioButton);
        variant3RadioButton = (RadioButton)findViewById(R.id.variant3RadioButton);
        variant4RadioButton = (RadioButton)findViewById(R.id.variant4RadioButton);
        nextCardButton = (Button)findViewById(R.id.nextCardButton);

        outcome = new Outcome();
        cardList = new DataBaseQuery(CardActivity.this).getCardList(topicId);

        if(cardList.size() != 0){
            cardCount = cardList.size();
            outcome.setMarkAllCount(cardCount);
            card = cardList.get(cardIndex);
            viewCards();
        }
        else{
            variantRadioGroup.setVisibility(View.GONE);
            nextCardButton.setVisibility(View.GONE);
            Toast.makeText(CardActivity.this, getString(R.string.noCard_message), Toast.LENGTH_LONG).show();
        }

        nextCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioButtonId = variantRadioGroup.getCheckedRadioButtonId();
                String englishUser = getEnglishUser(radioButtonId);
                boolean markUser;

                if(card.getInfo("english").equals(englishUser)){
                  markUser = true;
                  outcome.setMarkGoodCount(1);
                }
                else{
                  markUser = false;
                }

                //Добавление в объект OutCome новой Card с результатами (Номер, Kanji, выбор пользователя, English, правильность ответа)
                card.setInfo("englishUser",englishUser);
                card.setInfo("markUser",Boolean.toString(markUser));
                outcome.addCard(card);

                cardIndex++;

                //Если все карточки закончились
                if(cardIndex >= cardCount){
                 // Переход на форму вывода результата ResultActivity
                    Intent cardActivityIntent = new Intent(CardActivity.this, ResultActivity.class);
                    String outcomeJson = new GsonBuilder().create().toJson(outcome);
                    cardActivityIntent.putExtra("OUTCOME", outcomeJson);
                    startActivity(cardActivityIntent);
                }
                else{
                  card = cardList.get(cardIndex);
                  viewCards();
                }

            }
        });

    }


    private void viewCards(){
        variantRadioGroup.clearCheck();
        variant1RadioButton.setChecked(true);
        variant2RadioButton.setChecked(false);
        variant3RadioButton.setChecked(false);
        variant4RadioButton.setChecked(false);

        cardIndexTextView.setText("Card " + cardIndex + 1);
        kanjiTextView.setText(card.getInfo("kanji"));
        String variants = card.getInfo("variants");
        String[] variantsArray = card.variantsToArray(variants);

        variant1RadioButton.setText(variantsArray[0].trim());
        variant2RadioButton.setText(variantsArray[1].trim());
        variant3RadioButton.setText(variantsArray[2].trim());
        variant4RadioButton.setText(variantsArray[3].trim());
    }



    private String getEnglishUser(int id){
        String textContent = "";

        if(id == R.id.variant1RadioButton){
            textContent = variant1RadioButton.getText().toString();
        }
        if(id == R.id.variant2RadioButton){
            textContent = variant2RadioButton.getText().toString();
        }
        if(id == R.id.variant3RadioButton){
            textContent = variant3RadioButton.getText().toString();
        }
        if(id == R.id.variant4RadioButton){
            textContent = variant4RadioButton.getText().toString();
        }
        return textContent;
    }
}

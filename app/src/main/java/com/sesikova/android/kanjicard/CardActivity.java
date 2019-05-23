package com.sesikova.android.kanjicard;

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
import com.sesikova.android.kanjicard.Service.DataBaseQuery;
import com.sesikova.android.kanjicard.Service.Card;
import com.sesikova.android.kanjicard.Service.ResultObject;
import com.sesikova.android.kanjicard.Service.ScoreObject;

import java.util.List;

public class CardActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView cardIndexTextView, kanjiTextView;
    private RadioGroup variantRadioGroup;
    private RadioButton variant1RadioButton,variant2RadioButton,variant3RadioButton,variant4RadioButton;

    private Card card;
    private List<Card> cardList;
    private int cardCount;
    private int cardIndex = 0;

    private ScoreObject scoreObject;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        String categoryName = getIntent().getExtras().getString("TOPIC_NAME");
        if(!TextUtils.isEmpty(categoryName)){
            setTitle("Card : " + categoryName);
        }

        scoreObject = new ScoreObject();

        cardIndexTextView = (TextView)findViewById(R.id.cardIndexTextView);
        kanjiTextView = (TextView)findViewById(R.id.kanjiTextView);
        variantRadioGroup = (RadioGroup)findViewById(R.id.variantRadioGroup);

        variant1RadioButton = (RadioButton)findViewById(R.id.variant1RadioButton);
        variant2RadioButton = (RadioButton)findViewById(R.id.variant2RadioButton);
        variant3RadioButton = (RadioButton)findViewById(R.id.variant3RadioButton);
        variant4RadioButton = (RadioButton)findViewById(R.id.variant4RadioButton);


        int topicId = getIntent().getExtras().getInt("TOPIC_ID");
        DataBaseQuery query = new DataBaseQuery(CardActivity.this);

        cardList = query.getCardList(topicId);

        Button nextQuestionButton = (Button)findViewById(R.id.next_quiz);

        if(cardList.size() > 0){
            cardCount = cardList.size();
            card = cardList.get(cardIndex);
            displayQuizQuestions();

            //assert nextQuestionButton != null;

        }else{
            variant1RadioButton.setVisibility(View.GONE);
            variant2RadioButton.setVisibility(View.GONE);
            variant3RadioButton.setVisibility(View.GONE);
            variant4RadioButton.setVisibility(View.GONE);
            nextQuestionButton.setVisibility(View.GONE);
            Toast.makeText(CardActivity.this, getString(R.string.no_quiz), Toast.LENGTH_LONG).show();
        }

        nextQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioButtonId = variantRadioGroup.getCheckedRadioButtonId();
                String englishUser = selectedAnswerOption(radioButtonId);

                if(englishUser.equals("")){
                    Toast.makeText(CardActivity.this, "You must select an answer " + englishUser, Toast.LENGTH_LONG).show();
                }else{
                    //check for the correct answer
                    boolean markUser;

                    if(card.getInfo("english").trim().equals(englishUser.trim())){
                        //set new score
                        markUser = true;
                        scoreObject.setScore(1);
                    }else{
                        markUser = false;
                    }



                    //Добавление объекта с результатами (Номер, Kanji, выбор пользователя, English, правильность ответа)

                    card.setInfo("englishUser",englishUser);
                    card.setInfo("markUser",Boolean.toString(markUser));
                    scoreObject.addCard(card);

                    cardIndex++;

                    // check if there is more question
                    if(cardIndex >= cardCount){
                        // Quiz over
                        Intent quizActivityIntent = new Intent(CardActivity.this, ResultActivity.class);

                        Gson gson = new GsonBuilder().create();
                        final String scoreString = gson.toJson(scoreObject);

                        quizActivityIntent.putExtra("RESULT_OBJECT", scoreString);
                        quizActivityIntent.putExtra("QUIZ_SCORE", String.valueOf(scoreObject.getScore()));
                        quizActivityIntent.putExtra("QUIZ_COUNT", String.valueOf(cardCount));

                        // compare score and save
                        double percentageScore = (scoreObject.getScore() * 100) / cardCount ;
                        Double mDouble = new Double(percentageScore);


                        startActivity(quizActivityIntent);

                    }else{
                        // display new questions
                        card = cardList.get(cardIndex);

                        displayQuizQuestions();
                    }
                }
            }
        });

    }


    private void displayQuizQuestions(){
        if(card != null){
            unsetRadioButton();
            int currentQuestion = cardIndex + 1;
            cardIndexTextView.setText("Card " + currentQuestion);
            kanjiTextView.setText(card.getInfo("kanji"));

            String variants = card.getInfo("variants");
            String[] allAnswerOptions = card.convertOptionsToStringArray(variants);

            variant1RadioButton.setText(allAnswerOptions[0]);
            variant2RadioButton.setText(allAnswerOptions[1]);
            variant3RadioButton.setText(allAnswerOptions[2]);
            variant4RadioButton.setText(allAnswerOptions[3]);
        }
    }

    private void unsetRadioButton(){
        variantRadioGroup.clearCheck();
        variant1RadioButton.setChecked(false);
        variant2RadioButton.setChecked(false);
        variant3RadioButton.setChecked(false);
        variant4RadioButton.setChecked(false);
    }

    private String selectedAnswerOption(int id){
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

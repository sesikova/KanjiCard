package com.sesikova.android.kanjicard.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

        //Создаем объект outcome для хранения результатов теста.
        outcome = new Outcome();
        //Запрашиваем из БД карточки заданной темы (topicId)
        cardList = new DataBaseQuery(CardActivity.this).getCardList(topicId);
        //Если карточки заддной темы есть
        if(cardList.size() != 0){
            //Заносим в outcome количество карточек
            cardCount = cardList.size();
            outcome.setMarkAllCount(cardCount);
            //Выбираем первую карточку и выводим информацию
            card = cardList.get(cardIndex);
            viewCard();
        }
        //Если карточек нет, то скрываем все элементы и выводим сообщение
        else{
            variantRadioGroup.setVisibility(View.GONE);
            nextCardButton.setVisibility(View.GONE);
            Toast.makeText(CardActivity.this, getString(R.string.noCard_message), Toast.LENGTH_LONG).show();
        }

        //Обработка кнопки NEXT CARD
        nextCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Узнаем какой радио-кнопку User выбрал и соответсвеннно с каким текстом
                int radioButtonId = variantRadioGroup.getCheckedRadioButtonId();
                String englishUser = getEnglishUser(radioButtonId);
                boolean markUser;

                //Если английский текст карточки равен тексту выбранному User
                if(card.getInfo("english").equals(englishUser)){
                  //Выставляем признак зачета True
                  markUser = true;
                  //А количество правильных ответов увеличиваем на 1
                  outcome.setMarkGoodCount(1);
                }
                //Если ответ не совпадает
                else{
                  //Выставляем признак зачета False
                  markUser = false;
                }

                //Добавление в объект outcome новой Card с результатами (Номер, Kanji, выбор пользователя, English, правильность ответа)
                card.setInfo("englishUser",englishUser);
                card.setInfo("markUser",Boolean.toString(markUser));
                outcome.addCard(card);

                cardIndex++;

                //Если все карточки закончились
                if(cardIndex == cardCount){
                 // Переход на форму вывода результата ResultActivity
                    Intent cardActivityIntent = new Intent(CardActivity.this, ResultActivity.class);
                    String outcomeJson = new GsonBuilder().create().toJson(outcome);
                    cardActivityIntent.putExtra("OUTCOME", outcomeJson);
                    startActivity(cardActivityIntent);
                }
                //если карточки не закончились, получаем следующую и отображаем ее
                else{
                  card = cardList.get(cardIndex);
                  viewCard();
                }

            }
        });

    }

    //Отображение информации карты и вариантов ответа (variant)
    private void viewCard(){
        //Очистка радио-кнопок
        variantRadioGroup.clearCheck();
        //Первую кнопку Tue, чтобы исключить - не выбрано ни однга кнопка
        variant1RadioButton.setChecked(true);
        //Остальые False
        variant2RadioButton.setChecked(false);
        variant3RadioButton.setChecked(false);
        variant4RadioButton.setChecked(false);

        //Вывод номера карты и иероглифа
        cardIndexTextView.setText("Card " + card.getInfo("id"));
        kanjiTextView.setText(card.getInfo("kanji"));

        //Вывод текста вариантов ответов
        String[] variantArray = card.getVariantArray();
        variant1RadioButton.setText(variantArray[0]);
        variant2RadioButton.setText(variantArray[1]);
        variant3RadioButton.setText(variantArray[2]);
        variant4RadioButton.setText(variantArray[3]);
    }


    //Узнаем текс нажатой радио-кнопки
    private String getEnglishUser(int radioButtonId){
        String EnglishUser = "";

        switch (radioButtonId){
            case R.id.variant1RadioButton : EnglishUser = variant1RadioButton.getText().toString();
                 break;
            case R.id.variant2RadioButton : EnglishUser = variant2RadioButton.getText().toString();
                 break;
            case R.id.variant3RadioButton : EnglishUser = variant3RadioButton.getText().toString();
                break;
            case R.id.variant4RadioButton : EnglishUser = variant4RadioButton.getText().toString();
                break;
        }
        return EnglishUser;
    }
}

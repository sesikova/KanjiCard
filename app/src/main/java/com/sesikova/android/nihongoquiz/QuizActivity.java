    package com.sesikova.android.nihongoquiz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sesikova.android.nihongoquiz.DataBase.DataBaseQuery;
import com.sesikova.android.nihongoquiz.Entity.QuizObject;
import com.sesikova.android.nihongoquiz.Entity.ResultObject;
import com.sesikova.android.nihongoquiz.Entity.ScoreObject;
import com.sesikova.android.nihongoquiz.Helper.MySharedPreference;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView questionNumber, question;
    private RadioGroup radioGroup;
    private RadioButton option1, option2, option3, option4;

    private List<QuizObject> quizList;
    private QuizObject quizObject;

    private int quizCount;
    private int quizIndex = 0;

    private ScoreObject scoreObject;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        String categoryName = getIntent().getExtras().getString("TOPIC_NAME");
        if(!TextUtils.isEmpty(categoryName)){
            setTitle("Quiz on " + categoryName);
        }

        scoreObject = new ScoreObject();

        questionNumber = (TextView)findViewById(R.id.question_number);
        question = (TextView)findViewById(R.id.question);

        radioGroup = (RadioGroup)findViewById(R.id.answer_options);

        option1 = (RadioButton)findViewById(R.id.answer_1);
        option2 = (RadioButton)findViewById(R.id.answer_2);
        option3 = (RadioButton)findViewById(R.id.answer_3);
        option4 = (RadioButton)findViewById(R.id.answer_4);

        int topicId = getIntent().getExtras().getInt("TOPIC_ID");
        DataBaseQuery query = new DataBaseQuery(QuizActivity.this);

        quizList = query.getQuizList(topicId);

        Button nextQuestionButton = (Button)findViewById(R.id.next_quiz);

        if(quizList.size() > 0){
            quizCount = quizList.size();
            quizObject = quizList.get(quizIndex);
            displayQuizQuestions();

            assert nextQuestionButton != null;
            nextQuestionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int radioButtonId = radioGroup.getCheckedRadioButtonId();
                    String userSelectedAnswer = selectedAnswerOption(radioButtonId);

                    if(userSelectedAnswer.equals("")){
                        Toast.makeText(QuizActivity.this, "You must select an answer " + userSelectedAnswer, Toast.LENGTH_LONG).show();
                    }else{
                        //check for the correct answer
                        Log.d(TAG, "Match answers " + quizObject.getAnswer() + " select " + userSelectedAnswer);
                        boolean isCorrect;

                        if(quizObject.getAnswer().trim().equals(userSelectedAnswer.trim())){
                            //set new score
                            isCorrect = true;
                            scoreObject.setScore(1);
                        }else{
                            isCorrect = false;
                        }
                        //add the result
                        scoreObject.addNewResult(new ResultObject(Integer.toString(quizIndex+1), quizObject.getQuestion(), userSelectedAnswer, quizObject.getAnswer(),isCorrect));


                        Log.d(TAG, "Quiz Result " + scoreObject.getResultList().size());
                        quizIndex++;

                        // check if there is more question
                        if(quizIndex >= quizCount){
                            // Quiz over
                            Intent quizActivityIntent = new Intent(QuizActivity.this, ResultActivity.class);

                            //GsonBuilder builder = new GsonBuilder();
                            //Gson gson = builder.create();

                            Gson gson = new GsonBuilder().create();
                            final String scoreString = gson.toJson(scoreObject);

                            quizActivityIntent.putExtra("RESULT_OBJECT", scoreString);
                            quizActivityIntent.putExtra("QUIZ_SCORE", String.valueOf(scoreObject.getScore()));
                            quizActivityIntent.putExtra("QUIZ_COUNT", String.valueOf(quizCount));

                            // compare score and save
                            MySharedPreference sharedPreference = new MySharedPreference(QuizActivity.this);
                            double percentageScore = (scoreObject.getScore() * 100) / quizCount ;
                            Double mDouble = new Double(percentageScore);
                            int presentScore = mDouble.intValue();
                            if(!sharedPreference.isHighestScore(presentScore)){
                                sharedPreference.saveQuizHighestQuizScore(presentScore);
                            }

                            startActivity(quizActivityIntent);

                        }else{
                            // display new questions
                            quizObject = quizList.get(quizIndex);

                            displayQuizQuestions();
                        }
                    }
                }
            });
        }else{
            option1.setVisibility(View.GONE);
            option2.setVisibility(View.GONE);
            option3.setVisibility(View.GONE);
            option4.setVisibility(View.GONE);
            nextQuestionButton.setVisibility(View.GONE);
            Toast.makeText(QuizActivity.this, getString(R.string.no_quiz_in_category), Toast.LENGTH_LONG).show();
        }
    }

    private void displayQuizQuestions(){
        if(quizObject != null){
            unsetRadioButton();
            int currentQuestion = quizIndex + 1;
            questionNumber.setText("Question " + currentQuestion);
            question.setText(quizObject.getQuestion());

            String answerOption = quizObject.getOptions();
            String[] allAnswerOptions = quizObject.convertOptionsToStringArray(answerOption);

            option1.setText(allAnswerOptions[0]);
            option2.setText(allAnswerOptions[1]);
            option3.setText(allAnswerOptions[2]);
            option4.setText(allAnswerOptions[3]);
        }
    }

    private void unsetRadioButton(){
        radioGroup.clearCheck();
    }

    private String selectedAnswerOption(int id){
        String textContent = "";
        if(id == R.id.answer_1){
            textContent = option1.getText().toString();
        }
        if(id == R.id.answer_2){
            textContent = option2.getText().toString();
        }
        if(id == R.id.answer_3){
            textContent = option3.getText().toString();
        }
        if(id == R.id.answer_4){
            textContent = option4.getText().toString();
        }
        return textContent;
    }
}

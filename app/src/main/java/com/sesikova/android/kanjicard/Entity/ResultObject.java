package com.sesikova.android.kanjicard.Entity;



public class ResultObject {

    private String quizIndex;

    private String question;

    private String selectedAnswer;

    private String answer;

    private boolean isCorrect;

    public ResultObject(String quizIndex, String question, String selectedAnswer, String answer, boolean isCorrect) {
        this.quizIndex = quizIndex;
        this.question = question;
        this.selectedAnswer = selectedAnswer;
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public String getQuizIndex() {
        return quizIndex;
    }

    public String getQuestionTitle() {
        return question;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public String getCollectAnswer() {
        return answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}

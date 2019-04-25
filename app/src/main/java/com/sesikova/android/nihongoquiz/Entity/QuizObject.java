package com.sesikova.android.nihongoquiz.Entity;

public class QuizObject {

    private int topicId;

    private String question;

    private String options;

    private String answer;


    public QuizObject(int topicId, String question, String options, String answer) {
        this.topicId = topicId;
        this.question = question;
        this.options = options;
        this.answer = answer;
    }


    public int getTopicId() {
        return topicId;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptions() {
        return options;
    }

    public String getAnswer() {
        return answer;
    }



    public String[] convertOptionsToStringArray(String options){
        String[] allOptions = options.split(",");
        return allOptions;
    }

    public boolean isAnswerCorrect(String[] options, String answer){
        for(int i = 0; i < options.length; i++){
            if(options[i].equals(answer)){
                return true;
            }
        }
        return false;
    }
}

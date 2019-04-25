package com.sesikova.android.nihongoquiz.Entity;

public class TopicObject {

    private int topicId;
    private String topicName;
    private String topicNameJapan;
    private String topicImagePath;


    public TopicObject(int topicId, String topicName,String topicNameJapan, String topicImagePath) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.topicNameJapan = topicNameJapan;
        this.topicImagePath = topicImagePath;
    }

    public int getTopicId(){
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getTopicNameJapan() {
        return topicNameJapan;
    }

    public String getTopicImagePath() {
        return topicImagePath;
    }
}

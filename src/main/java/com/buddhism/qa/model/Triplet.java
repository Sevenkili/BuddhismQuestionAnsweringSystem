package com.buddhism.qa.model;

/**
 * 三元组的数据结构
 * Created by TT. Wu on 2017/4/22.
 */
public class Triplet {
    String subject;
    String predict;
    String object;

    public Triplet(){

    }

    public Triplet(String subject, String predict, String object){
        this.setObject(object);
        this.setPredict(predict);
        this.setSubject(subject);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPredict() {
        return predict;
    }

    public void setPredict(String predict) {
        this.predict = predict;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}

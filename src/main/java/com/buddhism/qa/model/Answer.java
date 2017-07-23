package com.buddhism.qa.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 候选答案的数据结构
 * Created by TT. Wu on 2017/4/22.
 */
public class Answer implements Comparable {
    // 选项内容
    String answerStr;

    // 选项的词语结构
    List<Word> keyWords = new ArrayList<>();
    List<Word> words = new ArrayList<>();

    // 选项中的汉字结构
    List<Character> characters = new ArrayList<>();

    // 支持选项的文本证据以及知识库证据
    List<TextEvidence> textEvidences = new ArrayList<>();
    List<Triplet> triplets = new ArrayList<>();

    Double[] answerEmbedding = new Double[300];

    // 答案的可信度

    public Double[] getAnswerEmbedding() {
        return answerEmbedding;
    }

    public void setAnswerEmbedding(Double[] answerEmbedding) {
        this.answerEmbedding = answerEmbedding;
    }

    double score = 0.0;

    public Answer(){

    }

    public Answer(String answerStr){
        this.setAnswerStr(answerStr);
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getAnswerStr() {
        return answerStr;
    }

    public void setAnswerStr(String answerStr) {
        this.answerStr = answerStr;
    }

    public List<Word> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<Word> keyWords) {
        this.keyWords = keyWords;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public List<TextEvidence> getTextEvidences() {
        return textEvidences;
    }

    public void setTextEvidences(List<TextEvidence> textEvidences) {
        this.textEvidences = textEvidences;
    }

    public List<Triplet> getTriplets() {
        return triplets;
    }

    public void setTriplets(List<Triplet> triplets) {
        this.triplets = triplets;
    }

    @Override
    public int compareTo(Object o) {
        Answer a = (Answer)o;
        if (a.getScore() < this.getScore())
            return 1;
        else if(a.getScore() > this.getScore()){
            return -1;
        }
        return 0;
    }
}

package com.buddhism.qa.model;

import com.buddhism.qa.analysis.Words;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wutong on 2017/7/23.
 */
public class Sentence implements Comparable<Sentence>{
    // 选项内容
    String sentenceStr;

    // 选项的词语结构
    List<Word> words = new ArrayList<>();

    // 选项中的汉字结构
    List<Character> characters = new ArrayList<>();

    Double[] sentenceEmbedding = new Double[300];

    // 句子的重要程度的可信度
    double score = 1.0;

    public Sentence(String sentenceStr){
        this.sentenceStr = sentenceStr;
//        Words.setWordList(this);
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

    public Double[] getSentenceEmbedding() {
        return sentenceEmbedding;
    }

    public void setSentenceEmbedding(Double[] sentenceEmbedding) {
        this.sentenceEmbedding = sentenceEmbedding;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }



    public String getSentenceStr() {
        return sentenceStr;
    }

    public void setSentenceStr(String sentenceStr) {
        this.sentenceStr = sentenceStr;
    }


    @Override
    public int compareTo(Sentence o) {
        if(this.score > o.score){
            return 1;
        }else{
            return 0;
        }
    }
}

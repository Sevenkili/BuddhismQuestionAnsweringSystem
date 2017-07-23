package com.buddhism.qa.model;

/**
 * 词语的数据结构
 * Created by TT. Wu on 2017/4/22.
 */
public class Word {

    String wordStr;
    Double[] wordEmbedding = new Double[300];
    WordType wordType;
    String pos;

    public Word(){

    }

    public Word(String wordStr){
        this.setWordStr(wordStr);
    }

    public String getWordStr() {
        return wordStr;
    }

    public void setWordStr(String wordStr) {
        this.wordStr = wordStr;
    }

    public Double[] getWordEmbedding() {
        return wordEmbedding;
    }

    public void setWordEmbedding(Double[] wordEmbedding) {
        this.wordEmbedding = wordEmbedding;
    }

    public WordType getWordType() {
        return wordType;
    }

    public void setWordType(WordType wordType) {
        this.wordType = wordType;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}

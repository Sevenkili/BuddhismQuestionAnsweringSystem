package com.buddhism.qa.model;

/**
 * 汉字的数据结构
 * Created by TT. Wu on 2017/4/22.
 */
public class Character {
    String characterStr;
    Double[] characterEmbedding = new Double[300];

    public Character(){

    }

    public Character(String characterStr){
        this.setCharacterStr(characterStr);
    }

    public String getCharacterStr() {
        return characterStr;
    }

    public void setCharacterStr(String characterStr) {
        this.characterStr = characterStr;
    }

    public Double[] getCharacterEmbedding() {
        return characterEmbedding;
    }

    public void setCharacterEmbedding(Double[] characterEmbedding) {
        this.characterEmbedding = characterEmbedding;
    }
}

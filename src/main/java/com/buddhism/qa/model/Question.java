package com.buddhism.qa.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题的数据结构
 * Created by TT. Wu on 2017/4/22.
 */
public class Question {
    // 输入问题可以得到的信息：题干和选项
    String questionStr;
    List<Answer> options = new ArrayList<>();
    // 问题的类型
    List<QuestionType> questionType;
    // 问题的词结构：全部的分词列表和关键词列表
    List<Word> keyWords = new ArrayList<>();
    List<Word> words = new ArrayList<>();
    // 问题的字结构：全部的字列表
    List<Character> characters = new ArrayList<>();
    // 处理问题后得到的文本证据
    List<TextEvidence> evidences = new ArrayList<>();
    // 问题的向量表示
    Double[] questionEmbedding = new Double[300];
    // 正确答案
    Answer expectAnswer;
    // 返回的答案
    Answer returnAnswer;

    public Question(){

    }

    public Question(String questionStr){
        this.setQuestionStr(questionStr);
    }

    public Answer getExpectAnswer() {
        return expectAnswer;
    }

    public void setExpectAnswer(Answer expectAnswer) {
        this.expectAnswer = expectAnswer;
    }

    public Answer getReturnAnswer() {
        return returnAnswer;
    }

    public void setReturnAnswer(Answer returnAnswer) {
        this.returnAnswer = returnAnswer;
    }

    public String getQuestionStr() {
        return questionStr;
    }

    public void setQuestionStr(String questionStr) {
        this.questionStr = questionStr;
    }

    public List<Answer> getOptions() {
        return options;
    }

    public void setOptions(List<Answer> options) {
        this.options = options;
    }

    public List<QuestionType> getQuestionType() {
        return questionType;
    }

    public void setQuestionType(List<QuestionType> questionType) {
        this.questionType = questionType;
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

    public List<TextEvidence> getEvidences() {
        return evidences;
    }

    public void setEvidences(List<TextEvidence> evidences) {
        this.evidences = evidences;
    }

    public Double[] getQuestionEmbedding() {
        return questionEmbedding;
    }

    public void setQuestionEmbedding(Double[] questionEmbedding) {
        this.questionEmbedding = questionEmbedding;
    }


}

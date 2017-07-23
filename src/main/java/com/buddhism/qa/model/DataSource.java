package com.buddhism.qa.model;

/**
 * Created by TT. Wu on 2017/4/22.
 */
public enum DataSource {
    DICTIONARY_DESCRIPTION("佛学百科"),
    FREQUENTLY_ASKED_QUESTIONS("常考问答对"),
    KNOWLEDGE_PERSON("人物知识图谱"),
    KNOWLEDGE_TERMINOLOGY("术语知识图谱"),
    TEXTBOOK("佛学教材"),
    WIKI("维基百科-佛学"),
    ORIGINAL_TRANSLATION("原文-译文");

    DataSource(String description){}
}

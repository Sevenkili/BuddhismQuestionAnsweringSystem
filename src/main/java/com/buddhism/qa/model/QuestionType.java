package com.buddhism.qa.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TT. Wu on 2017/4/24.
 */
public enum QuestionType {
    person("人物"),
    book("佛经名"),
    geography("地名"),
    doctrine("术语"),
    factoid("直答"),
    list("列举"),
    description("解释"),
    unknown("未知");

    String des;
    QuestionType(String description){
        this.des = description;
    }

    private Map<String, QuestionType> _getEnumMapQuestionType(){
        Map<String, QuestionType> map = new HashMap<>();

        map.put("人物", QuestionType.person);
        map.put("经名", QuestionType.book);
        map.put("地名", QuestionType.geography);
        map.put("术语", QuestionType.doctrine);
        map.put("直答", QuestionType.factoid);
        map.put("列举", QuestionType.list);
        map.put("解释", QuestionType.description);
        map.put("未知", QuestionType.unknown);

        return map;
    }

    public static QuestionType getQuestionType(String description){
        Map<String, QuestionType> map = new HashMap<>();

        QuestionType type = map.get(description);

        if(type == null){
            type = QuestionType.unknown;
        }

        return type;
    }
}

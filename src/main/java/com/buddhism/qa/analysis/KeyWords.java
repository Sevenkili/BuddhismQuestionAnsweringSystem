package com.buddhism.qa.analysis;

import com.buddhism.qa.model.Answer;
import com.buddhism.qa.model.Question;
import com.buddhism.qa.model.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取问句或选项中的关键词
 * Created by TT. Wu on 2017/4/23.
 */
public class KeyWords {

    //获取问句及选项中的关键词
    public static Question setKeyWord(Question question){
        List<Word> keyWords = new ArrayList<>();
        List<Word> words = question.getWords();

        for(Word word: words){
            if(word.getPos().startsWith("bpn")){
                keyWords.add(word);
            }
        }

        if(keyWords.size() == 0){
            keyWords = words;
        }

        question.setKeyWords(keyWords);
        //处理选项和答案
        question = setOptionsKeyWord(question);

        return question;
    }

    // 获取答案中的关键词
    public static Answer setKeyWord(Answer answer){
        List<Word> keyWords = new ArrayList<>();
        List<Word> words = answer.getWords();

        for(Word word: words){
            if(word.getPos().startsWith("bpn")){
                keyWords.add(word);
            }
        }

        if(keyWords.size() == 0){
            keyWords = words;
        }
        answer.setKeyWords(keyWords);
        return answer;
    }

    // 统一处理选项
    private static Question setOptionsKeyWord(Question question){
        List<Answer> optionList = question.getOptions();

        for(int i = 0; i < optionList.size(); i++){
            Answer processed = setKeyWord(optionList.get(i));
            optionList.set(i, processed);
        }

        question.setOptions(optionList);
        return question;
    }
}

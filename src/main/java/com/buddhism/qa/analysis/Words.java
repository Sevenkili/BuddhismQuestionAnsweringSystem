package com.buddhism.qa.analysis;

import com.buddhism.qa.model.*;

import com.buddhism.qa.util.segmentation.Segmentor;
import com.buddhism.qa.util.segmentation.SingletonSegmentorByJieba;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 关于分词方法的实现(设置Word：字段名、词性)
 * Created by TT. Wu on 2017/4/23.
 */
public class Words {
    private static final Logger LOG = LoggerFactory.getLogger(Words.class);

    public static Question setWordList(Question question){
        String questionStr = question.getQuestionStr();

        Result result = Segmentor.getInstance().parse(questionStr);
        List<Term> termList = result.getTerms();

        List<Word> words = new ArrayList<>();

        LOG.info("term list size："+ termList.size());
        for(Term wordTerm: termList){
            Word word = new Word();
            String wordStr = wordTerm.getName();
            if(wordStr.startsWith(")") || wordStr.equals("(")
                    || wordStr.equals("（") || wordStr.equals("）"))
                continue;

            word.setWordStr(wordTerm.getName());

            word.setPos(wordTerm.getNatureStr());
            words.add(word);
        }
        question.setWords(words);
        question = setOptionsWordList(question);

        return question;
    }

    public static Answer setWordList(Answer answer){
        String answerStr = answer.getAnswerStr();

        Result result = Segmentor.getInstance().parse(answerStr);
        List<Term> termList = result.getTerms();

        Word word = new Word();
        List<Word> words = new ArrayList<>();
        for(Term wordTerm: termList){
            word.setWordStr(wordTerm.getName());
            word.setPos(wordTerm.getNatureStr());
            words.add(word);
        }
        answer.setWords(words);
        return answer;
    }

    private static Question setOptionsWordList(Question question){
        List<Answer> options = question.getOptions();

        for(int i = 0; i < options.size(); i++){
            Answer answer = Words.setWordList(options.get(i));
            options.set(i, answer);
        }

        question.setOptions(options);
        return question;
    }
}

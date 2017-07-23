package com.buddhism.qa.analysis.answer;

import com.buddhism.qa.model.Answer;
import com.buddhism.qa.model.Question;
import org.apache.lucene.search.ScoreDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 将答案和问题组合成一句话
 * Created by TT. Wu on 2017/5/13.
 */
public class OptionRewrite {
    public static List<String> rewrite(Question question){
        String questionStr = question.getQuestionStr();
        List<Answer> answerList = question.getOptions();
        List<String> result = new ArrayList<>();

        for(Answer answer: answerList){
            String temp = questionStr;
            temp = temp.replace("()", answer.getAnswerStr());
            result.add(temp);
        }

        return result;
    }

}

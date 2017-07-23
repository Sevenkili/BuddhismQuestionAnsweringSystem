package com.buddhism.qa.analysis.answer;

import com.buddhism.qa.model.Answer;
import com.buddhism.qa.model.Question;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * "以上皆是"等选项的答案重写
 * Created by TT. Wu on 2017/4/23.
 */
public class AnswerRewriteFilter {
    public static Question answerRewrite(Question question){
        List<Answer> options = question.getOptions();
        Answer expectAnswer = question.getExpectAnswer();
        int size = options.size();
//        String regEx = "[以上|上述][皆|都|全][是|可|对|行]";
//        Pattern pattern = Pattern.compile(regEx);

        if(size-1 > 0){
            Answer lastOption = options.get(size - 1);
            String lastOptionStr = lastOption.getAnswerStr();
//            Matcher matcher = pattern.matcher(lastOptionStr);

//            if(matcher.matches()){
            if(lastOptionStr.startsWith("以上")){
                StringBuffer stringBuffer = new StringBuffer();

                for(int j = 0; j < size-1; j++){
                    stringBuffer.append(options.get(j).getAnswerStr()+" ");
                }
                String optionProcessed = stringBuffer.toString().trim();
                lastOption.setAnswerStr(optionProcessed);

                options.set(size-1, lastOption);

                if(expectAnswer.getAnswerStr().startsWith("以上")){
                    expectAnswer = lastOption;
                }
            }
        }

        question.setOptions(options);
        question.setExpectAnswer(expectAnswer);

        return question;
    }
}

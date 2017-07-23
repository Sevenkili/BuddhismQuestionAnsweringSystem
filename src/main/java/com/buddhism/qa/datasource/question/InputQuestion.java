package com.buddhism.qa.datasource.question;

import com.buddhism.qa.model.Question;
import com.buddhism.qa.system.QuestionAnsweringSystem;

import java.util.List;

/**
 * Created by TT. Wu on 2017/4/23.
 */
public interface InputQuestion {

    /**
     * 批量输入问题
     * @return
     */
    public List<Question> getQuestionList();

    /**
     * 输入问题及选项
     * @param questionStr
     * @param options
     * @return
     */
    public Question getQuestion(String questionStr, List<String> options);

    /**
     * 输入问题选项以及答案
     * @param questionStr
     * @param options
     * @param answerId
     * @return
     */
    public Question getQuestion(String questionStr, List<String> options, int answerId);

    /**
     * 批量输入问题并进行解答
     * @param questionAnsweringSystem
     * @return
     */
    public List<Question> getAndAnswerQuestions(QuestionAnsweringSystem questionAnsweringSystem, List<Question> questions);

    /**
     * 输入问题并进行解答
     * @param questionAnsweringSystem
     * @return
     */
    public Question getAndAnswerQuestion(QuestionAnsweringSystem questionAnsweringSystem, Question question);
}

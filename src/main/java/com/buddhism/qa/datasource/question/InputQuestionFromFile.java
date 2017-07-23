package com.buddhism.qa.datasource.question;

import com.buddhism.qa.files.FileConfig;
import com.buddhism.qa.model.Answer;
import com.buddhism.qa.model.Question;
import com.buddhism.qa.system.QuestionAnsweringSystem;
import com.buddhism.qa.util.xml.QuestionParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TT. Wu on 2017/5/8.
 */
public class InputQuestionFromFile implements InputQuestion{
    @Override
    public List<Question> getQuestionList() {
        List<Question> questionList = new QuestionParser().parseQuesion();
        return questionList;
    }

    public List<Question> getQuestionList(String filePath) {
        List<Question> questionList = new QuestionParser(filePath).parseQuesion();
        return questionList;
    }

    @Override
    public Question getQuestion(String questionStr, List<String> options) {
        Question question = new Question();
        question.setQuestionStr(questionStr);

        List<Answer> answerList = new ArrayList<>();
        for(String option: options){
            Answer answer = new Answer();
            answer.setAnswerStr(option);
            answerList.add(answer);
        }

        question.setOptions(answerList);
        return question;
    }

    @Override
    public Question getQuestion(String questionStr, List<String> options, int answerId) {

        Question question = new Question();
        question.setQuestionStr(questionStr);

        List<Answer> answerList = new ArrayList<>();
        for(String option: options){
            Answer answer = new Answer();
            answer.setAnswerStr(option);
            answerList.add(answer);
        }

        Answer correctAnswer = answerList.get(answerId-1);

        question.setOptions(answerList);
        question.setExpectAnswer(correctAnswer);

        return question;
    }

    @Override
    public List<Question> getAndAnswerQuestions(QuestionAnsweringSystem questionAnsweringSystem, List<Question> questions) {
        return null;
    }

    @Override
    public Question getAndAnswerQuestion(QuestionAnsweringSystem questionAnsweringSystem, Question question) {
        return null;
    }
}

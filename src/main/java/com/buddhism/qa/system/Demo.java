package com.buddhism.qa.system;

import com.buddhism.qa.analysis.KeyWords;
import com.buddhism.qa.analysis.Words;
import com.buddhism.qa.analysis.answer.AnswerRewriteFilter;

import com.buddhism.qa.datasource.document.IndexFile;
import com.buddhism.qa.datasource.question.InputQuestionFromFile;
import com.buddhism.qa.files.FileConfig;
import com.buddhism.qa.model.*;
import com.buddhism.qa.retrieval.AnswerEvidence;
import com.buddhism.qa.retrieval.GenerateQuery;
import com.buddhism.qa.retrieval.QuestionEvidence;
import com.buddhism.qa.score.ReturnAnswer;
import com.buddhism.qa.score.answer.TermFrequencyCandidateScore;
import com.buddhism.qa.score.answer.TextualAlignmentCandidateAnswerScore;
import com.buddhism.qa.score.evidence.TermMatchEvidenceScore;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TT. Wu on 2017/5/14.
 */
public class Demo {
    private static final Logger LOG = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws IOException {
        //输入问题及选项
        //数据源：文件
        List<Question> questionList = new InputQuestionFromFile().getQuestionList(FileConfig.questionFile2);

        //建立本地的文档索引
        IndexFile indexFile = new IndexFile();
//        Directory directory = indexFile.indexData(FileConfig.wikiData);

        List<Directory> directoryList = indexFile.indexData(FileConfig.frequentlyAskedQuestions);
        List<Directory> directoryList1 = indexFile.indexData(FileConfig.textBooks);
        directoryList.addAll(directoryList1);
        directoryList.add(indexFile.indexData(FileConfig.wikiData));
        Directory[] directory = directoryList.toArray(new Directory[directoryList.size()]);

        int correct = 0;
        int sum = questionList.size();
        List<String> couldAnswer = new ArrayList<>();

        //逐一处理问题
        //Question: questionStr, questionType, options, exceptAnswer
        //Answer: answerStr
        for(int i = 0; i < questionList.size(); i++){
            Question question = questionList.get(i);
            System.out.println("******************************");
            System.out.println("题目："+ question.getQuestionStr());

            /*对部分问题进行改写*/
            question = AnswerRewriteFilter.answerRewrite(question);

            /*为问句以及选项统一进行分词*/
            question = Words.setWordList(question);

            /*分析关键词*/
            question = KeyWords.setKeyWord(question);

            /*获取查询(通过问句题干进行查询)：term query*/
//            Query query1 = GenerateQuery.generateDescQueryFromKeyWords(question);
//            Query query2 = GenerateQuery.generateTitleQueryFromKeyWords(question);
            Query query1 = GenerateQuery.generateDescQueryFromKeyWords(question);

            /*获取证据列表*/
//            List<TextEvidence> evidenceList = QuestionEvidence.getEvidence(query1, DataSource.FREQUENTLY_ASKED_QUESTIONS, directories);
            List<TextEvidence> evidenceList = QuestionEvidence.getEvidence(query1, DataSource.WIKI, directory);

            /*选择可信的证据*/
            question = new TermMatchEvidenceScore().score(question, evidenceList);

            /*获取查询(通过答案获取查询)：term query*/
            List<Answer> options = question.getOptions();
            for(int j = 0; j < options.size(); j++){
                Answer option = options.get(j);
                /*由答案关键字生成查询*/
                Query query = GenerateQuery.generateDescQueryFromAnswerKeyWords(option);
                /*检索文本证据*/
                List<TextEvidence> optionEvidenceList = AnswerEvidence.getEvidence(query, DataSource.WIKI, directory);
                /*选择可信的证据*/
                option = new TermMatchEvidenceScore().score(option, optionEvidenceList);
                options.set(j, option);
            }
            question.setOptions(options);

            //利用证据对选项进行评分
            question = new TermFrequencyCandidateScore().score(question);
            question = new TextualAlignmentCandidateAnswerScore().score(question);
//            question = new TermFrequencyCandidateScore().reverseScore(question);

//            for(Answer option: question.getOptions()){
//                System.out.println(option.getAnswerStr()+": "+option.getScore());
//            }

            //获取可信度最高的答案
            question = ReturnAnswer.getAnswer(question);

            System.out.println("预期答案: "+question.getExpectAnswer().getAnswerStr());
            System.out.println("系统作答: "+question.getReturnAnswer().getAnswerStr());

            if(question.getExpectAnswer().getAnswerStr().equals(question.getReturnAnswer().getAnswerStr())){
                correct += 1;
                couldAnswer.add(i + ": " + question.getQuestionStr()+"  答案: "+question.getExpectAnswer().getAnswerStr());
            }

            System.out.println("****************************************************");
        }

        LOG.info("可以回答的问题有：");
        for(int i = 0; i < couldAnswer.size(); i++){
            System.out.println(couldAnswer.get(i));
        }
        LOG.info("系统准确率：");
        System.out.println("correct number: "+ correct);
        System.out.println("sum number: " + sum);
    }
}

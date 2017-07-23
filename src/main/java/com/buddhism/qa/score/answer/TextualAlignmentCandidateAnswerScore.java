package com.buddhism.qa.score.answer;

import com.buddhism.qa.model.Answer;
import com.buddhism.qa.model.Question;
import com.buddhism.qa.model.TextEvidence;
import com.buddhism.qa.model.Word;

import com.buddhism.qa.system.ScoreWeight;
import com.buddhism.qa.util.segmentation.Segmentor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通过文本对齐的方法对答案进行打分
 * Created by TT. Wu on 2017/5/18.
 */
public class TextualAlignmentCandidateAnswerScore implements AnswerScore {
    private static final Logger LOG  = LoggerFactory.getLogger(TextualAlignmentCandidateAnswerScore.class);
    private ScoreWeight scoreWeight = new ScoreWeight();

    @Override
    public Question score(Question question) {
        LOG.debug("***************");
        LOG.debug("文本对齐评分");

        //加载基本信息：问题，选项以及既有证据
        List<Word> wordList = question.getWords();
        int questionTermsSize = wordList.size();

        List<TextEvidence> evidenceList = question.getEvidences();
        List<Answer> options = question.getOptions();

        //对每个选项的得分进行初始化
        double[] scores = new double[options.size()];
        for(int i = 0; i < scores.length; i++){
            scores[i] = 0.0;
        }

        for(TextEvidence textEvidence: evidenceList){
            String evidenceText = textEvidence.getTitle() + " " + textEvidence.getSnippet();

            for(int i = 0; i < options.size(); i++){
                String answerStr = options.get(i).getAnswerStr();

                for(int j = 0; j < questionTermsSize; j++){
                    StringBuilder textAlignment = new StringBuilder();
                    for(int k = 0; k < questionTermsSize; k++){
                        if(j == k){
                            textAlignment.append(answerStr);
                        } else{
                            textAlignment.append(wordList.get(k).getWordStr());
                        }
                    }

                    String textualAlignmentPattern = textAlignment.toString();

                    //演化为多个模式，支持模糊匹配
                    List<Word> textualAlignmentPatternTerms = Segmentor.getWordList(textualAlignmentPattern);
                    List<String> patterns = new ArrayList<>();
                    patterns.add(textualAlignmentPattern);
                    StringBuilder str = new StringBuilder();
                    int len = textualAlignmentPatternTerms.size();

                    for(int t = 0; t < len; t++){
                        str.append(textualAlignmentPatternTerms.get(t).getWordStr());
                        if(t < len-1){
                            str.append(".{0,5}");
                        }
                    }
                    patterns.add(str.toString());

                    //判断文本是否对齐
                    int count = 0;
                    int length = 0;

                    for(String pattern: patterns){
//                        LOG.info(pattern);
                        Pattern p = Pattern.compile(pattern);
                        Matcher matcher = p.matcher(evidenceText);
                        while (matcher.find()){
                            String text = matcher.group();
                            LOG.debug("对齐的文本："+text);
                            LOG.debug("对齐的模式："+ pattern);
                            count++;
                            length += text.length();
                        }
                    }

                    //打分
                    if(count>0){
                        double avgLen = (double) length/count;
                        //问题长度question长度为正因子
                        //匹配长度avg长度为负因子
                        int questionLen = question.getQuestionStr().length();
                        scores[i] += scoreWeight.getTextualAlignmentCandidateAnswerScoreWeight() * questionLen / avgLen;
                    }
                }
            }
        }

        for(int x = 0; x < scores.length; x++){
            LOG.info("选项"+x+": "+scores[x]);
        }

        //重新封装成question对象
        for(int i = 0; i < options.size(); i++){
            Answer option = options.get(i);
            double score = option.getScore() + scores[i] * scoreWeight.getTermFrequencyAnswerScoreWeight();
            option.setScore(score);
            System.out.println(option.getScore());
            options.set(i, option);
        }
        question.setOptions(options);

        LOG.debug("文本对齐评分结束");
        LOG.debug("***********************");
        return question;
    }

    @Override
    public void setScoreWeight(ScoreWeight scoreWeight) {
        this.scoreWeight = scoreWeight;
    }
}

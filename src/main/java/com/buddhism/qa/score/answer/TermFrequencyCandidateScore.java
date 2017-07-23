package com.buddhism.qa.score.answer;

import com.buddhism.qa.model.Answer;
import com.buddhism.qa.model.Question;
import com.buddhism.qa.model.TextEvidence;
import com.buddhism.qa.model.Word;
import com.buddhism.qa.system.ScoreWeight;
import com.buddhism.qa.util.segmentation.Segmentor;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.apache.lucene.store.RAMDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基于词频进行评分
 * Created by TT. Wu on 2017/5/13.
 */
public class TermFrequencyCandidateScore implements AnswerScore {
    private static final Logger LOG = LoggerFactory.getLogger(TermFrequencyCandidateScore.class);
    private static final int TITLE_WEIGHT = 10;
    private ScoreWeight scoreWeight = new ScoreWeight();

    @Override
    public Question score(Question question) {
        LOG.debug("***********************");
        LOG.debug("词频评分开始");

        List<TextEvidence> evidences = question.getEvidences();
        List<Answer> options = question.getOptions();

        //对每个选项的得分进行初始化
        double[] scores = new double[options.size()];
        for(int i = 0; i < scores.length; i++){
            scores[i] = 0.0;
        }

        for(TextEvidence textEvidence: evidences){
            Map<String, Integer> map = getWordFrequency(textEvidence.getTitle(), textEvidence.getSnippet());
            double evidencesWeight = textEvidence.getScore();

            for(int i = 0; i < options.size(); i++){
                Answer option = options.get(i);
                List<Word> answerWords = option.getWords();
                int count = 1;

                for(Word answerWord: answerWords){
                    String word = answerWord.getWordStr();

                    Integer wordFrequency = map.get(word);
                    if(wordFrequency != null){
                        count += wordFrequency;
                    }
                }

                scores[i] += evidencesWeight * count;
            }
        }

        //重新封装成question对象
        for(int i = 0; i < options.size(); i++){
            Answer option = options.get(i);
            double score = option.getScore() + scores[i] * scoreWeight.getTermFrequencyAnswerScoreWeight();
            option.setScore(score);
            System.out.println(option.getAnswerStr()+": "+option.getScore());
            options.set(i, option);
        }
        question.setOptions(options);

        LOG.debug("词频分析结束");
        LOG.debug("***********************");
        return question;
    }

    @Override
    public void setScoreWeight(ScoreWeight scoreWeight) {
        this.scoreWeight = scoreWeight;
    }

    private Map<String, Integer> getWordFrequency(String title, String description){
        Result resultTitle = Segmentor.getInstance().parseStr(title);
        Result resultDescription = Segmentor.getInstance().parseStr(description);

        Map<String, Integer> mapWords = new HashMap<>();

        for(Term titleTerm: resultTitle.getTerms()){
            Integer count = mapWords.get(titleTerm.getName());

            if(count == null){
                count = TITLE_WEIGHT;
            } else{
                count += TITLE_WEIGHT;
            }

            mapWords.put(titleTerm.getName(), count);
        }

        for(Term descriptionTerm: resultDescription.getTerms()){
            Integer count = mapWords.get(descriptionTerm.getName());

            if(count == null){
                count = 1;
            } else{
                count += 1;
            }

            mapWords.put(descriptionTerm.getName(), count);
        }

        return mapWords;
    }

    /**
     * 问题答案证据的双向评分
     * @param question
     * @return
     */
    public Question bidirectionScore(Question question) {
        LOG.debug("***********************");
        LOG.debug("词频评分开始");

        List<TextEvidence> evidences = question.getEvidences();
        List<Answer> options = question.getOptions();

        //对每个选项的得分进行初始化
        double[] scores = new double[options.size()];
        for(int i = 0; i < scores.length; i++){
            scores[i] = 0.0;
        }

        //通过问题的证据对答案评分
        for(TextEvidence textEvidence: evidences){
            Map<String, Integer> map = getWordFrequency(textEvidence.getTitle(), textEvidence.getSnippet());
            double evidencesWeight = textEvidence.getScore();

            for(int i = 0; i < options.size(); i++){
                Answer option = options.get(i);
                List<Word> answerWords = option.getKeyWords();
                int count = 1;

                for(Word answerWord: answerWords){
                    String word = answerWord.getWordStr();

                    Integer wordFrequency = map.get(word);
                    if(wordFrequency != null){
                        count += wordFrequency;
                    }
                }

                scores[i] += evidencesWeight * count;
            }
        }

        //通过答案的证据对答案评分
        List<Word> questionWords = question.getWords();

        for(int i = 0; i < options.size(); i++){
            Answer option = options.get(i);
            List<TextEvidence> optionEvidences = option.getTextEvidences();

            for(TextEvidence textEvidence: optionEvidences) {
                Map<String, Integer> map = getWordFrequency(textEvidence.getTitle(), textEvidence.getSnippet());
                double evidencesWeight = textEvidence.getScore();
                int count = 1;

                for(Word answerWord: questionWords){
                    String word = answerWord.getWordStr();

                    Integer wordFrequency = map.get(word);
                    if(wordFrequency != null){
                        count += wordFrequency;
                    }
                }

                scores[i] += evidencesWeight * count;
            }
        }

        //重新封装成question对象
        for(int i = 0; i < options.size(); i++){
            Answer option = options.get(i);
            double score = option.getScore() + scores[i] * scoreWeight.getTermFrequencyAnswerScoreWeight();
            option.setScore(score);
            System.out.println(option.getAnswerStr()+": "+option.getScore());
            options.set(i, option);
        }
        question.setOptions(options);

        LOG.debug("词频分析结束");
        LOG.debug("***********************");
        return question;
    }

    /**
     * 问题答案证据的反向评分
     * @param question
     * @return
     */
    public Question reverseScore(Question question) {
        LOG.debug("***********************");
        LOG.debug("词频评分开始");

        List<TextEvidence> evidences = question.getEvidences();
        List<Answer> options = question.getOptions();

        //对每个选项的得分进行初始化
        double[] scores = new double[options.size()];
        for(int i = 0; i < scores.length; i++){
            scores[i] = 0.0;
        }

        //通过答案的证据对答案评分
        List<Word> questionWords = question.getWords();

        for(int i = 0; i < options.size(); i++){
            Answer option = options.get(i);
            List<TextEvidence> optionEvidences = option.getTextEvidences();

            for(TextEvidence textEvidence: optionEvidences) {
                Map<String, Integer> map = getWordFrequency(textEvidence.getTitle(), textEvidence.getSnippet());
                double evidencesWeight = textEvidence.getScore();
                int count = 1;

                for(Word answerWord: questionWords){
                    String word = answerWord.getWordStr();

                    Integer wordFrequency = map.get(word);
                    if(wordFrequency != null){
                        count += wordFrequency;
                    }
                }

                scores[i] += evidencesWeight * count;
            }
        }

        //重新封装成question对象
        for(int i = 0; i < options.size(); i++){
            Answer option = options.get(i);
            double score = option.getScore() + scores[i] * scoreWeight.getTermFrequencyAnswerScoreWeight();
            option.setScore(score);
            System.out.println(option.getAnswerStr()+": "+option.getScore());
            options.set(i, option);
        }
        question.setOptions(options);

        LOG.debug("词频分析结束");
        LOG.debug("***********************");
        return question;
    }


}

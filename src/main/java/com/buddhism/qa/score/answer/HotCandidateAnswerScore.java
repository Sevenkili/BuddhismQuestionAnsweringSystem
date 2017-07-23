package com.buddhism.qa.score.answer;

import com.buddhism.qa.model.Question;
import com.buddhism.qa.model.Word;
import com.buddhism.qa.system.ScoreWeight;
import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 找关键词，在证据中距离关键词最近的答案分值翻倍。
 * Created by TT. Wu on 2017/5/19.
 */
public class HotCandidateAnswerScore implements AnswerScore {
    private static final Logger LOG = LoggerFactory.getLogger(HotCandidateAnswerScore.class);
    private ScoreWeight scoreWeight = new ScoreWeight();

    @Override
    public Question score(Question question) {
        return question;
    }

    @Override
    public void setScoreWeight(ScoreWeight scoreWeight) {
        this.scoreWeight = scoreWeight;
    }
}

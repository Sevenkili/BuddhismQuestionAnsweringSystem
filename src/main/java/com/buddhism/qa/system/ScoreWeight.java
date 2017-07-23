package com.buddhism.qa.system;

/**
 * 列举设置评分组件的权重
 * Created by TT. Wu on 2017/4/23.
 */
public class ScoreWeight {
    public double TermFrequencyAnswerScoreWeight = 1;
    public double TextualAlignmentCandidateAnswerScoreWeight = 1;

    public double getTextualAlignmentCandidateAnswerScoreWeight() {
        return TextualAlignmentCandidateAnswerScoreWeight;
    }

    public void setTextualAlignmentCandidateAnswerScoreWeight(double textualAlignmentCandidateAnswerScoreWeight) {
        TextualAlignmentCandidateAnswerScoreWeight = textualAlignmentCandidateAnswerScoreWeight;
    }

    public double getTermFrequencyAnswerScoreWeight() {
        return TermFrequencyAnswerScoreWeight;
    }

    public void setTermFrequencyAnswerScoreWeight(double termFrequencyAnswerScoreWeight) {
        TermFrequencyAnswerScoreWeight = termFrequencyAnswerScoreWeight;
    }
}

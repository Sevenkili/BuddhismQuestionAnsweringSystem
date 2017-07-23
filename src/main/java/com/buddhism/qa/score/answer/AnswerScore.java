package com.buddhism.qa.score.answer;

import com.buddhism.qa.model.Question;
import com.buddhism.qa.system.ScoreWeight;

/**
 * 对选项进行最终的评分
 * Created by TT. Wu on 2017/4/23.
 */
public interface AnswerScore {
    /**
     * 对每个候选答案进行打分，要求问题和答案的证据已知；
     * 得到的分值会保存在Answer实例中。
     * @param question
     */
    public Question score(Question question);

    /**
     * 设置评分组件的权重
     * @param scoreWeight
     */
    public void setScoreWeight(ScoreWeight scoreWeight);
}

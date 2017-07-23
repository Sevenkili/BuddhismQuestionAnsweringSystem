package com.buddhism.qa.score.evidence;

import com.buddhism.qa.model.Question;
import com.buddhism.qa.model.TextEvidence;
import com.buddhism.qa.system.ScoreWeight;

import java.io.IOException;
import java.util.List;

/**
 * Created by TT. Wu on 2017/4/24.
 */
public interface EvidenceScore {
    /**
     * 根据问题对候选证据进行打分，并将分值写入TextEvidence的实例中。
     * @param question
     * @param evidence
     */
    public  Question score(Question question, List<TextEvidence> evidence) throws IOException;

    /**
     * 对不同的评分组件设置不同的值
     * @param scoreWeight
     */
    public void setScoreWeight(ScoreWeight scoreWeight);
}

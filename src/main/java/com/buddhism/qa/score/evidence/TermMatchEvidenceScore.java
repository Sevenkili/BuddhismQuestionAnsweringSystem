package com.buddhism.qa.score.evidence;

import com.buddhism.qa.datasource.IndexEvidence;
import com.buddhism.qa.model.Answer;
import com.buddhism.qa.model.Question;
import com.buddhism.qa.model.TextEvidence;
import com.buddhism.qa.model.Word;
import com.buddhism.qa.score.answer.TermFrequencyCandidateScore;
import com.buddhism.qa.system.ScoreWeight;
import com.buddhism.qa.util.lucene.ConcreteTFIDFSimilarity;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;

import org.apache.lucene.store.Directory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import java.util.List;

/**
 * 基于tf-idf的文本证据评分算法
 * Created by TT. Wu on 2017/5/14.
 */
public class TermMatchEvidenceScore implements EvidenceScore {
    private static final Logger LOG = LoggerFactory.getLogger(TermFrequencyCandidateScore.class);
    private ScoreWeight scoreWeight = new ScoreWeight();
    /**
     * 选择问题对应的证据（暂时省略筛选）
     * @param question
     * @param evidences
     * @return
     */
    @Override
    public Question score(Question question, List<TextEvidence> evidences) throws IOException {
        question.setEvidences(evidences);
        return question;
//        LOG.debug("开始进行基于tf-idf的文本证据评分");
//        Directory directory = IndexEvidence.index(evidence);
//        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));
//        ConcreteTFIDFSimilarity similarity = new ConcreteTFIDFSimilarity();
//        indexSearcher.setSimilarity(similarity);
//        IndexReader indexReader = indexSearcher.getIndexReader();
//
//        for(Word word: question.getWords()){
//            Term term = new Term("description", word.getWordStr());
//            Query query = new TermQuery(term);
//
//            //计算idf
//            float idf = similarity.idf(indexReader.docFreq(term), indexReader.maxDoc());
//
//            System.out.println(idf);
//            //CollectionStatistics collectionStats = new CollectionStatistics("description", indexReader.maxDoc(), indexReader.getDocCount("description"), indexReader.getSumTotalTermFreq("description"), indexReader.getSumDocFreq("description"));
//            //TermStatistics termStats = new TermStatistics(term.bytes(), indexReader.docFreq(term), indexReader.totalTermFreq(term));
//
//            //Explanation explanation = similarity.idfExplain(collectionStats, termStats);
//            //LOG.debug("idf说明： "+explanation.toString());
//
//            // finds documents which contain the term in the field contents
//            PostingsEnum docEnum = MultiFields.getTermDocsEnum(indexReader, "description", term.bytes());
//
//
//            LOG.debug("indexReader.numDocs: "+indexReader.numDocs());
//
//            // for each document which is result of the query:
//            while(docEnum.nextDoc() < indexReader.numDocs()){
//                // calculates term frequency
//                float tf = similarity.tf(docEnum.freq());
//
//                // calculates vector space model score for the document
//                double score = tf * idf;
//                System.out.println("\t\tDocument id: " + docEnum.docID() + " tf: " + tf + " score:" + score);
//            }
//
//            LOG.debug("结束文本证据评分");
//        }
//        return question;
    }

    /**
     * 设置答案的相关证据（暂时省略筛选）
     * @param answer
     * @param evidences
     * @return
     * @throws IOException
     */
    public Answer score(Answer answer, List<TextEvidence> evidences) throws IOException {
        answer.setTextEvidences(evidences);
        return answer;
    }

    @Override
    public void setScoreWeight(ScoreWeight scoreWeight) {
        this.scoreWeight = scoreWeight;
    }
}

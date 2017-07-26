package test.com.buddhism.qa.score.sentence;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buddhism.qa.model.Sentence;
import com.buddhism.qa.model.TextEvidence;
import com.buddhism.qa.score.sentence.CutSentences;
import com.buddhism.qa.score.sentence.SentenceMatchEvidenceScore;



public class SentenceScoreTest {
	private static final Logger LOG  = LoggerFactory.getLogger(SentenceScoreTest.class);
	@Test
	public void test(){
		SentenceMatchEvidenceScore ss = new SentenceMatchEvidenceScore();
		TextEvidence textEvidence = ss.evidence;
		LOG.info("证据评分之前：");
		for(Sentence sentence:textEvidence.getImportantSentenceList()){
			System.out.println(sentence.getSentenceStr());
		}
		ss.setImportantSentence(textEvidence, ss.questionStr) ;
		LOG.info("证据评分之后：");
		System.out.println("问题:" + ss.questionStr);
		for(Sentence sentence:textEvidence.getImportantSentenceList()){
			System.out.println("证据:" + sentence.getSentenceStr()+"score:"+sentence.getScore());
		}
		
	}

}

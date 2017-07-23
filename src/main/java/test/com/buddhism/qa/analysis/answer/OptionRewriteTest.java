package test.com.buddhism.qa.analysis.answer;

import com.buddhism.qa.analysis.answer.OptionRewrite;
import com.buddhism.qa.model.Answer;
import com.buddhism.qa.model.Question;
import org.apache.lucene.search.ScoreDoc;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;
import java.util.List;

/**
 * OptionRewrite Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>???? 13, 2017</pre>
 */
public class OptionRewriteTest {

    @Before
    public void before() throws Exception {


    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: rewrite(Question question)
     */
    @Test
    public void testRewrite() throws Exception {
        Question question = new Question("内心起嗔恚，应以()对治");
        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer("平常心"));
        answerList.add(new Answer("富贵心"));
        question.setOptions(answerList);
        List<String> result = OptionRewrite.rewrite(question);
        for(String s: result){
            System.out.println(s);
        }
    }


} 

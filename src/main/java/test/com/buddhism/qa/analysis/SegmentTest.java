package test.com.buddhism.qa.analysis;

import com.buddhism.qa.analysis.Words;
import com.buddhism.qa.model.Question;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * Words Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>???? 13, 2017</pre>
 */
public class SegmentTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: setWordList(Question question)
     */
    @Test
    public void testSetWordListQuestion() throws Exception {
//TODO: Test goes here...
        Words segment = new Words();
        Question question = new Question("菩萨初地是发光地");
        segment.setWordList(question);

    }

    /**
     * Method: setWordList(Answer answer)
     */
    @Test
    public void testSetWordListAnswer() throws Exception {
//TODO: Test goes here... 
    }


} 

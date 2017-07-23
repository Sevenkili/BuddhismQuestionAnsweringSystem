package test.com.buddhism.qa.util.xml;

import com.buddhism.qa.model.Question;
import com.buddhism.qa.util.xml.QuestionParser;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.List;

/**
 * QuestionParser Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>???? 9, 2017</pre>
 */
public class QuestionParserTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: parseQuesion()
     */
    @Test
    public void testParseQuesion() throws Exception {
    //TODO: Test goes here...
        List<Question> questionList = new QuestionParser().parseQuesion();

        for(Question question: questionList){
            System.out.println(question.getQuestionStr());
        }
    }


    /**
     * Method: _parse()
     */
    @Test
    public void test_parse() throws Exception {
    //TODO: Test goes here...

    }

} 

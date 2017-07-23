package test.com.buddhism.qa.util.segmentation;

import com.buddhism.qa.util.segmentation.AnsjTest;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * AnsjTest Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>???? 11, 2017</pre>
 */
public class AnsjTestTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: baseAnalysisTest(String text)
     */
    @Test
    public void testBaseAnalysisTest() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: toAnalysisTest(String text)
     */
    @Test
    public void testToAnalysisTest() throws Exception {
//TODO: Test goes here...

    }

    /**
     * Method: nlpAnalysisTest(String text)
     */
    @Test
    public void testNlpAnalysisTest() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: indexAnalysisTest(String text)
     */
    @Test
    public void testIndexAnalysisTest() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: toAnalysisWithDictionary(String text)
     */
    @Test
    public void testToAnalysisWithDictionary() throws Exception {
//TODO: Test goes here...
        AnsjTest ansjTest = new AnsjTest();
        ansjTest.toAnalysisWithDictionary("阿旃陀石窟在哪里");
    }

    /**
     * Method: toAnalysisWithMultiDictionary(String text)
     */
    @Test
    public void testToAnalysisWithMultiDictionary() throws Exception {
//TODO: Test goes here...
        AnsjTest ansjTest = new AnsjTest();
        ansjTest.toAnalysisWithMultiDictionary("阿旃陀石窟在哪里");

    }


} 

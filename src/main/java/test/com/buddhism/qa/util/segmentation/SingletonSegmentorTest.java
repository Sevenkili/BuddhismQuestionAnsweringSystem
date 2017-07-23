package test.com.buddhism.qa.util.segmentation;

import com.buddhism.qa.util.segmentation.SingletonSegmentorByJieba;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.List;

/**
 * SingletonSegmentorByJieba Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>???? 15, 2017</pre>
 */
public class SingletonSegmentorTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getInstance()
     */
    @Test
    public void testGetInstance() throws Exception {
        SingletonSegmentorByJieba segmentor = SingletonSegmentorByJieba.getInstance();
        List<String> segTokens = segmentor.sentenceProcess("大慈大悲观世音菩萨");
        for(String s: segTokens){
            System.out.println(s);
        }
    }


} 

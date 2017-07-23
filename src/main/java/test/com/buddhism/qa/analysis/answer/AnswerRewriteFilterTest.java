package test.com.buddhism.qa.analysis.answer;

import com.buddhism.qa.analysis.answer.AnswerRewriteFilter;
import com.buddhism.qa.analysis.answer.OptionRewrite;
import com.buddhism.qa.model.Answer;
import com.buddhism.qa.model.Question;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * AnswerRewriteFilter Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>五月 17, 2017</pre>
 */
public class AnswerRewriteFilterTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: answerRewrite(Question question)
     */
    @Test
    public void testAnswerRewrite() throws Exception {
//TODO: Test goes here...
        Question question = new Question("为人祝寿时，最适合()诵祝福");
        List<Answer> options = new ArrayList<>();
        Answer answer = new Answer("普门品、大悲咒");
        options.add(answer);
        answer = new Answer("金刚经");
        options.add(answer);
        answer = new Answer("心经");
        options.add(answer);
        answer = new Answer("以上皆是");
        options.add(answer);
        question.setOptions(options);

        question = AnswerRewriteFilter.answerRewrite(question);
        for(Answer option: question.getOptions()){
            System.out.println(option.getAnswerStr());
        }

//        String regEx = "[以上|上述][皆|都|全][是|可|对|行]";
//        Pattern pattern = Pattern.compile(regEx);
//        String s = "以上皆是";
//        Matcher matcher = pattern.matcher(s);
//        System.out.println(matcher.matches());
    }


} 

package com.buddhism.qa.util.lucene;

import com.buddhism.qa.model.Character;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;


import java.io.IOException;
import java.io.StringReader;

/**
 * Created by TT. Wu on 2017/4/24.
 */
public class TokenTest {
    public static void main(String[] args) throws IOException {
        String text = "The door has been opened for us";

        StandardAnalyzer analyzer = new StandardAnalyzer();
        TokenStream stream = analyzer.tokenStream("", new StringReader(text));

        //增加token表示的字符串属性
        CharTermAttribute term = stream.addAttribute(CharTermAttribute.class);



    }
}

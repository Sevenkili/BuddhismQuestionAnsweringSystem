package com.buddhism.qa.util.lucene.jieba;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.WordDictionary;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by TT. Wu on 2017/5/11.
 */
public class JiebaTokenizer {
    /**
     * 过滤文字,得到分词结果
     * @param text
     * @param dictionary
     * @return
     */
    public static String filter(String text, String... dictionary){
        String result = "";
        StringBuffer stringBuffer = new StringBuffer();
        JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();

        WordDictionary dic= WordDictionary.getInstance();

        for(String dict: dictionary){
            Path path = Paths.get(dict);
            dic.loadUserDict(path);
        }

        List<String> sentences = jiebaSegmenter.sentenceProcess(text);
        for (String word: sentences){
            stringBuffer.append(word + " ");
        }
        result = stringBuffer.toString().trim();

        return result;
    }

    public static List<String> getWordList(String text, String... dictionary){
        String result = "";
        JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
        WordDictionary dic= WordDictionary.getInstance();

        for(String dict: dictionary){
            Path path = Paths.get(dict);
            dic.loadUserDict(path);
        }

        List<String> sentences = jiebaSegmenter.sentenceProcess(text);
        return sentences;
    }
}

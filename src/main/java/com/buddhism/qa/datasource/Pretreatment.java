package com.buddhism.qa.datasource;

import com.buddhism.qa.util.segmentation.SingletonSegmentorByJieba;

import java.util.List;

/**
 * Created by TT. Wu on 2017/5/13.
 */
public class Pretreatment {
    public Pretreatment(){
    }

    /**
     * 预分词，添加空格
     * @param inputString
     * @return
     */
    public static String process(String inputString){
        StringBuffer stringBuffer = new StringBuffer();
        SingletonSegmentorByJieba segmentor = SingletonSegmentorByJieba.getInstance();

        List<String> segTokens = segmentor.sentenceProcess(inputString);
        for(String token: segTokens) {
            stringBuffer.append(token + " ");
        }
        return stringBuffer.toString().trim();
    }

    /**
     * 去除空格
     * @param inputString
     * @return
     */
    public static String removeSpace(String inputString){
        StringBuffer stringBuffer = new StringBuffer();

        String[] words = inputString.split(" ");
        for(String word: words){
            stringBuffer.append(word);
        }
        return stringBuffer.toString();
    }
}

package com.buddhism.qa.util.segmentation;

import com.buddhism.qa.files.FileConfig;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.WordDictionary;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * 单例模式：返回系统中使用的分词器(jieba)
 * Created by TT. Wu on 2017/5/15.
 */
public class SingletonSegmentorByJieba extends JiebaSegmenter {
    private static SingletonSegmentorByJieba segmentor;

    private SingletonSegmentorByJieba(){

    }

    public static SingletonSegmentorByJieba getInstance(){
        if(segmentor == null){
            WordDictionary dictionary = WordDictionary.getInstance();

            List<String> paths = FileConfig.getSegmentDirectionary();
            for(String path: paths){
                Path p = Paths.get(path);
                dictionary.loadUserDict(p);
            }

            segmentor = new SingletonSegmentorByJieba();
        }

        return segmentor;
    }

}

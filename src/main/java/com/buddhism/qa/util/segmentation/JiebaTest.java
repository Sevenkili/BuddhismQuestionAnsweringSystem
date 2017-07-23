package com.buddhism.qa.util.segmentation;

import com.buddhism.qa.files.FileConfig;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import com.huaban.analysis.jieba.WordDictionary;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by TT. Wu on 2017/5/11.
 */
public class JiebaTest {
    public static void main(String[] args) {
        WordDictionary dictionary = WordDictionary.getInstance();
        List<String> dics = FileConfig.getSegmentDirectionary();
        for(String dic: dics){
            Path path = Paths.get(dic);
            dictionary.loadUserDict(path);
        }
        JiebaSegmenter jiebaSegmenter1 = new JiebaSegmenter();
        List<SegToken> sentences2 = jiebaSegmenter1.process("", JiebaSegmenter.SegMode.SEARCH);
        for (SegToken s: sentences2){
            System.out.println(s);
        }
    }
}

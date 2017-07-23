package com.buddhism.qa.util.segmentation;

import com.buddhism.qa.files.FileConfig;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.domain.Value;
import org.nlpcn.commons.lang.tire.library.Library;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * Created by TT. Wu on 2017/5/15.
 */
public class AnsjTest1 {
    public static void main(String[] args) throws Exception {

        // 多用户词典
        String str = "神探夏洛克这部电影作者.是一个dota迷";
        System.out.println(ToAnalysis.parse(str));
        // 两个词汇 神探夏洛克 douta迷
        Forest dic1 = new Forest();
        Library.insertWord(dic1, new Value("神探夏洛克", "define", "1000"));
        Forest dic2 = new Forest();
        Library.insertWord(dic2, new Value("dota迷", "define", "1000"));
        Result result = ToAnalysis.parse(str, dic1, dic2);
        List<Term> terms = result.getTerms();
        for (Term term: terms){
            System.out.println(term.getName());
            System.out.println(term.getNatureStr());
            System.out.println(term.getRealName());
            System.out.println(term.getOffe());
            System.out.println(term.from());
            System.out.println("***************");
        }
    }

}

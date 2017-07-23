package com.buddhism.qa.util.segmentation;

import com.buddhism.qa.files.FileConfig;
import org.ansj.domain.Result;

import org.ansj.splitWord.analysis.BaseAnalysis;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.util.MyStaticValue;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.domain.Value;
import org.nlpcn.commons.lang.tire.library.Library;

import java.io.File;
import java.util.List;

/**
 * Created by TT. Wu on 2017/5/10.
 */
public class AnsjTest {
    public void baseAnalysisTest(String text){
        Result result = BaseAnalysis.parse(text);
        System.out.println(result.getTerms());
    }

    public void toAnalysisTest(String text){
        Result result = ToAnalysis.parse(text);
        System.out.println(result.getTerms());
    }

    public void nlpAnalysisTest(String text){
        Result result = NlpAnalysis.parse(text);
        System.out.println(result.getTerms());
    }

    public void indexAnalysisTest(String text){
        Result result = IndexAnalysis.parse(text);
        System.out.println(result.getTerms());
    }

    public void toAnalysisWithDictionary(String text) throws Exception {
        Forest forest = Library.makeForest("src\\main\\resources\\dictionary_word\\a_art.dic");
        Result result = ToAnalysis.parse(text, forest);
        System.out.println(result.getTerms());
        result = ToAnalysis.parse(text);
        System.out.println(result.getTerms());
    }

    public void toAnalysisWithMultiDictionary(String text) throws Exception{
        Forest forest1 = Library.makeForest(FileConfig.bpnArt);
        Forest forest2 = Library.makeForest(FileConfig.bpnBook);
        Forest forest3 = Library.makeForest(FileConfig.bpnDoctrine);

        Result result = ToAnalysis.parse(text, forest1, forest2, forest3);

        System.out.println(result.getTerms());
    }

    public static void main(String[] args) throws Exception {
        AnsjTest ansjTest = new AnsjTest();
        ansjTest.toAnalysisWithDictionary("°²ÔÀÄ¥ÑÂ·ðÏñÔÚÄÄÀï");
    }
}

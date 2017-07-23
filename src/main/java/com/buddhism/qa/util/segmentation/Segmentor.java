package com.buddhism.qa.util.segmentation;

import com.buddhism.qa.files.FileConfig;
import com.buddhism.qa.model.Word;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.Analysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.domain.Value;
import org.nlpcn.commons.lang.tire.library.Library;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by TT. Wu on 2017/5/15.
 */
public class Segmentor extends ToAnalysis {
    private static List<Forest> forestList;
    private static Segmentor analysis;

    private static void getDictionary(){
        List<String> filename = FileConfig.getSegmentDirectionary();
        List<Forest> forests = new ArrayList<>();
        String temp = "";

        for(String s: filename){
            String pos = s.split("_")[2].replace("dic\\", "bpn_");
            BufferedReader br = getBufferedReader(s);
            Forest dic = new Forest();
            try {
                while((temp = br.readLine()) != null){
                    String[] temps = temp.split(" ");
                    if(temps.length == 2){
                        Library.insertWord(dic, new Value(temps[0], pos, temps[1]));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            forests.add(dic);
        }

        forestList = forests;
    }

    private static BufferedReader getBufferedReader(String filepath){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filepath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        return bufferedReader;
    }

    public static Forest[] getDics(){
        if(forestList == null){
            getDictionary();
        }
        Forest[] dics = forestList.toArray(new Forest[forestList.size()]);
        return dics;
    }

    public static Segmentor getInstance(){
        if(analysis == null){
            analysis = new Segmentor();
            analysis.setForests(Segmentor.getDics());
        }

        return analysis;
    }

    private Segmentor(){}

    public static List<Word> getWordList(String text){
        Segmentor segmentor = Segmentor.getInstance();

        Result result = segmentor.parseStr(text);
        List<Term> terms = result.getTerms();
        List<Word> wordList = new ArrayList<>();

        for(Term term: terms){
            String wordStr = term.getName();
            String pos = term.getNatureStr();
            Word word = new Word(wordStr);
            word.setPos(pos);
            wordList.add(word);
        }

        return wordList;
    }

    public static void main(String[] args){
        String s = "阿育王息坏目因缘经";
        System.out.println(ToAnalysis.parse(s, Segmentor.getDics()));
        System.out.println(ToAnalysis.parse(s));
    }
}
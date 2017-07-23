package com.buddhism.qa.files;

import java.util.ArrayList;
import java.util.List;

/**
 * 本地资源存放路径
 * Created by TT. Wu on 2017/4/21.
 */
public class FileConfig {

    // 问题标注
    public static final String questionFile = "src\\main\\resources\\question\\questions.xml";
    public static final String questionFile1 = "src\\main\\resources\\question\\questions1.xml";
    public static final String questionFile2 = "src\\main\\resources\\question\\questions2.xml";

    // 模板标注
    public static final String patternFile = "src\\main\\resources\\pattern\\question_stem_pattern.xml";

    // 未分类的佛学百科词典内容
    public static final String[] dictionaryDescriptions = {
            "src\\main\\resources\\dictionary_description\\docs_list0.xml",
            "src\\main\\resources\\dictionary_description\\docs_list1.xml",
            "src\\main\\resources\\dictionary_description\\docs_list2.xml",
            "src\\main\\resources\\dictionary_description\\docs_list3.xml",
            "src\\main\\resources\\dictionary_description\\docs_list4.xml",
            "src\\main\\resources\\dictionary_description\\docs_list5.xml",
            "src\\main\\resources\\dictionary_description\\docs_list6.xml",
            "src\\main\\resources\\dictionary_description\\docs_list7.xml",
            "src\\main\\resources\\dictionary_description\\docs_list8.xml",
            "src\\main\\resources\\dictionary_description\\docs_list9.xml",
            "src\\main\\resources\\dictionary_description\\docs_list10.xml",
            "src\\main\\resources\\dictionary_description\\docs_list11.xml",
            "src\\main\\resources\\dictionary_description\\docs_list12.xml",
            "src\\main\\resources\\dictionary_description\\docs_list13.xml",
            "src\\main\\resources\\dictionary_description\\docs_list14.xml",
            "src\\main\\resources\\dictionary_description\\docs_list15.xml",
            "src\\main\\resources\\dictionary_description\\docs_list16.xml",
            "src\\main\\resources\\dictionary_description\\docs_list17.xml",
            "src\\main\\resources\\dictionary_description\\docs_list18.xml",
            "src\\main\\resources\\dictionary_description\\docs_list19.xml"
    };

    // 已分类的佛学词典条目
    public static final String bpnBook = "src\\main\\resources\\dictionary_word_dic\\b_books.dict";
    public static final String bpnPerson = "src\\main\\resources\\dictionary_word_dic\\p_person.dict";
    public static final String bpnDailyLife = "src\\main\\resources\\dictionary_word_dic\\dl_daily_life.dict";
    public static final String bpnIncantation = "src\\main\\resources\\dictionary_word_dic\\i_incantation.dict";
    public static final String bpnGeography = "src\\main\\resources\\dictionary_word_dic\\g_geography.dict";
    public static final String bpnSect = "src\\main\\resources\\dictionary_word_dic\\s_sect.dict";
    public static final String bpnTemple = "src\\main\\resources\\dictionary_word_dic\\t_temple.dict";
    public static final String bpnDoctrine = "src\\main\\resources\\dictionary_word_dic\\d_doctrine.dict";
    public static final String bpnDailySupply = "src\\main\\resources\\dictionary_word_dic\\ds_daily_supplies.dict";
    public static final String bpnArt = "src\\main\\resources\\dictionary_word_dic\\a_art.dict";
    public static final String bpnPoem = "src\\main\\resources\\dictionary_word_dic\\po_poem.dict";

    // 未分类的佛学词典条目
    public static final String bpnAll = "src\\main\\resources\\dictionary_word_dic\\all.dict";

    // 针对训练集，手工抽取的关键词
    public static final String bpnKeyWord = "src\\main\\resources\\dictionary_word_dic\\key_word.dict";

    // 维基百科内容
    public static final String wikiData = "src\\main\\resources\\wiki\\wiki.xml";

    // 佛学教科书
    public static final String[] textBooks = {
            "src\\main\\resources\\textbook\\佛光教科书.xml",
            "src\\main\\resources\\textbook\\佛学基础书籍.xml",
            "src\\main\\resources\\textbook\\佛学教科书.xml",
            "src\\main\\resources\\textbook\\佛学课本.xml",
            "src\\main\\resources\\textbook\\基础课本选读.xml"
    };

    // 佛学常考题类问答对
    public static final String[] frequentlyAskedQuestions = {
            "src\\main\\resources\\frequently_asked_questions\\佛咒简介.xml",
            "src\\main\\resources\\frequently_asked_questions\\佛教名词.xml",
            "src\\main\\resources\\frequently_asked_questions\\佛教因果定律.xml",
            "src\\main\\resources\\frequently_asked_questions\\佛教基础教义.xml",
            "src\\main\\resources\\frequently_asked_questions\\佛教基础知识.xml",
            "src\\main\\resources\\frequently_asked_questions\\佛经简介.xml",
            "src\\main\\resources\\frequently_asked_questions\\宗教简介.xml"
    };

    public static List<String> getSegmentDirectionary(){
        List<String> files = new ArrayList<>();

        files.add(bpnArt);
        files.add(bpnBook);
        files.add(bpnDailyLife);
        files.add(bpnDailySupply);
        files.add(bpnDoctrine);
        files.add(bpnGeography);
        files.add(bpnIncantation);
        files.add(bpnPerson);
        files.add(bpnPoem);
        files.add(bpnTemple);
        files.add(bpnSect);

        return files;
    }

    public static List<String> getAllWordfiles(){
        List<String> files = new ArrayList<>();

        files.add(bpnArt);
        files.add(bpnBook);
        files.add(bpnDailyLife);
        files.add(bpnDailySupply);
        files.add(bpnDoctrine);
        files.add(bpnGeography);
        files.add(bpnIncantation);
        files.add(bpnPerson);
        files.add(bpnPoem);
        files.add(bpnTemple);
        files.add(bpnSect);

        files.add(bpnAll);
        files.add(bpnKeyWord);

        return files;
    }
}

package com.buddhism.qa.retrieval;

import com.buddhism.qa.model.Answer;
import com.buddhism.qa.model.Question;
import com.buddhism.qa.model.Word;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.MultiPhraseQuery;
import org.apache.lucene.search.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 提供文本查询模板
 * Created by TT. Wu on 2017/5/13.
 */
public class GenerateQuery {
    private static final Logger LOG = LoggerFactory.getLogger(GenerateQuery.class);

    /*term query*/
    private static Query titleQueryFromWords(List<Word> wordList){
        List<Term> terms = new ArrayList<>();
        for(Word word: wordList){
            String wordStr = word.getWordStr();
            terms.add(new Term("title", wordStr));
        }
        Term[] terms1 = terms.toArray(new Term[terms.size()]);
        LOG.info("titleQuery term size"+terms.size());

        MultiPhraseQuery multiPhraseQuery = new MultiPhraseQuery.Builder().add(terms1).setSlop(3).build();
        return multiPhraseQuery;
    }

    private static Query descQueryFromWords(List<Word> wordList){
        List<Term> terms = new ArrayList<>();
        for(Word word: wordList){
            String wordStr = word.getWordStr();
            terms.add(new Term("description", wordStr));
        }
        Term[] terms1 = terms.toArray(new Term[terms.size()]);
        LOG.info("descQuery term size"+terms.size());

        MultiPhraseQuery multiPhraseQuery = new MultiPhraseQuery.Builder().add(terms1).setSlop(3).build();
        return multiPhraseQuery;
    }

    /*
        由题干生成查询
     */
    public static Query generateTitleQueryFromWords(Question question){
        List<Word> words = question.getWords();
        return titleQueryFromWords(words);
    }

    public static Query generateDescQueryFromWords(Question question){
        List<Word> words = question.getWords();
        return descQueryFromWords(words);
    }

    public static Query generateTitleQueryFromKeyWords(Question question){
        List<Word> words = question.getKeyWords();
        Query query = titleQueryFromWords(words);

        if(query == null){
            return generateDescQueryFromWords(question);
        }

        return query;
    }

    public static Query generateDescQueryFromKeyWords(Question question){
        List<Word> words = question.getKeyWords();
        Query query = descQueryFromWords(words);
        if(query == null){
            return generateDescQueryFromWords(question);
        }
        return query;
    }

    /*
        由选项生成查询
     */
    public static Query generateTitleQueryFromAnswerWords(Answer answer){
        List<Word> words = answer.getWords();
        return titleQueryFromWords(words);
    }

    public static Query generateDescQueryFromAnswerWords(Answer answer){
        List<Word> words = answer.getWords();
        return descQueryFromWords(words);
    }

    public static Query generateTitleQueryFromAnswerKeyWords(Answer answer){
        List<Word> words = answer.getKeyWords();
        Query query = titleQueryFromWords(words);

        if(query == null){
            return generateDescQueryFromAnswerWords(answer);
        }

        return query;
    }

    public static Query generateDescQueryFromAnswerKeyWords(Answer answer){
        List<Word> words = answer.getKeyWords();
        Query query = descQueryFromWords(words);
        if(query == null){
            return generateDescQueryFromAnswerWords(answer);
        }
        return query;
    }
}

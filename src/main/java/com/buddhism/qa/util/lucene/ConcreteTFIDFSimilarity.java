package com.buddhism.qa.util.lucene;

import org.apache.lucene.search.similarities.ClassicSimilarity;

/**
 * Created by TT. Wu on 2017/5/14.
 */
public class ConcreteTFIDFSimilarity extends ClassicSimilarity {
    @Override
    public float idf(long docFreq, long numDocs){
        return (float) Math.log10(((double)numDocs+0.5)/((double)docFreq+0.5));
    }

    @Override
    public float tf(float freq){
        return (float) (1 + Math.log10((double)freq)+0.5);
    }
}

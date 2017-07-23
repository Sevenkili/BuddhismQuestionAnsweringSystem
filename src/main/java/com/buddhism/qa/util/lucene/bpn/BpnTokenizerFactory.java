package com.buddhism.qa.util.lucene.bpn;

import org.ansj.lucene6.AnsjAnalyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeFactory;

import java.io.Reader;
import java.util.Map;

/**
 * Created by TT. Wu on 2017/5/15.
 */
public class BpnTokenizerFactory extends TokenizerFactory {
    private Map<String, String> args;

    public BpnTokenizerFactory(Map<String, String> args) {
        super(args);
        this.args = args;
    }

    public Tokenizer create(AttributeFactory factory) {
        return AnsjAnalyzer.getTokenizer((Reader)null, this.args);
    }
}

package com.buddhism.qa.util.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;

import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by TT. Wu on 2017/4/23.
 */
public class SearchTest {
    private static final Logger LOG = LoggerFactory.getLogger(SearchTest.class);

    public static void main(String[] args) throws IOException, ParseException {
        Path dirPath = Paths.get("src\\main\\resources\\lucene_index_directory");
        IndexReader reader = DirectoryReader.open(FSDirectory.open(dirPath));
        IndexSearcher searcher = new IndexSearcher(reader);

        Analyzer analyzer = new StandardAnalyzer();

        QueryParser parser = new QueryParser("description", analyzer);

        Term term = new Term("的");
        Query query1 = new TermQuery(term);

        Query query = parser.parse("一");

        ScoreDoc[] hits = searcher.search(query1,1).scoreDocs;
        System.out.println(hits.length);

        for(int i = 0; i < hits.length; i++){
            System.out.println(hits[i].toString());
            System.out.println(hits[i].score);

            Document doc = searcher.doc(hits[i].doc);
            System.out.println(doc.get("description"));
            System.out.println(doc.get("title"));

        }
    }
}

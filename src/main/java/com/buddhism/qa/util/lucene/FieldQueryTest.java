package com.buddhism.qa.util.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;

/**
 * Created by TT. Wu on 2017/5/6.
 */
public class FieldQueryTest {
    /**
     * XXXPoint类中提供了一些常用的静态工厂查询方法，可以直接用来构建查询语句
     */
    public void testIntPointQuery() throws IOException{
        Directory directory = new RAMDirectory();
        IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(new StandardAnalyzer()));

        Document document = new Document();
        Field intPoint = new IntPoint("age", 11);
        document.add(intPoint);
        intPoint = new StoredField("age", 11);
        document.add(intPoint);
        indexWriter.addDocument(document);

        Field intPoint1 = new IntPoint("age", 22);
        document = new Document();
        document.add(intPoint1);
        intPoint1 = new StoredField("age", 22);
        document.add(intPoint1);
        indexWriter.addDocument(document);

        indexWriter.close();

        //进行查询
        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));

        //精确查询
        Query query = IntPoint.newExactQuery("age", 11);
        ScoreDoc[] scoreDocs = indexSearcher.search(query, 10).scoreDocs;
        for(ScoreDoc scoreDoc: scoreDocs){
            System.out.println("精确查询："+indexSearcher.doc(scoreDoc.doc));
        }

        //范围查询，不包含边界
        query = IntPoint.newRangeQuery("age", Math.addExact(11, 1), Math.addExact(33, -1));
        ScoreDoc[] scoreDocs1 = indexSearcher.search(query, 10).scoreDocs;
        for(ScoreDoc scoreDoc: scoreDocs1){
            System.out.println("范围查询，不包含边界："+indexSearcher.doc(scoreDoc.doc));
        }

        //集合查询
        query = IntPoint.newSetQuery("age", 11, 22, 33);
        ScoreDoc[] scoreDocs2 = indexSearcher.search(query,10).scoreDocs;
        for(ScoreDoc scoreDoc: scoreDocs2){
            System.out.println("集合查询："+indexSearcher.doc(scoreDoc.doc));
        }
    }

    public void testDoublePoint() throws IOException{
        Directory directory = new RAMDirectory();
        IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(new StandardAnalyzer()));

        Document document = new Document();
        Field doublePoint = new DoublePoint("age", 11.1);
        document.add(doublePoint);
        doublePoint = new StoredField("age", 11.1);
        document.add(doublePoint);
        indexWriter.addDocument(document);
        doublePoint = new DoublePoint("age", 22.2);
        document = new Document();
        document.add(doublePoint);
        doublePoint = new StoredField("age", 22.2);
        document.add(doublePoint);
        indexWriter.addDocument(document);
        indexWriter.close();

        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));

        //精确匹配
        Query query = DoublePoint.newExactQuery("age", 11.1);
        ScoreDoc[] scoreDocs = indexSearcher.search(query, 10).scoreDocs;
        for(ScoreDoc scoreDoc: scoreDocs){
            System.out.println("精确匹配: "+indexSearcher.doc(scoreDoc.doc));
        }

        //范围匹配
        Query query1 = DoublePoint.newRangeQuery("age", Math.nextUp(11.1), Math.nextDown(33.3));
        ScoreDoc[] scoreDocs1 = indexSearcher.search(query1, 10).scoreDocs;
        for(ScoreDoc scoreDoc: scoreDocs1){
            System.out.println("范围匹配："+indexSearcher.doc(scoreDoc.doc));
        }
    }



    /**
     * main
     */
    public static void main(String[] args) throws IOException {
        FieldQueryTest fieldQueryTest = new FieldQueryTest();
        fieldQueryTest.testIntPointQuery();
        System.out.println("-----------------------------------------");
        fieldQueryTest.testDoublePoint();
    }
}

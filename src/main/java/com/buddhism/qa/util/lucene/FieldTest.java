package com.buddhism.qa.util.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.BytesRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.attribute.standard.DocumentName;
import java.io.IOException;

/**
 * Created by TT. Wu on 2017/5/5.
 */
public class FieldTest {

    Logger LOG = LoggerFactory.getLogger(FieldTest.class);

    /**
     * IntPoint的使用;
     * FloatPoint, LongPoint, DoublePoint的使用方法类似，不再赘述
     */
    public void addIntPoint(Document document, String name, int value){
        Field field = new IntPoint(name, value);

        document.add(field);

        //要排序，必须要添加一个同名的NumericDocValuesField
        field = new NumericDocValuesField(name, value);
        document.add(field);

        //要存储值，必须添加一个同名的StoredField
        field = new StoredField(name, value);
        document.add(field);
    }

    public void testIntPointSort() throws IOException{
        //创建索引
        Document document = new Document();
        Directory directory = new RAMDirectory();
        IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(new StandardAnalyzer()));

        addIntPoint(document, "intValue", 10);
        indexWriter.addDocument(document);

        document = new Document();
        addIntPoint(document, "intValue", 20);
        indexWriter.addDocument(document);

        document = new Document();
        addIntPoint(document, "intValue", 30);
        indexWriter.addDocument(document);

        indexWriter.close();

        //进行检索
        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));

        //第三个参数为true，代表从大到小进行排序
        SortField intValues = new SortField("intValue", SortField.Type.INT, true);
        TopDocs topDocs = indexSearcher.search(new MatchAllDocsQuery(), 10, new Sort(intValues));
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        for(ScoreDoc scoreDoc: scoreDocs){
            System.out.println(indexSearcher.doc(scoreDoc.doc));
        }
    }

    /**
     * BinaryDocValuesField的使用方法
     */
    public void addBinaryDocValuesField(Document document, String name, String value){
        Field field = new BinaryDocValuesField(name, new BytesRef(value));
        document.add(field);

        // 如需存储，需要添加此行
        field = new StoredField(name, new BytesRef(value));
        document.add(field);
    }

    public void testBinaryDocValuesField() throws IOException{
        Document document = new Document();
        Directory directory = new RAMDirectory();
        IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(new StandardAnalyzer()));

        addBinaryDocValuesField(document, "binaryValue", "1234");
        indexWriter.addDocument(document);

        document = new Document();
        addBinaryDocValuesField(document, "binaryValue", "235");
        indexWriter.addDocument(document);

        indexWriter.close();

        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));
        SortField sortField = new SortField("binaryValue", SortField.Type.STRING_VAL, true);
        TopFieldDocs topFieldDocs = indexSearcher.search(new MatchAllDocsQuery(), 10, new Sort(sortField));
        ScoreDoc[] scoreDocs = topFieldDocs.scoreDocs;

        for(ScoreDoc scoreDoc: scoreDocs){
            System.out.println(indexSearcher.doc(scoreDoc.doc));
        }
    }

    /**
     * StringField的使用
     */
    public void addStringField(Document document, String name, String value){
        Field field = new StringField(name, value, Field.Store.YES);
        document.add(field);

        field = new SortedDocValuesField(name, new BytesRef(value));
        document.add(field);
    }

    public void testStringField() throws IOException{
        Document document = new Document();
        Directory directory = new RAMDirectory();
        IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(new StandardAnalyzer()));

        addStringField(document, "stringValue", "1234");
        indexWriter.addDocument(document);

        document = new Document();
        addStringField(document, "stringValue", "2345");
        indexWriter.addDocument(document);

        document = new Document();
        addStringField(document, "stringValue", "123454");
        indexWriter.addDocument(document);

        indexWriter.close();

        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));
        SortField sortField = new SortField("stringValue", SortField.Type.STRING_VAL);
        TopFieldDocs topFieldDocs = indexSearcher.search(new MatchAllDocsQuery(), 10, new Sort(sortField));
        ScoreDoc[] scoreDocs = topFieldDocs.scoreDocs;
        for(ScoreDoc scoreDoc: scoreDocs){
            System.out.println(indexSearcher.doc(scoreDoc.doc));
        }

    }

    /**
     * TextField的使用
     */
    public void addTextField(Document document, String name, String value) {
        Field field = new TextField(name, value, Field.Store.YES);
        document.add(field);
        field = new SortedDocValuesField(name, new BytesRef(value));
        document.add(field);
    }

    public void testTextFieldSort() throws IOException {
        Document document = new Document();
        Directory directory = new RAMDirectory();
        IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(new StandardAnalyzer()));
        addTextField(document, "textValue", "1234");
        indexWriter.addDocument(document);

        document = new Document();
        addTextField(document, "textValue", "2345");
        indexWriter.addDocument(document);

        document = new Document();
        addTextField(document, "textValue", "12345");
        indexWriter.addDocument(document);

        indexWriter.close();

        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));
        SortField sortField = new SortField("testValue", SortField.Type.STRING_VAL, true);
        TopFieldDocs topFieldDocs = indexSearcher.search(new MatchAllDocsQuery(), 10, new Sort(sortField));
        ScoreDoc[] scoreDocs = topFieldDocs.scoreDocs;
        for(ScoreDoc scoreDoc: scoreDocs){
            System.out.println(indexSearcher.doc(scoreDoc.doc));
        }
    }

    /**
     * main
     */
    public static void main(String[] args) throws IOException {
        FieldTest fieldTest = new FieldTest();
        //fieldTest.testIntPointSort();
        //fieldTest.testBinaryDocValuesField();
        //fieldTest.testStringField();
        fieldTest.testTextFieldSort();
    }
}

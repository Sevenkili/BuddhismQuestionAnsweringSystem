package com.buddhism.qa.util.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.index.*;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;

/**
 * Created by TT. Wu on 2017/5/3.
 */
public class LuceneDemo {

    private Directory directory;
    private String[] ids = {"1", "2"};
    private String[] unIndex = {"NetherLand", "Italy"};
    private String[] unStored = {"Amsterdam has lots of bridges", "Venice has lots of canals"};
    private String[] text = {"Amsterdam", "Venice"};

    IndexWriter indexWriter;
    IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new StandardAnalyzer());

    public void createIndex() throws IOException{
        directory = new RAMDirectory();

        //将索引打印至控制台
        indexWriterConfig.setInfoStream(System.out);
        indexWriter = new IndexWriter(directory, indexWriterConfig);
        indexWriterConfig = (IndexWriterConfig)indexWriter.getConfig();

        FieldType fieldType = new FieldType();
        fieldType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS);
        fieldType.setStored(true);//是否存储
        fieldType.setTokenized(true);//是否分词

        for(int i = 0; i < ids.length; i++){
            Document document = new Document();

            document.add(new Field("id", ids[i], fieldType));
            document.add(new Field("country", unIndex[i], fieldType));
            document.add(new Field("contents", unStored[i], fieldType));
            document.add(new Field("city", text[i], fieldType));

            indexWriter.addDocument(document);
        }

        System.out.println("========================");
        indexWriter.commit();
    }

    public void testDelete() throws IOException{
        RAMDirectory directory = new RAMDirectory();

        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new StandardAnalyzer());
        indexWriterConfig.setInfoStream(System.out);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        Document document = new Document();
        document.add(new IntPoint("id", 1));

        indexWriter.addDocument(document);
        indexWriter.commit();

        //无法删除id为1的document
        indexWriter.deleteDocuments(new Term("id", "1"));
        indexWriter.commit();

        DirectoryReader open = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(open);
        Query query = IntPoint.newExactQuery("id", 1);
        TopDocs search = indexSearcher.search(query, 10);
        System.out.println(search.totalHits);

        indexWriter.deleteDocuments(query);
        indexWriter.commit();
        indexSearcher = new IndexSearcher(DirectoryReader.openIfChanged(open));
        search = indexSearcher.search(query, 10);
        System.out.println(search.totalHits);
    }

    public static void main(String[] args) throws IOException {
        LuceneDemo ld = new LuceneDemo();

        ld.createIndex();
        ld.testDelete();
    }
}

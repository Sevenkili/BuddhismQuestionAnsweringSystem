package com.buddhism.qa.datasource;

import com.buddhism.qa.model.TextEvidence;
import com.buddhism.qa.util.lucene.bpn.BpnAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;
import java.util.List;


/**
 * 适用于文本证据的细粒度分析阶段
 * Created by TT. Wu on 2017/5/14.
 */
public class IndexEvidence {

    /**
     * 为已经粗粒度抽取的证据建立索引
     * @param evidenceList
     * @return
     */
    public static Directory index(List<TextEvidence> evidenceList){
        Directory directory = new RAMDirectory();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new BpnAnalyzer(BpnAnalyzer.TYPE.DIC));
        indexWriterConfig.setSimilarity(new ClassicSimilarity());

        try (IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig)) {

            for(TextEvidence textEvidence: evidenceList){
                String titleText = textEvidence.getTitle();
                String descriptionText = textEvidence.getSnippet();

                Document document = new Document();
                Field title = new StringField("title", titleText, Field.Store.YES);
                Field description = new TextField("description", descriptionText, Field.Store.YES);
                document.add(title);
                document.add(description);
                indexWriter.addDocument(document);
            }

//          indexWriter.forceMerge(1);
            indexWriter.close();
            return directory;

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}

package com.buddhism.qa.datasource.document;

import com.buddhism.qa.files.FileConfig;
import com.buddhism.qa.util.lucene.bpn.BpnAnalyzer;
import com.buddhism.qa.util.xml.DocumentParser;
import com.buddhism.qa.util.xml.WikiParser;
import org.ansj.lucene6.AnsjAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.KeepOnlyLastCommitDeletionPolicy;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 将本地文件加至索引（无参调用wiki数据）
 * Created by TT. Wu on 2017/5/13.
 */
public class IndexFile {
    private static final Logger LOG = LoggerFactory.getLogger(IndexFile.class);
    Map<String, String> data;

    /**
     * 从文件中加载内容至内存
     * @return
     */
    private void getDataMap(){
        WikiParser wikiParser = new WikiParser();
        Map<String, String> map = wikiParser.parseWiki();
        this.data = map;
    }

    private void getDataMap(String filepath){
        DocumentParser documentParser = new DocumentParser(filepath);
        Map<String, String> map = documentParser.parseDocument(filepath);
        this.data = map;
    }

    /**
     * 建立索引
     * @return
     */
    public Directory indexData(){
        this.getDataMap();
        Directory directory = new RAMDirectory();
//        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new BpnAnalyzer(BpnAnalyzer.TYPE.DIC));
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new BpnAnalyzer(BpnAnalyzer.TYPE.DIC));
        try (IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig)) {

            Set<String> keys = this.data.keySet();

            for(String key: keys){
                String value = this.data.get(key);
//                String value = Pretreatment.process(wikiData.get(key));
//                key = Pretreatment.process(key);

                Document document = new Document();
                Field title = new StringField("title", key, Field.Store.YES);
                Field description = new TextField("description", value, Field.Store.YES);
                document.add(title);
                document.add(description);
                indexWriter.addDocument(document);
            }
            indexWriter.forceMerge(1);
            indexWriter.close();
            return directory;

        } catch (IOException e){
            LOG.error("create wiki index failed");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回指定文件的索引目录
     * @param filePath
     * @return
     */
    public Directory indexData(String filePath){
        this.getDataMap(filePath);
        Directory directory = new RAMDirectory();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new BpnAnalyzer(BpnAnalyzer.TYPE.DIC));
//        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new AnsjAnalyzer(AnsjAnalyzer.TYPE.base_ansj));

        try (IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig)) {

            Set<String> keys = data.keySet();

            for(String key: keys){
                String value = data.get(key);
//                String value = Pretreatment.process(wikiData.get(key));
//                key = Pretreatment.process(key);

                Document document = new Document();
                Field title = new StringField("title", key, Field.Store.YES);
                Field description = new TextField("description", value, Field.Store.YES);
                document.add(title);
                document.add(description);
                indexWriter.addDocument(document);
            }
            indexWriter.forceMerge(1);
            indexWriter.close();
            return directory;

        } catch (IOException e){
            LOG.error("create wiki index failed");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回指定文件列表的索引目录
     * @param filePaths
     * @return
     */
    public List<Directory> indexData(String... filePaths){
        List<Directory> directories = new ArrayList<>();

        for(String s: filePaths){
            directories.add(indexData(s));
        }

        return directories;
    }
}

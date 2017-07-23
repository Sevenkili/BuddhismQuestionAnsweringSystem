package com.buddhism.qa.util.lucene;

import com.buddhism.qa.files.FileConfig;
import com.buddhism.qa.util.xml.WikiParser;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;



/**
 * Created by TT. Wu on 2017/4/19.
 */
public class IndexTest {
    private static final Logger LOG = LoggerFactory.getLogger(IndexTest.class);

    public static void main(String[] args) throws IOException {
        WikiParser parser = new WikiParser(FileConfig.wikiData);
        Map<String, String> wikiMap = parser.parseWiki();

        Analyzer analyzer = new StandardAnalyzer();

        Path indexDirectory = Paths.get("src\\main\\resources\\lucene_index_directory");


        Directory directory = FSDirectory.open(indexDirectory);

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(directory, config);

        LOG.info("成功写入IndexWriter");

        for (Map.Entry<String, String> entry: wikiMap.entrySet()){
            String title = entry.getKey();
            String description = entry.getValue();

            Document doc = new Document();
            doc.add(new Field("title", title, TextField.TYPE_STORED));
            doc.add(new Field("description", description, TextField.TYPE_STORED));

            LOG.info(title);
            writer.addDocument(doc);


        }
        LOG.info("成功创建");
        writer.close();
    }

}

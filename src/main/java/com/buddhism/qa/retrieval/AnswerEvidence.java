package com.buddhism.qa.retrieval;

import com.buddhism.qa.datasource.Pretreatment;
import com.buddhism.qa.model.DataSource;
import com.buddhism.qa.model.Question;
import com.buddhism.qa.model.TextEvidence;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 为答案筛选证据
 * Created by TT. Wu on 2017/5/13.
 */
public class AnswerEvidence {
    Map<Map<String, String>, Double> evidenceWithScore;

    /**
     * 从文档库中检索支持选项的文本证据
     * @param query
     * @param source
     * @param directories
     * @return
     * @throws IOException
     */
    public static List<TextEvidence> getEvidence(Query query, DataSource source, Directory... directories) throws IOException {

        List<TextEvidence> textEvidenceList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        for(Directory directory: directories){
            IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));

            TopDocs topDocs = indexSearcher.search(query, 5);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for(ScoreDoc scoreDoc: scoreDocs){
                String title = Pretreatment.removeSpace(indexSearcher.doc(scoreDoc.doc).get("title"));
                String description = Pretreatment.removeSpace(indexSearcher.doc(scoreDoc.doc).get("description"));
                map.put(title, description);
            }
        }

        for(Map.Entry<String, String> entry: map.entrySet()){
            TextEvidence textEvidence = new TextEvidence(source, entry.getKey(), entry.getValue());
            textEvidenceList.add(textEvidence);
        }

        return textEvidenceList;
    }

}

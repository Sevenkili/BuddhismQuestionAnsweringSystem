package com.buddhism.qa.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 文本证据的数据结构
 * Created by TT. Wu on 2017/4/22.
 */
public class TextEvidence {
    // 具体来自哪个数据源
    DataSource dataSource;
    // 支持片段的标题
    String title;
    // 支持片段的文本证据
    String snippet;
    // 对证据的确信度评分
    double score = 1;
    // 由证据可以获得的答案列表
    List<Answer> answerList = new ArrayList();

    public TextEvidence(DataSource dataSource, String title, String snippet) {
        this.dataSource = dataSource;
        this.title = title;
        this.snippet = snippet;
    }

    public TextEvidence(String title, String snippet) {
        this.title = title;
        this.snippet = snippet;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}

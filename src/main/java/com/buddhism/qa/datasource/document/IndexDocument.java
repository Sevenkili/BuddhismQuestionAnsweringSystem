package com.buddhism.qa.datasource.document;

import org.apache.lucene.store.Directory;

import java.util.Map;

/**
 * 为不同来源的文本数据建立索引
 * Created by TT. Wu on 2017/4/23.
 */
public interface IndexDocument {
    /**
     * 从数据源中加载数据，返回键值对
     * @return
     */
    public Map<String,String> getDataMap();

    /**
     * 建立索引，返回索引所在的目录
     * @param data
     * @return
     */
    public Directory indexData(Map<String, String> data);
}

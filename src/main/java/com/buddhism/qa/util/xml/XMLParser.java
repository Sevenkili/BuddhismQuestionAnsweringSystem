package com.buddhism.qa.util.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * Created by TT. Wu on 2017/4/19.
 */
public abstract class XMLParser {

    String filepath;
    SAXReader reader = new SAXReader();
    Document document;

    XMLParser(){}

    XMLParser(String filepath) throws DocumentException {
        this.filepath = filepath;
        this.document = reader.read(new File(filepath));
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) throws DocumentException {
        this.filepath = filepath;
        this.document = reader.read(new File(filepath));
    }

    public Document getDocument(String filepath){
        return this.document;
    }
}

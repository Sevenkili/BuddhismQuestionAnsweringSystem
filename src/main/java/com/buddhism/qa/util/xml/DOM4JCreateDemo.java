package com.buddhism.qa.util.xml;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by TT. Wu on 2017/4/18.
 */
public class DOM4JCreateDemo {
    public static void main(String[] args) throws IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("cars");
        Element supercarElement = root.addElement("superCar")
                .addAttribute("company", "Ferrari");

        supercarElement.addElement("carname")
                .addAttribute("type", "Ferrari 101")
                .addText("Ferrari 101");

        supercarElement.addElement("carname")
                .addAttribute("type", "sports")
                .addText("Ferrari 202");

        // pretty print the document to System.out
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer;
        writer = new XMLWriter(System.out, format);
        writer.write(document);



    }
}

package com.buddhism.qa.util.xml;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * Created by TT. Wu on 2017/4/18.
 */
public class DOM4JParserDemo {
    public static void main(String[] args) {
        String filepath = "src\\main\\resources\\question\\questions.xml";
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new File(filepath));

            System.out.println("root element: "+document.getRootElement().getName());

            Element rootElement = document.getRootElement();

            List<Node> nodes = document.selectNodes("/Questions/Question");

            System.out.println("------------------------------------------------------");

            for(Node node: nodes){
                System.out.println("current element: "+ node.getName());

                System.out.println("id: "+node.selectSingleNode("id").getText());
                System.out.println("question stem: "+node.selectSingleNode("questionStem").getText());
                System.out.println("question type: "+node.selectSingleNode("questionType").getText());
                List<Node> options = node.selectNodes("options/option");
                for(Node option: options){
                    System.out.println("\toption1: "+ option.getText());
                }
                System.out.println("answer: "+ node.selectSingleNode("answer").getText());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

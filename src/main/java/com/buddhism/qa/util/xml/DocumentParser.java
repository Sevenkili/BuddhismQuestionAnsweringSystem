package com.buddhism.qa.util.xml;

import com.buddhism.qa.files.FileConfig;
import com.buddhism.qa.util.segmentation.Segmentor;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by TT. Wu on 2017/5/16.
 */
public class DocumentParser extends XMLParser{
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(DocumentParser.class);

    String filepath = FileConfig.wikiData;

    public DocumentParser(String filepath){
        try {
            this.document = reader.read(new File(filepath));
        } catch (DocumentException e) {
            LOG.error("document exception");
            e.printStackTrace();
        }
    }

//    public DocumentParser(){
//        try {
//            this.document = reader.read(new File(filepath));
//        } catch (DocumentException e) {
//            LOG.error("document exception");
//            e.printStackTrace();
//        }
//    }

    /**
     * 解析.xml文件
     * @return
     */
//    public Map<String, String> parseDocumnet(){
//
//        Map<String, String> map = new HashMap<String, String>();
//
//        if (!Files.isReadable(Paths.get(filepath))){
//            LOG.error("Document directory '" +Paths.get(filepath).toAbsolutePath()+
//                    "' does not exist or is not readable, please check the path");
//            return map;
//        } else {
//            map = parse(filepath);
//            return map;
//        }
//    }

    /**
     * 解析.xml文件
     * @param filepath
     * @return
     */
    public Map<String, String> parseDocument(String filepath){

        Map<String, String> map = new HashMap<String, String>();

        if (filepath == null || filepath == ""){
            filepath = this.filepath;
        }

        if (!Files.isReadable(Paths.get(filepath))){
            LOG.error("Document directory '" +Paths.get(filepath).toAbsolutePath()+
                    "' does not exist or is not readable, please check the path");
            return map;
        } else {
            map = parse(filepath);
            return map;
        }
    }

    /**
     * 解析.xml文件
     * @param filepath
     * @return
     */
    private Map<String, String> parse(String filepath){

        super.document = getDocument(filepath);
        LOG.info("load data from filepath: "+filepath);

        Map<String, String> map = new HashMap<String, String>();

        Element rootElement = document.getRootElement();

        List<Node> nodes = document.selectNodes("/Docs/Doc");

        String title = null;
        String desc = null;

        for(Node node: nodes) {
            Node titleNode = node.selectSingleNode("title");
            Node descNode = node.selectSingleNode("desc");
            if(titleNode == null || descNode == null){
                continue;
            }

            title = titleNode.getText();
            desc = descNode.getText();

            map.put(title, desc);
        }

        if (map.isEmpty()) {
            LOG.info("failed to load wiki data");
        }

        return map;
    }

}

package com.buddhism.qa.util.xml;

import com.buddhism.qa.files.FileConfig;
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
 * Created by TT. Wu on 2017/4/19.
 */
public class WikiParser extends XMLParser {

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(WikiParser.class);

    String filepath = FileConfig.wikiData;

    public WikiParser(String filepath){
        this.filepath = filepath;
        try {
            this.document = reader.read(new File(filepath));
        } catch (DocumentException e) {
            LOG.error("document exception");
            e.printStackTrace();
        }
    }

    public WikiParser(){
        try {
            this.document = reader.read(new File(filepath));
        } catch (DocumentException e) {
            LOG.error("document exception");
            e.printStackTrace();
        }
    }

    /**
     * 解析wiki.xml文件
     * @return
     */
    public Map<String, String> parseWiki(){

        Map<String, String> map = new HashMap<String, String>();

        String filepath = this.filepath;

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
     * 解析wiki.xml文件
     * @param filepath
     * @return
     */
    public Map<String, String> parseWiki(String filepath){

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
     * 解析wiki.xml文件
     * @param filepath
     * @return
     */
    private Map<String, String> parse(String filepath){

        super.document = getDocument(filepath);
        LOG.info("load wiki from filepath: "+filepath);

        Map<String, String> map = new HashMap<String, String>();

        Element rootElement = document.getRootElement();

        List<Node> nodes = document.selectNodes("/Docs/Doc");

        String title = null;
        String desc = null;

        for(Node node: nodes) {
            title = node.selectSingleNode("title").getText();
            desc = node.selectSingleNode("desc").getText();

            map.put(title, desc);
        }

        if (map.isEmpty()) {
            LOG.info("failed to load wiki data");
        }

        return map;
    }

    public static void main(String[] args) {
        String filepath = FileConfig.wikiData;
        WikiParser wp = new WikiParser(filepath);

        Map<String, String> map = wp.parseWiki(filepath);

        Set<String> keys = map.keySet();

        for (String key: keys){
            System.out.println(key);

        }

        System.out.println(keys.size());
    }

}

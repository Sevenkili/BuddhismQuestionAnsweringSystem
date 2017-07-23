package com.buddhism.qa.util.tools;

import java.io.File;
import java.nio.file.Paths;


/**
 * 清空某个文件夹下的所有文件和子文件夹。
 * Created by TT. Wu on 2017/4/23.
 */
public class ClearFile {

    /**
     * 清空lucene索引文件夹下面的所有文件和文件夹
     */
    public static void clearIndexDirectry(){
        String filepath = "src\\main\\resources\\lucene_index_directory";
        deleteFile(filepath);
    }

    /**
     * 清空某个文件夹下的所有文件和子文件夹中的内容
     * @param filepath
     */
    public static void deleteFile(String filepath){
        File file = new File(filepath);
        if(file.isFile()){
            file.delete();
            return;
        }else{
            String[] childFilesPath = file.list();
            for(String childFilePath: childFilesPath){
                deleteFile(file.getAbsolutePath()+"\\"+childFilePath);
            }
        }
    }

    public static void main(String[] args) {
        String directoryPath = "src\\main\\resources\\lucene_index_directory";

        File file = new File(directoryPath);

        String[] childs = file.list();
        for(String child: childs){
            System.out.println(child);
        }

        file = new File(directoryPath);
        deleteFile(directoryPath);

        childs = file.list();
        for(String child: childs){
            System.out.println(child);
        }
    }
}

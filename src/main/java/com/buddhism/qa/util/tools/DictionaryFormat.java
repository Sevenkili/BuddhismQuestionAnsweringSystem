package com.buddhism.qa.util.tools;

import com.buddhism.qa.files.FileConfig;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by TT. Wu on 2017/5/11.
 */
public class DictionaryFormat {
    public List<File> readAllFiles(String filepath){
        File dictionary = new File(filepath);

        if (dictionary.isDirectory()){
            String[] fileList = dictionary.list();
            List<File> fileList1 = new ArrayList<>();

            for(String file: fileList){
                fileList1.add(new File(file));
            }

            return fileList1;
        }
        return null;
    }

    public void writeIntoDictionary(File input) throws IOException {
        StringBuffer sb = new StringBuffer();
        String filename = input.getName();
        String pos = "bpn_" + filename.split("_")[0];

        FileReader fileReader = new FileReader("src\\main\\resources\\dictionary_word\\"+input.getName());
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String temp;

        while((temp = bufferedReader.readLine()) != null){
            sb.append(temp.trim()+"000\n");
        }

        bufferedReader.close();
        fileReader.close();

        FileWriter fileWriter = new FileWriter("src\\main\\resources\\dictionary_word_dic\\" + input.getName() + "t");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(sb.toString());

        bufferedWriter.close();
        fileWriter.close();
    }

    public static void main(String[] args) throws IOException {
        DictionaryFormat dictionaryFormat = new DictionaryFormat();
//        List<File> fileList = dictionaryFormat.readAllFiles("src\\main\\resources\\dictionary_word");
//
//        for (File file: fileList){
//            if (!file.getName().equals("all.dict") && !file.getName().equals("key_word.dict")){
//                dictionaryFormat.writeIntoDictionary(file);
//            }
//            if (file.getName().equals("key_word.dict")){
//                dictionaryFormat.writeIntoDictionary(file);
//            }
//        }

        File file = new File(FileConfig.bpnKeyWord);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Set<String> word = new TreeSet<>();
        String temp;

        while((temp = bufferedReader.readLine()) != null){
            temp = temp.split(" ")[0];
            word.add(temp);
        }

        for(String s: word){
            System.out.println(s);
        }


    }

}

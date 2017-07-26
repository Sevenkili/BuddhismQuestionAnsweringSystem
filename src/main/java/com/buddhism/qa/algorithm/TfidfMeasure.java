package com.buddhism.qa.algorithm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import com.buddhism.qa.model.Sentence;
import com.buddhism.qa.model.Word;
import com.buddhism.qa.util.segmentation.Segmentor;

public class TfidfMeasure {
	
	/**
	 * 获取句子TF-IDF权值向量
	 * @author qichenglin
	 * @param sentence
	 * @param docs
	 * @param allSentence
	 * @return
	 */
	public double[]  getTermFrequency(Sentence sentence, List<Sentence> docs,Sentence allSentence){
		List<Word> allSentenceTermsList = Segmentor.getWordList(allSentence.getSentenceStr());
		List<Word> sentenceTermsList = Segmentor.getWordList(sentence.getSentenceStr());
		double[] tfidfVector = new double[allSentenceTermsList.size()];	
		tfidfVector= getTfidfWeight(allSentenceTermsList, sentenceTermsList, docs);
		return tfidfVector;		
	}
	
	/**
	 * 计算句子TF-IDF值，构造向量
	 * @author qichenglin
	 * @param allSentenceTermsList
	 * @param sentenceTermsList
	 * @param docs
	 * @return
	 */
	
	public double[] getTfidfWeight(List<Word> allSentenceTermsList, List<Word> sentenceTermsList, List<Sentence> docs){
		Map<Word, Integer> wordFrequencyMap = new LinkedHashMap<Word, Integer>(); 
		double[] tfidfVector = new double[allSentenceTermsList.size()] ;
		wordFrequencyMap = generateTermFrequency(sentenceTermsList, allSentenceTermsList);
		int sumTermNum = sumTermFrequency(wordFrequencyMap);
		int i = 0;
		for (Entry<Word, Integer> entry : wordFrequencyMap.entrySet()) {  
			float tf = getTermFrequency(entry.getValue(), sumTermNum);
			float idf = generateInverseDocumentFrequency(docs,entry.getKey());
			BigDecimal bdVector = new BigDecimal(tf * idf);
			tfidfVector[i++] = bdVector.doubleValue();
		}
		return tfidfVector;
	}
	
	

	/**
	 * 计算所有句子中每个词的词频
	 * @author qichenglin
	 * @param sentenceTermsList
	 * @param allSentenceTermsList
	 * @return
	 */
	private Map<Word,Integer> generateTermFrequency(List<Word> sentenceTermsList,List<Word> allSentenceTermsList){
		Word[] words = new Word[sentenceTermsList.size()]; 
		for(int i = 0; i < sentenceTermsList.size(); i++){
			words[i] = sentenceTermsList.get(i);
		}
		Word[] distinctWords = getDistinctWords(allSentenceTermsList, words);
		Arrays.sort(words, new WordStrComparator());	
		Map<Word, Integer> wordFrequencyMap = new LinkedHashMap<Word, Integer>(); 
		int count = 0;
		for(int i = 0; i < distinctWords.length; i++){
			count = CountWords(distinctWords[i],words);
			wordFrequencyMap.put(distinctWords[i], count);
		}
		return wordFrequencyMap;
	}
	/**
	 * 所有句子中所有字词的出现次数之和
	 * @author qichenglin
	 * @param wordFrequencyMap
	 * @return
	 */
	private int sumTermFrequency(Map<Word,Integer> wordFrequencyMap){
		int sumTermNum = 0;
		for (Entry<Word, Integer> entry : wordFrequencyMap.entrySet()) {  
			sumTermNum += entry.getValue();
		}
		return sumTermNum;
	}
	
	/**
	 * 计算TF值
	 * @author qichenglin
	 * @param termFrequency
	 * @param sumNum
	 * @return
	 */
	private float getTermFrequency(int termFrequency, int sumNum){	
		return (float) termFrequency/(float) sumNum;
	}
	
	/**
	 * 二分查找法查询数组元素，如果word在words中，则返回word的索引值
	 * @author qichenglin
	 * @param word
	 * @param words
	 * @return
	 */
	private int CountWords(Word word, Word[] words)
	{
		int itemIdx = binarySearch(words, word);
		if (itemIdx > 0)			
			while (itemIdx > 0 && words[itemIdx].equals(word))				
				itemIdx--;				
					
		int count=0;
		while (itemIdx < words.length && itemIdx >= 0)
		{
			if (words[itemIdx].getWordStr().equals(word.getWordStr())) count++;				
			
			itemIdx++;
			if (itemIdx < words.length)				
				if (!words[itemIdx].getWordStr().equals(word.getWordStr())) break;					
			
		}
		
		return count;
	}
	
	/**
	 * 二分查找
	 * @author qichenglin
	 * @param words
	 * @param word
	 * @return
	 */
	private int binarySearch(Word[] words, Word word) {
		int start = 0;
		int end = words.length-1;
		
		while(start <= end){
			int mid = start + (end - start)/2;
			int compareNum = words[mid].getWordStr().compareTo(word.getWordStr());
			if(compareNum < 0)
				start = mid + 1;
			else if(compareNum > 0)
				end = mid - 1;
			else{
				return mid;
			}
		}
		return -1;
	}
	
	/**
	 * 所有句子的词构成向量
	 * @author qichenglin
	 * @param sentenceTermsList
	 * @param words
	 * @return
	 */
	public Word[] getDistinctWords(List<Word> sentenceTermsList,Word[] words){
		if (sentenceTermsList == null || sentenceTermsList.size() == 0)			
			return new Word[0];			
		else
		{
			List<Word> wordsList = new ArrayList<Word>();
			
            List<Word> list = new ArrayList<Word>();
			for (int i=0; i < sentenceTermsList.size(); i++){
				 wordsList.add(sentenceTermsList.get(i));
				 boolean find = false;
				 for(Word w:list){
					 if(w.getWordStr().equals(sentenceTermsList.get(i).getWordStr())) {
						 find = true;
						 break;
					 }
				 }
				if (!find) {
					//System.out.println(sentenceTermsList.get(i).getWordStr());
					list.add(sentenceTermsList.get(i));
				}
			}
			Word[] v=new Word[list.size()];
			return (Word[]) list.toArray(v);
		}	
	}
	
	private float Log(float num){
		return (float) Math.log(num);
	}
	
	/**
	 * 计算句子总数
	 * @author qichenglin
	 * @param sentences
	 * @return
	 */
	private int getDocNum( List<Sentence> sentences){
		return sentences.size();
	}
	
	/**
	 * 计算某个词在句子中出现的句子数
	 * @author qichenglin
	 * @param docs
	 * @param word
	 * @return
	 */
	private int wordInDocNum(List<Sentence> docs, Word word){
		int wordInDocNum = 0;
		for(Sentence sentence : docs ){
			List<Word> sentenceTermsList = Segmentor.getWordList(sentence.getSentenceStr());
			boolean find = false;
			 for(Word w : sentenceTermsList){
				 if(w.getWordStr().equals(word.getWordStr())) {
					 find = true;
					 break;
				 }
			 }
			if (find) {
				wordInDocNum++;
			}
		}
		return wordInDocNum;
	}
	
	/**
	 * 计算IDF值，逆文档频率
	 * @author qichenglin
	 * @param docs
	 * @param word
	 * @return
	 */
	private float generateInverseDocumentFrequency(List<Sentence> docs,Word word){
		int docNum = getDocNum( docs);
		int wordInDocNum = wordInDocNum(docs,word);
		return Log((float) docNum/(float) wordInDocNum + (float)0.01);
	}
}

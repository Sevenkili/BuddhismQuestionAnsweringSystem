package com.buddhism.qa.score.sentence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.buddhism.qa.algorithm.TermVector;
import com.buddhism.qa.algorithm.TfidfMeasure;
import com.buddhism.qa.model.Sentence;
import com.buddhism.qa.model.TextEvidence;

/**
 * @author qichenglin
 */
public class SentenceMatchEvidenceScore implements SentenceScore{

	/**
     * 输入TextEvidence和问题题干，返回TextEvidence equipped with importantSentenceList
     * 原数据结构中仅包含数据源dataSource，标题title，内容snippet等基本信息
     * 返回的数据结构添加了分析所得的重要（和问题相关度高）的句子
     * @author qichenglin
     * @param evidence
     * @param questionStr
     * @return
     */
	@Override
	public TextEvidence setImportantSentence(TextEvidence evidence,
			String questionStr) {
		// TODO Auto-generated method stub
		List<Sentence> importantSentenceList = new ArrayList<Sentence>();
		String evidenceContent = evidence.getSnippet();
		importantSentenceList = getImportantSentence(evidenceContent, questionStr);
		evidence.setImportantSentenceList(importantSentenceList);
		return evidence;
	}

	/**
     * 选择得分最高的5个句子作为计算结果返回
     * @author qichenglin
     * @param evidenceContent
     * @param questionStr
     * @return
     */
	@Override
	public List<Sentence> getImportantSentence(String evidenceContent,
			String questionStr) {
		// TODO Auto-generated method stub
		if(evidenceContent.equals("")||questionStr.equals(""))
			return null;
		int N = 5;
		Map<Integer,Double> scoreMap = new HashMap<Integer,Double>();
		List<Sentence> importantSentenceList = new ArrayList<Sentence>();
		List<Sentence> evidenceList = CutSentences.getSentencesList(evidenceContent);
		for(int i = 0; i < evidenceList.size(); i++){
			//System.out.println("第"+i+"对：");
			scoreMap.put(i,getSentenceScore(evidenceList.get(i), questionStr));
		}
		List<Integer> topNIndexList = new ArrayList<Integer>(); 
		topNIndexList = getTopNEvidenceIndex(scoreMap, N);
		for(Integer index : topNIndexList){
			Sentence evidenceSentence = evidenceList.get(index);
			evidenceSentence.setScore(scoreMap.get(index));
			importantSentenceList.add(evidenceSentence);
		}
		return importantSentenceList;
	}

	/**
     * 计算具体的某个句子的重要性：
     * 关于计算文本相似度的算法，可以写至com.buddhism.qa.algorithm包中
     * @author qichenglin
     * @param sentence
     * @param questiontr
     * @return
     */
	@Override
	public double getSentenceScore(Sentence sentence, String questionStr) {
		if(sentence == null || sentence.getSentenceStr().equals("") || questionStr.equals(""))
			return -1;
		double score = 1.0;
		double[][] tfidfMatrix = new double[2][];
		tfidfMatrix = getTfidfMatrix(sentence, questionStr);
		
		for(int i = 0; i < tfidfMatrix.length; i++){
			System.out.print("[");
			for(int j = 0; j < tfidfMatrix[i].length; j++)
				System.out.print(tfidfMatrix[i][j]+",");
			System.out.println("]");
		}	
		score = TermVector.ComputeCosineSimilarity(tfidfMatrix[0], tfidfMatrix[1]);
		return score;
	}
	
	/**
	 * 构造问句和证据句的TF-IDF矩阵
	 * @author qichenglin
	 * @param evidenceContent
	 * @param questionStr
	 * @return
	 */
	private double[][] getTfidfMatrix(Sentence evidenceContent, String questionStr){
		StringBuffer allText = new StringBuffer();
		allText.append(questionStr);
		allText.append(evidenceContent.getSentenceStr());
		Sentence allSentence = new Sentence(allText.toString());
		List<Sentence> docs = new ArrayList<Sentence>();
		docs.add(new Sentence(questionStr));
		docs.add(evidenceContent);
		
		TfidfMeasure tfidfMeasure = new TfidfMeasure();
		double[] tfidfVector = new double[docs.size()];
		double[][] tfidfMatrix = new double[docs.size()][];
		for(int i = 0; i < docs.size(); i++){
			tfidfVector	= tfidfMeasure.getTermFrequency(docs.get(i), docs, allSentence);
			tfidfMatrix[i] = tfidfVector;
		}
		return tfidfMatrix;
	}
	
	/**
	 * 获取排名前N的证据句的索引值
	 * @author qichenglin
	 * @param map
	 * @param N
	 * @return
	 */
	private List<Integer> getTopNEvidenceIndex(Map<Integer,Double> map, int N){
		List<Map.Entry<Integer, Double>> list_data = new ArrayList<Map.Entry<Integer, Double>>(map.entrySet()); 
		list_data = sort(map);
		List<Integer> topNIndexList = new ArrayList<Integer>(); 
		if(list_data.size() > N){
			for(int i = 0; i < N; i++){
				topNIndexList.add(list_data.get(i).getKey());
			}
		}else{
			for(int i = 0; i < list_data.size(); i++){
				topNIndexList.add(list_data.get(i).getKey());
			}
		}
		//System.out.println(topWordsList); 
		return topNIndexList;
	}
	/**
	 * 根据分值对证据句进行排序
	 * @author qichenglin
	 * @param map
	 * @return
	 */
	private List<Map.Entry<Integer, Double>> sort(Map<Integer,Double> map){
		List<Map.Entry<Integer, Double>> list_data = new ArrayList<Map.Entry<Integer, Double>>(map.entrySet()); 
		Collections.sort(list_data, new Comparator<Map.Entry<Integer, Double>>() 
				{   
			          public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2)  
			          {  
			        	  if(o2.getValue()!=null&&o1.getValue()!=null&&o2.getValue().compareTo(o1.getValue())>0){  
			        		  return 1;  
			        	  }
			        	  else if(o2.getValue()!=null&&o1.getValue()!=null&&o2.getValue().compareTo(o1.getValue()) == 0){
			        		  return 0;
			        	  }else{  
			        		  return -1;  
			        	  }      
			          }  
			      });  
		return list_data;
	}
}

package com.buddhism.qa.algorithm;

import java.util.Comparator;
import com.buddhism.qa.model.Word;

/**
 * @author qichenglin
 */
public class WordStrComparator implements Comparator<Word>{

	public int compare(Word o1,Word o2) {
		return o1.getWordStr().compareTo(o2.getWordStr());
	}
	
	
}

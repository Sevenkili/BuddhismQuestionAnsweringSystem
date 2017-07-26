package com.buddhism.qa.score.sentence;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buddhism.qa.model.Sentence;
import com.buddhism.qa.score.answer.TextualAlignmentCandidateAnswerScore;

public class CutSentences {
	private static final Logger LOG  = LoggerFactory.getLogger(CutSentences.class);
	@Test
	public void test(){
		String input =  "华严宗，又称贤首宗，汉传佛教的流派之一，此宗以《华严经》为所依，故称为“华严宗”。实际创始人是法藏，但传统上以龙树菩萨为初祖。因法藏受封“贤首国师”，故此宗或称为“贤首宗”。\n" +
                "大唐道璇律师于日本天平八年，赍《华严宗章疏》入日本，新罗之审祥往大唐，从贤首学华严，后至日本，住于大安寺。日本始有华严宗。十宗略说华严为经中之王，秘于龙宫，龙树菩萨乘神通力诵出略本，流传人间。\n" +
                "有唐杜顺和尚者，文殊师利化身也。依经立观，是为初祖。继其道者，云华智俨、贤首法藏以至清凉澄观，而纲目备举，于是四法界、十玄门、六相、五教，经纬于疏钞之海，而华严奥义如日丽中天，有目共睹矣。后之学者，欲入此不思议法界，于诸祖撰述，宜尽心焉。\n" +
                "在判教上尊《华严经》为最高经典，并从《华严经》的思想，发展出法界缘起、十玄、四法界、六相圆融的学说，发挥事事无碍的理论。此派从盛唐立宗，至武宗灭佛后，逐渐衰微。华严宗祖庭为陕西华严寺，现仅存砖塔两座[1]。日本华严宗大本山为奈良东大寺。" +
                "传统上的说法，华严宗的世系以唐之帝心杜顺和尚为始祖，云华智俨法师为二祖，贤首法藏法师为三祖，清凉澄观法师为四祖，圭峰宗密禅师为五祖，宋朝加入马鸣、龙树而为七祖。晋水净源誉为华严宗在北宋的中兴之祖。被认为是华严宗的七祖，或十祖。\n" +
                "境野黄洋考证，华严宗最初是由智正，智现，贤首三代相传，认为杜顺初祖说是后人杜撰。根据凤潭与觉洲的说法，铃木宗忠也考证华严宗并非起源杜顺，认为应该是起源自智俨。宇井伯寿则主张应该是起源自智正，智俨，贤首三代。但是常盘大定则支持传统说法，认为初祖应是杜顺，传智俨、贤首。";
		for(Sentence sentence : getSentencesList(input))
			LOG.info(sentence.getSentenceStr());
	}
	
	/**
	 * 分句
	 * @author qichenglin
	 * @param input
	 * @return
	 */
	public static List<Sentence> getSentencesList(String input){
		List<Sentence> sentencesList = new ArrayList<Sentence>();
		
		/*正则表达式：句子结束符*/  
		String regEx="？|。|！|；|\n|\r\n";    
		Pattern p =Pattern.compile(regEx);   
		Matcher m = p.matcher(input);   
			  
		/*按照句子结束符分割句子*/  
		String[] sens = p.split(input);   
			  
		/*将句子结束符连接到相应的句子后*/  
		if(sens.length > 0)   
		{   
			int count = 0;   
			while(count < sens.length)   
			{   
			   if(m.find())   
			   {   
			        sens[count] += m.group(); 
			        if(!sens[count].equals("\n") && !sens[count].equals("\r\n")){
			        	Sentence sentence = new Sentence(sens[count]);
			        	sentencesList.add(sentence);
			        }
			   }   
			   count++;   
			}   
		}   
		return sentencesList;
	}
}

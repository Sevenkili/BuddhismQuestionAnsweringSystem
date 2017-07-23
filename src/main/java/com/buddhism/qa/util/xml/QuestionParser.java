package com.buddhism.qa.util.xml;

import com.buddhism.qa.files.FileConfig;
import com.buddhism.qa.model.Answer;
import com.buddhism.qa.model.Question;
import com.buddhism.qa.model.QuestionType;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by TT. Wu on 2017/4/22.
 */
public class QuestionParser extends XMLParser {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionParser.class);

    String filepath = FileConfig.questionFile;

    public QuestionParser(String filepath){
        this.filepath = filepath;
        try{
            this.document = reader.read(new File(filepath));
        }catch (DocumentException e){
            LOG.error("document exception");
            e.printStackTrace();
        }
    }

    public QuestionParser(){
        try {
            this.document = reader.read(new File(filepath));
        } catch (DocumentException e) {
            LOG.error("document exception");
            e.printStackTrace();
        }
    }

    /**
     * 解析question.xml文件
     * @return
     */
    public List<Question> parseQuesion(){
        List<Question> questionList = new ArrayList<>();

        if (!Files.isReadable(Paths.get(filepath))){
            LOG.error("Document directory '" +Paths.get(filepath).toAbsolutePath()+
                    "' does not exist or is not readable, please check the path");
            return questionList;
        } else {
            questionList= _parse();
            return questionList;
        }
    }

    /**
     * 解析question文件
     * @return
     */
    private List<Question> _parse(){
        LOG.info("load data from question.xml");

        List<Question> questionList = new ArrayList<>();

        List<Node> nodes = document.selectNodes("/Questions/Question");

        for(Node node: nodes){
            Question question = new Question();

            String questionStr;
            List<String> answers = new ArrayList<>();
            int exceptAnswerId;
            String questiontype;


            //从xml中解析内容
            questionStr = node.selectSingleNode("questionStem").getText();
            List<Node> options = node.selectNodes("options/option");
            for(Node option: options){
                answers.add(option.getText());
            }
            exceptAnswerId = Integer.parseInt(node.selectSingleNode("answer").getText());
            questiontype = node.selectSingleNode("questionType").getText();

            //处理成指定的数据结构
            String[] types = questiontype.split("|");
            List<QuestionType> questionTypeList = new ArrayList<>();
            for(String type: types){
                questionTypeList.add(QuestionType.getQuestionType(type));
            }

            List<Answer> optionList = new ArrayList<>();
            for(String o: answers){
                Answer answer = new Answer();
                answer.setAnswerStr(o);
                optionList.add(answer);
            }

            Answer exceptAnswer = optionList.get(exceptAnswerId-1);

            //封装成对象
            question.setQuestionStr(questionStr);
            question.setOptions(optionList);
            question.setExpectAnswer(exceptAnswer);

            questionList.add(question);
        }

        return questionList;
    }
}

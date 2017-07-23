package test.com.buddhism.qa.score.evidence;

import com.buddhism.qa.analysis.Words;
import com.buddhism.qa.model.Question;
import com.buddhism.qa.model.TextEvidence;
import com.buddhism.qa.model.Word;
import com.buddhism.qa.score.evidence.TermMatchEvidenceScore;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;
import java.util.List;

/**
 * TermMatchEvidenceScore Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>???? 14, 2017</pre>
 */
public class TermMatchEvidenceScoreTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: score(Question question, List<TextEvidence> evidence)
     */
    @Test
    public void testScore() throws Exception {
        Question question = new Question("菩萨");
        question = Words.setWordList(question);

        List<Word> words = question.getWords();
        for (Word word: words){
            System.out.println(word.getWordStr());
        }

        List<TextEvidence> textEvidenceList = new ArrayList<>();

        textEvidenceList.add(new TextEvidence("菩萨", "坐床坐床是藏传佛教寺院中的重大宗教仪式，是活佛传承过程中，转世者由转世灵童正式继任活佛并改称活佛名号的必要仪式。坐床的主要内容是恭敬藏传佛教历代教派祖师以及松赞干布、唐朝文成公主、莲花生、白郎木女神。礼仪上，先由活佛、转世灵童向释迦牟尼像、松赞干布和宗喀巴等各教派传承祖师献哈达。身穿黄色法衣的达赖喇嘛，乘坐黄色来到拉萨，达赖喇嘛在大昭寺向释迦牟尼像、松赞干布、唐朝文成公主、莲花生、白郎木女神的塑像献哈达，并念《成就四业经》。念毕，在布达拉宫的日光殿上，达赖喇嘛与驻藏大臣交换哈达，并向驻藏大使赠送佛像、礼品等。接着即在日光殿上举行坐床礼仪庆祝大会。坐床典礼是藏传佛教的传统法规，当小活佛被寻访并确定其身份后，要进行隆重庄严的坐床仪式。「坐床」是指用来坐的床，只能坐而不能卧。故在佛典中的床与座，往往可以通用。坐床典礼根据活佛的地位各不相同，可以分为大、中、小。在确定「转世灵童」之后，要将「转世灵童」迎接到寺院，这时称为「转世灵童」，在举行了坐床典礼以后，就不再称为「转世灵童」，而称活佛的名号，如达赖喇嘛、班禅额尔德尼等。"));
        textEvidenceList.add(new TextEvidence("上人", "上人上人，佛教术语，对于出家众的一种敬称，通常用于当成长老、上座之称号，类似于和尚、喇嘛、高僧、大德、尊者、大师等。指内有智德、外有胜行的僧人，比如出类拔萃，在人之上，因此有了这个称谓。上人本义是指，与一般人相较，更为出众而杰出的人。后被当成是一种敬称，如在《十诵律》中记载，阿阇世王在未登基为王前，称调达为上人，《维摩诘经》中，文殊师利菩萨称长者维摩诘为上人。唐朝时，中国习惯以上人来作为僧人的敬称。日本的日莲宗与净土真宗中，上人是个敬称，位阶低于圣人。日本平安时代，于864年，设立了法桥上人这个僧位。在室町时代之后，天皇降旨，提及著名僧侣时，多称其为上人。此后，民间习惯以上人来称呼著名的僧人。"));
        textEvidenceList.add(new TextEvidence("乐尊和尚", "十地十地，佛教术语，指大乘佛教修菩萨道行者所要经历的最后十个修行阶段，出自《华严经》十地品及《大方广菩萨十地经》。已发菩提心的菩萨行者，要历经十信、十住、十行、十回向等四十阶位修行福德与智慧资粮，然后进入十地修学。又作极喜地、喜地、悦豫地。主欢喜正法，并先知十地样貌，如知到彼方道路。「菩萨圆满十回向后，成就一分道种智，复勇发十无尽愿。何等为十？一众生不可尽。二世界不可尽。三虚空不可尽。四法界不可尽。五涅槃不可尽。六佛出世不可尽。七诸佛智慧不可尽。八心所缘不可尽。九起智不可尽。十世间转法转智转不可尽。若众生尽。我愿乃尽。若世界。虚空。法界。涅槃。佛出世。诸佛智慧。心所缘。起智。诸转尽。我愿乃尽。而众生实不可尽。世界。虚空。法界。涅槃。佛出世。诸佛智慧。心所缘。起智。诸转。实不可尽。我诸愿善根亦不可尽。」复又永伏性障如阿罗汉，但不俱断，能证慧解脱而不取证，由大愿故留惑润生，尽未来际终不取无余涅槃，利乐众生永无止息。 此地主修法施波罗蜜多及百法明门，证犹如镜像观，满初地心。又作无垢地、净地。初地功德圆满后，再成就一分道种智而入二地。这一阶段主发愿，舍弃自我之心，不舍一切众生。并应多闻佛法。《十住经》：二地亦主修戒波罗蜜及一切种智。又作明地、有光地、兴光地。二地满心后再证一分道种智，故入三地。《佛说十地经》卷3：「第二地中增上意乐善清净已。欲入菩萨第三地者。当以十种心之意乐作意而入。何等为十。所谓以清净心意乐作意。以安住心意乐作意。以厌离心意乐作意。以离欲心意乐作意。以不退心意乐作意。以坚固心意乐作意。以炽然心意乐作意。以勇健心意乐作意。以胜妙心意乐作意。以广大心意乐作意。菩萨以是十心意乐作意证入第三地中瑜伽师地论》「由发闻行。正法光明等持光明之所显示。是故此地名发光地。由内心净能发光明。是故说名增上心住。由此义故名发光地。即由此义当知复名增上心住。」因此故，此地名发光地。又作焰地、增曜地、晖曜地。由三地再证道种智一分故入四地。《佛说十地经》卷3：「欲入菩萨第四智地。当以十种法明而入。何等为十。所谓以有情界思察明入。以诸世界思察明入。以真法界思察明入。以虚空界思察明入。以识界思察明入。以欲界思察明入。以色界思察明入。以无色界思察明入。以妙意乐胜解界思察明入。以广大意乐胜解界思察明入。菩萨以此十法明入升第四地。」"));
        textEvidenceList.add(new TextEvidence("六和敬", "六和敬六和敬，又称六慰劳法、六可憘法、六和、六和精神，佛教术语，为追求菩萨道的修行者在团体生活中要遵循的六种生活态度，也是佛教僧团共住时需遵循的六种生活方法："));

        TermMatchEvidenceScore termMatchEvidenceScore = new TermMatchEvidenceScore();

        termMatchEvidenceScore.score(question, textEvidenceList);
    }

    /**
     * Method: setScoreWeight(ScoreWeight scoreWeight)
     */
    @Test
    public void testSetScoreWeight() throws Exception {
//TODO: Test goes here... 
    }


} 

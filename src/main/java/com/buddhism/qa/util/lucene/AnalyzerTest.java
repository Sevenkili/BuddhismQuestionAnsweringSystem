package com.buddhism.qa.util.lucene;

import com.buddhism.qa.util.lucene.bpn.BpnAnalyzer;
import org.ansj.lucene6.AnsjAnalyzer;
import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.*;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.*;
import org.apache.lucene.util.BytesRef;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.ansj.lucene6.AnsjAnalyzer.TYPE.base_ansj;

/**
 * Created by TT. Wu on 2017/5/6.
 */
public class AnalyzerTest {
    private static final String[] examples = {"内心起瞋恚，要以()对治","人是众因缘和合而生，故名()","一休一休宗纯日本后小松天皇皇子，幼年出家，是室町时代禅宗临济宗的著名奇僧，也是著名的诗人、书法家和画家。法名宗纯，又作宗顺，号一休，乳名千菊丸，训名周建，斋名瞎驴庵、梦闺，别号狂云子、瞎驴庵主人、小清国等。通称一休宗纯，简称一休。一休的父亲是日本南北朝时期的后小松天皇，母亲是日野中纳言的女儿伊予局，一说是藤原显纯的女儿藤侍从。当时的日本在幕府将军足利义满的统治下，结束了长达六十多年的南北对峙的局面，政权中心从镰仓转移到京都，史称室町时代。由于一休的母亲是被击败的南朝权臣藤原氏人，足利义满逼迫后小松天皇将其逐出宫廷。足利义满令一休从小就在京都安国寺出家，以免有后代。一休从未受过皇子的待遇，也从未以皇子自居。其父亲在位期间曾数次召其入宫。一休少时在安国寺出家，训名「周建」，又在京都其他几座寺院学习。1410年，17岁的一休师从西金寺的谦翁。谦翁给他起名“宗纯”。1414年，谦翁过世，一休悲痛不已，自杀未遂，后投入禅门大德寺高僧华叟宗云门下。在大德寺期间，一休在船上听到乌鸦叫声而悟道。1419年，一休25岁时，华叟以“一休”作为他的法号。典故为一休自己作的和歌：“欲从色界返空界，姑且短暂作一休，暴雨倾盘由它下，狂风卷地任它吹。”1428年，华叟病故，34岁的一休开始云游民间，此时室町幕府衰弱，日益受到地方诸侯大名威胁。1471年，一休78岁，遇到盲女艺人森，与之相爱，之后一直照顾她。1474年一休81岁时，受后土御门天皇的诏令，任大德寺第四十七代住持，以修缮因应仁之乱而荒废的寺院。他晚年住在今天京都府京田边市的酬恩庵卯时，一休因高烧不退而圆寂，享年88岁。而日本此时已经进入了地方豪强混战的战国时代。一休与净土真宗的法主莲如是好友，曾持莲如用以念佛的无量寿佛塑像当枕头，莲如知道了，没有生气，而是揶揄了一休一番，两人大笑。一休早年曾严守戒律，但后来却认为禅宗的禁欲教条虚伪，于是开始喝酒吃肉，甚至出入风月场所，对于日本临济宗来说，一休既是锐意革新的圣徒，又是一个离经叛道的颠僧。据说作了不少描述他寻欢作乐，及后来对盲女「森」的爱情诗，可是这些诗文多是在一休死后甚久才出现，可能因为日本的许多艺术家都是一休的门徒，再加上一休行为夸张放荡，经常成为好事者之标的。他的弟子中有不少有才华的人在和歌、连歌、茶道、和画等方面做出的重大贡献。《狂云集续狂云集骸骨自戒集一休法语》和《佛鬼军》等。1975年10月15日到1982年6月28日，共298集，播出时间长达8年之久。日本东映动画出品的卡通片中国大陆译为《聪明的一休》，台湾直接译为《一休和尚》，香港译为《机灵小和尚》）描写一休在安国寺出家的童年时代。剧中，一休总是能以过人的机智解决复杂的难题，成为日本、中国大陆等地的儿童心目中的英雄，他在思考时盘坐用手指在头顶打转的样子也被许多儿童仿效。但实际上，动画内容与史实相差甚大，仅有少部分依照流传于民间的一休生平传说《一休咄》，大部分则多是东映动画编剧组自己所编之原创故事，以及把其他高僧公案甚至东西方各国的机智故事，全张冠李戴套在一休头上。最严重的问题是一休其实是25岁才以悟道诗，获得了「一休」此一别名；他童年在安国寺修行时名叫周建，因此片名其实也是完全不符合历史。2012年日本推出了一休电影真人版。",
    "独乐寺独乐寺，又称大佛寺，位于中国天津市蓟州区西大街，是中国仅存的三大辽代寺院之一，也是中国现存著名的古代建筑之一。寺内现存最古老的两座建筑物山门和观音阁，皆辽圣宗统和二年重建，被公认为辽代建筑的重要代表，两座建筑内的数尊塑像也是与建筑同时的作品，但与建筑一样都历经后代的维修和改动。梁思成曾称独乐寺为“上承唐代遗风，下启宋式营造，实研究我国建筑蜕变之重要资料，罕有之宝物也。”寺内其他建筑都是明代以后修建，包括寺内东部的始建于清代乾隆十八年的行宫。1930年代，独乐寺因相继被日本学者关野贞以及中国学者梁思成调查并公布而闻名海内外。目前，独乐寺被中华人民共和国国务院批准为全国重点文物保护单位，被天津市人民政府批准为特殊保护等级历史风貌建筑。关于独乐寺寺名的由来有三种说法。其一，因观音塑像内部支架是一棵参天而立的大杜梨树，以“杜梨”的谐音而取名；其二，佛家清心寡欲，恪守戒律，独以普度众生为乐，故名独乐寺；其三，独乐寺为安禄山起兵叛唐誓师之地。独乐之名，亦安禄山所命，盖安禄山思独乐而不与民同乐，故而命名。而最后一种说法流传最为广泛。独乐寺始建于唐代贞观二年，安禄山起兵叛唐并在此誓师，据传因其“思独乐而不与民同乐”而得寺名。明代万历二十五年，进士王于陛督饷蓟州时独乐寺曾有过一次大规模修缮。据此记载考证，明代对独乐寺的这次修葺应该在在万历年间后期。而史书中所记载的“重修”，亦仅仅限于独乐寺的油饰彩画，故云“金碧辉映，庄严钜丽”，独乐寺的寺阁结构没有因修缮而更改也。明清之交改朝换代时，当时的蓟城惨遭屠城三次，相传当时蓟州全城人民集中至独乐寺及塔下寺，抵死保护，故城虽遭屠，而寺却无恙，这一历史典故足以表示蓟人对独乐寺的爱护以及独乐寺的历史意义。在王于陛修葺以后六十余年，王弘祚再度修复之。王弘祚以崇祯十四年“自盘阴来牧渔阳”。清代乾隆皇帝曾多次到访独乐寺，并曾赋诗。乾隆十年，清高宗到访独乐寺，作《寄题独乐寺欲留羲御问迁移，便是拈花不语时。丈六金身应好在，春风过客偶相思。禅心远逐穿云磬，古迹空传没字碑。烟柳丝丝新绿嫩，即看拖地有长眉。”后又作《独乐寺——时命重修落成，路便临憩葺修尚识统和年，龙象重教焕法筵。讵有废兴萦白业，徒闻翰墨说青莲。往来几为飞吟兴，布施非关种福田。小憩便教清跸去，我非独乐祗忧先。”在清代时，独乐寺一度成为禁地，平民不得入内。辛亥革命以后，独乐寺复归还于民众，一时香火极盛。民国六年，划拨西院为师范学校，自此的几年中一直为教育用途。民国十三年，陕军来到蓟县，驻扎于独乐寺，是为寺内驻军之始。民国十六年，北洋政府蓟县保安队驻扎在独乐寺，对于独乐寺的装修有所损坏。民国十七年春，驻军阀孙部军队，十八年春才离开。这一年中，对古建的破坏行为最为严重。但是，与当时清东陵盗陵案相比，独乐寺所受到的损坏则较轻。北伐成功以后，中国国民党在蓟县的党部成立，一时破除迷信之声，甚嚣尘上，于是党委中有倡议拍卖独乐寺者。蓟县当地民众纷纷表示不满和反对，因此这一提议未能实现。民国二十年，全寺被划拨为蓟县乡村师范学校，包括观音阁、山门以及东西院座落。东西院及后部正殿皆改为校舍，而观音阁和山门则保存未动。南面栅栏部分被围以土墙，于是无业游民对寺加以无聊之涂抹撕拆。当时蓟县乡村示范学校的主事者对于建筑保护备至。观音阁山门十余年来备受灾难后归学校管理，保护工作逐渐好转，但是社会及政府对于独乐寺等古建的保护犹为亟不容缓。民国二十年5月29日，日本学者关野贞驱车去往清东陵调查途径蓟县县城时，无意间透过车窗发现路边有一座古老的建筑，于是便停车从旁门进入。偶然来到独乐寺的关野贞，一眼便认定这是非常古老的辽代建筑。同年，中国建筑学者梁思成亦有赴蓟县考察的计划。但由于行装甫竣、时局动荡而作罢。民国廿一年，梁思成到独乐寺调查的计划终于成行，调查后整理并发表的学术论文使独乐寺闻名海内外。1961年，独乐寺被中华人民共和国国务院列入第一批全国重点文物保护单位。1966年，文化大革命爆发，当时中国各地频发针对文物特别是宗教建筑的大规模破坏活动。梁思成冒险抵达蓟县，提出要为观音阁“装避雷针、安门窗，为防止鸟类落在观音头像上，要为观音头像上罩铁丝网”，促成国家文化部当年拨款9000元，由河北省古建队施工安装完毕，使独乐寺受到了妥善保护。1972年，观音阁进行修整时发现了壁画。经过考证，这些壁画是清代乾隆十八年的一次大修独乐寺时，被覆盖上一层约1厘米厚的灰而被掩盖的。1976年7月，唐山大地震导致独乐寺院墙倒塌，观音阁墙皮部分脱落，但梁架未见歪闪颇为难得。从此，独乐寺历经千年经受多次地震而不倒塌的独特抗震性能开始成为建筑领域的研究课题。1990年3月，国家文物局批准独乐寺维修工程立项，并列为国家重点古建筑保护修缮工程。1993年，国家文物局将独乐寺列为申报世界文化遗产预备清单项目。1998年，观音阁曾进行过一次维修，维修时从观音阁上取下的木料进行了碳十四年代测定等相关研究，还在观音阁顶上发现了唐代的莲花勾头。独乐寺的主要建筑有山门和观音阁以及始建于清代的行宫等。独乐寺山门即独乐寺的大门，其面阔三间，进深二间，共有柱十二根，单檐庑殿顶。当心间面阔6.10米，中柱间安装大门，为出入独乐寺的孔道。梢间面阔5.23米，南面二间立哼哈二将两天王像，北面二间原来是否有像尚待考证。中柱与前后檐柱间之进深为4.38米。因进深较少于梢间面阔，故垂脊与正脊相交乃在梢间之内而不正在中柱之上。台基为石质，颇低；高约0.50米。前后台出约2.20米，而两山台出则为1.30米，显然不备行人绕门或在两山檐下通行者。南面石阶三级，颇短小，宽不及一间，殆非原状。盖阶之“长随间广”，自李明仲至于今日，尚为定例，明仲前百年，不宜有此例外也。北面石阶已毁，当与南面同。由于台基低矮，斗拱雄大，出檐深远，而脊端鸱尾形制成遒劲，给人以庄严稳固的感觉；内部不用天花，斗拱、梁、檩等构件全部显露可见，因而它们的装饰效果得到充分发挥；通过山门后部的明间，恰可把观音阁收入视线范围之内，既无遮挡，也无多大空隙，这种空间关系处理，显然是建造者有意设计。顶注四阿，脊作鸱尾，青瓦红墙。南面额曰“独乐寺”，相传出自严嵩手笔。全部权衡，与明清建筑物大异，所呈现象至为庄严稳固。在小建筑物上，施以四阿，尤为后世所罕见。独乐寺内观音阁，高23米，是中国现存最早的木结构楼阁，被认为是保护阁内的巨型观音像而修建。观音阁下檐上高悬“观音之阁”匾额，落款为“太白”，相传为唐代李白所写，这一传说得到了历史学家、文物鉴定家史树青的考证和认同。独乐寺内观音阁的形式，与寺内石碑中的记载相符，“上下两级，东西五间，南北八架”。观音阁是一座三层的木构楼阁，其中第二层是暗层，外观是两层，面阔五间，进深四间，使用了24种不同的斗拱榫接，没有使用任何钉子，在历史上经历了多次地震，但仍然屹立不倒，是建筑史上的奇迹。观音阁中央置有一座造型精美的观音像，高16米，穿过中间三层空井，直达屋顶，头顶有十尊小佛像，所以也被称为“十一面观音”，是中国现存最大的泥塑像。第三层明间在主像上覆以藻井，左右次间则用平綦。阁的外形，因台基较低矮，各层柱子略向内倾斜，下檐上面四周建平坐，上层覆以坡度和缓的歇山式屋顶，从而在造型上兼有唐朝雄健和宋朝柔和的特色，是辽朝建筑的一个重要实例。主殿内的观音菩萨像，是辽代泥塑艺术的珍品和中国现存最大的古代观音塑像。殿内的辽代彩塑珍品及壁画，和唐代仕女画一脉相承，这是古代雕塑家以当时生活的人物形象施于佛图的典型实例。独乐寺内的行宫始建于乾隆十八年，因而又称乾隆行宫，是清代皇帝去清东陵谒陵途中小憩的地方，也是天津地区现今仅存的一处皇帝行宫。乾隆之后的皇帝也都来过独乐寺行宫，并留下了大量赞美独乐寺的诗篇。2004年，清代行宫由政府出资被依照旧貌修缮，修缮过程中乾隆皇帝亲手写的、目前保存成组规模最大的28块107篇御笔碑刻全部都镶进了行宫的回廊之中。行宫现存有正殿一层，附属建筑三间，修复过程中恢复了回廊十四间，垂花门一座，使清代行宫可以独立成院。建筑学家梁思成曾在其著作《蓟县独乐寺观音阁山门考》中称独乐寺为“上承唐代遗风，下启宋式营造，实研究我国建筑蜕变之重要资料，罕有之宝物也。”辽代统和二年，即宋太宗雍熙元年。阁之再建，实在北宋初年。《营造法式》是中国现存最古老的关于营造术的典籍，亦为现存研究宋代建筑的唯一著述。《宋代营造》初刊于宋哲宗元符三年，当时再次修建的独乐寺观音阁已经有116年历史。而统和二年，距离唐朝结束的昭宣帝天佑四年仅有77年。以年月论，距离唐末尚近于宋代营造法式刊行之年。而且蓟县在当时地处边境之地，在地理上与中原地区距离较远、比较隔绝。梁思成分析，在唐代地属中国，其文化自直接受中原影响，五代以后，地属夷狄，中国原有文化，固自保守，然在中原若有新文化之产生，则所受影响，必因当时政治界限而隔阻，梁思成据此判断在观音阁再建之时，中原建筑若已有新变动之发生，在蓟北未必受其影响，而保存唐代特征亦比较多。如观音阁者，实唐宋二代间建筑之过渡形式，而研究上重要之关键也。梁思成在发现独乐寺之初即强调建筑物所具有的突出的唐代风格，并且推测独乐寺创建于唐朝初年。鉴于最早提及独乐寺名称的辽代碑文就称统和二年的工程为“再建”以及“重塑”，因此建筑和雕塑应是984年以前创建。而除了梁思成提到的观音阁的建筑整体风格以及观音阁两尊胁侍的风格唐风浓郁之外，近年天津大学建筑学院辽代建筑研究课题组进而发现了另外几方面的证据，从而把独乐寺观音阁的始建年代更为明确的推至唐代前期的武则天朝。而1998年观音阁维修时，从对观音阁使用的移用木构件进行了碳十四测年，发现最早的距今1555±60年，此外还推测出在唐代两个重要时期即“安史之乱”和“会昌灭佛”前后，观音阁也分别有一次维修。针对以独乐寺观音阁为代表的辽代建筑的基本模数尺度的推定，共有过不少于五次的详细研究。陈明达在对独乐寺研究后表示：“若论技术之精湛，艺术之品第，均应推为第一，可以说是现存古建筑中的上上品。”目前，独乐寺被中华人民共和国国务院批准为全国重点文物保护单位，被天津市人民政府批准为特殊保护等级历史风貌建筑。梁思成曾在其著作《蓟县独乐寺观音阁山门考》中对于独乐寺的保护工作进行了大篇幅的论述和具体的建议。他认为保护独乐寺，应当首先引起社会对独乐寺文物保护的关注，使公众知晓知独乐寺在文化上之价值；以及独乐寺观音阁的阁门在中国文化史上及中国建筑史上之价值。除此之外，还应及时翻修屋瓦避免水的侵入使木材腐朽，同时要注意防火和避雷，在屋脊之上安装避雷针。在翻修屋瓦的基础上，将缺损的门窗补制，其他建筑构件如无十足把握则应原样保护。同时，梁思成呼吁在社会方面，则政府立法保护。军队之大规模破坏，游人题壁窃砖，皆须同样禁止。2000年以后，独乐寺曾遭鼠耳蝠的危害，导致独乐寺中观音阁内不停出现木屑和泥土，由于蝙蝠的排泄也导致观音塑像的腋下也出现大片洇湿和裂缝，寺中的元代壁画出现类似钉子眼般的剥落。由于独乐寺为木质结构建筑，因而景区管理者每年都进行多次防火演习。"};
    private static final Analyzer[] analyzers = {
            new WhitespaceAnalyzer(),
            new SimpleAnalyzer(),
            new StopAnalyzer(),
            new KeywordAnalyzer(),
            new StandardAnalyzer(),
            new SmartChineseAnalyzer(),
            new CJKAnalyzer(),
            new AnsjAnalyzer(base_ansj),
            new BpnAnalyzer(BpnAnalyzer.TYPE.DIC)
    };

    public void testAnalyzer() throws IOException{
        for(int i = 0; i < analyzers.length; i++){
            String simpleName = analyzers[i].getClass().getSimpleName();
//            System.out.println(simpleName);

            for(int j = 0; j < examples.length; j++){
                TokenStream contents = analyzers[i].tokenStream("contents", examples[j]);

                OffsetAttribute offsetAttribute = contents.addAttribute(OffsetAttribute.class);
                TypeAttribute typeAttribute = contents.addAttribute(TypeAttribute.class);

                contents.reset();

                System.out.println(simpleName+" analyzing: "+examples[j]);

                while(contents.incrementToken()){
                    String s1 = offsetAttribute.toString();

                    int i1 = offsetAttribute.startOffset();
                    int i2 = offsetAttribute.endOffset();

                    System.out.print(s1 + "[" + i1 + "," + i2 + ":" + typeAttribute.type() + "]" + " ");
                }
                contents.end();
                contents.close();
                System.out.println();
                System.out.println();
            }
        }
    }

    public void testTokenStream() throws IOException{
        Analyzer analyzer = new SmartChineseAnalyzer();
        String inputText = "我是一名中国共产党党员";

        TokenStream tokenStream = analyzer.tokenStream("text", new StringReader(inputText));
        //保存token字符串
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        //在调用incrementToken（）开始消费token之前需要重置stream到一个干净的状态。
        tokenStream.reset();

        while(tokenStream.incrementToken()){
            System.out.println("[" + charTermAttribute + "]");
        }
    }

    public void testAttribute() throws IOException{
        Analyzer analyzer = new StandardAnalyzer();
        String input = "This is a test text for attribute! Just add-some word.";

        TokenStream tokenStream = analyzer.tokenStream("text", new StringReader(input));
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        PositionIncrementAttribute positionIncrementAttribute = tokenStream.addAttribute(PositionIncrementAttribute.class);
        OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
        TypeAttribute typeAttribute = tokenStream.addAttribute(TypeAttribute.class);
        PayloadAttribute payloadAttribute = tokenStream.addAttribute(PayloadAttribute.class);

        payloadAttribute.setPayload(new BytesRef("just"));

        tokenStream.reset();

        while(tokenStream.incrementToken()){
            System.out.println("[" + charTermAttribute
                    + " increment:" + positionIncrementAttribute.getPositionIncrement()
                    + " start:" + offsetAttribute.startOffset()
                    + " end:" + offsetAttribute.endOffset()
                    + " type:" + typeAttribute.type()
                    + " payload:" + payloadAttribute.getPayload()
                    + "]\n");
        }

        tokenStream.end();
        tokenStream.close();
    }

    public static void main(String[] args) throws IOException {
        AnalyzerTest analyzerTest = new AnalyzerTest();
//        analyzerTest.testAnalyzer();
//        analyzerTest.testTokenStream();
//        analyzerTest.testAttribute();
        TestTokenFilter testTokenFilter = new TestTokenFilter();
        testTokenFilter.test();
    }

}

class TestTokenFilter{
    public void test() throws IOException{
        String text = "Hi, Dr Wang, Mr Liu asks if you stay with Mrs Liu yesterday!";
        Analyzer analyzer = new WhitespaceAnalyzer();
        CourtesyTitleFilter courtesyTitleFilter = new CourtesyTitleFilter(analyzer.tokenStream("text", text));
        CharTermAttribute charTermAttribute = courtesyTitleFilter.addAttribute(CharTermAttribute.class);
        courtesyTitleFilter.reset();

        while (courtesyTitleFilter.incrementToken()){
            System.out.println(charTermAttribute+" ");
        }
    }
}

/**
 * 自定义的词过滤器
 */
class CourtesyTitleFilter extends TokenFilter{
    Map<String, String> courtesyTitleMap = new HashMap<>();
    private CharTermAttribute termAttribute;

    protected CourtesyTitleFilter(TokenStream input){
        super(input);
        termAttribute = addAttribute(CharTermAttribute.class);
        courtesyTitleMap.put("Dr", "doctor");
        courtesyTitleMap.put("Mr", "mister");
        courtesyTitleMap.put("Mrs", "miss");
    }

    @Override
    public boolean incrementToken() throws IOException {
        if(!input.incrementToken()){
            return false;
        }else{
            String small = termAttribute.toString();
            if(courtesyTitleMap.containsKey(small)){
                termAttribute.setEmpty().append(courtesyTitleMap.get(small));
            }
        }
        return true;
    }
}


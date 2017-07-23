package test.com.buddhism.qa.datasource.document;

import com.buddhism.qa.datasource.document.IndexFile;
import com.buddhism.qa.files.FileConfig;
import org.apache.lucene.store.Directory;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.List;

/**
 * IndexFile Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ÎåÔÂ 16, 2017</pre>
 */
public class IndexFileTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: indexData()
     */
    @Test
    public void testIndexData() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: indexData(String filePath)
     */
    @Test
    public void testIndexDataFilePath() throws Exception {
        IndexFile indexFile = new IndexFile();
//        List<Directory> directory = indexFile.indexData(FileConfig.frequentlyAskedQuestions);
        Directory directory = indexFile.indexData(FileConfig.frequentlyAskedQuestions[1]);
    }


    /**
     * Method: indexData(String... filePaths)
     */
    @Test
    public void testIndexDataFilePaths() throws Exception {
//TODO: Test goes here... 
    }


    /**
     * Method: getDataMap()
     */
    @Test
    public void testGetDataMap() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = IndexFile.getClass().getMethod("getDataMap"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: getDataMap(String filepath)
     */
    @Test
    public void testGetDataMapFilepath() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = IndexFile.getClass().getMethod("getDataMap", String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 

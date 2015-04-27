import org.abutaleb.amin.ticketmachine.algorithm.Trie;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.*;

/**
 * Created by taleba on 27/04/2015.
 */
public class SearchTest {

    private Trie trie;

    @BeforeSuite
    public void populateData() throws IOException {
        trie = new Trie();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("data").getFile());
        BufferedReader br = new BufferedReader(new FileReader(file));

        String sCurrentLine;
        while ((sCurrentLine = br.readLine()) != null) {
            trie.insert(sCurrentLine.trim());
        }
    }

    @Test
    public void dummyTest(){
        Assert.assertTrue(true);
    }
}

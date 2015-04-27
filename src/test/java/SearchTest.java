import org.abutaleb.amin.ticketmachine.algorithm.Trie;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by taleba on 27/04/2015.
 */
public class SearchTest {

    private Trie trie;

    @BeforeSuite
    public void populateData() throws IOException {
        trie = new Trie();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("testdata").getFile());
        BufferedReader br = new BufferedReader(new FileReader(file));

        String sCurrentLine;
        while ((sCurrentLine = br.readLine()) != null) {
            trie.insert(sCurrentLine.trim());
        }
    }

    @Test(description = "Searches for one of the words")
    public void searchComplete(){
        Long start = System.currentTimeMillis();
        String sample = "Bellingham";

        List<String> words = trie.search(sample);

        Assert.assertFalse(words.isEmpty());
        Assert.assertEquals(words.size(), 1);
        Assert.assertEquals(words.get(0), sample);
        Reporter.log("searchComplete: " + (System.currentTimeMillis() - start) + " millis", true );
    }

    @Test(description = "Searches for the first half of one of the words")
    public void searchFirstHalf(){
        Long start = System.currentTimeMillis();
        String sample = "Bellin";

        List<String> words = trie.search(sample);

        Assert.assertFalse(words.isEmpty());
        Assert.assertEquals(words.size(), 1);
        Assert.assertEquals(words.get(0), "Bellingham");

        Reporter.log("searchFirstHalf: " + (System.currentTimeMillis() - start) + " millis", true );
    }

    @Test(description = "Searches for the first half of one of the words")
    public void searchMultipleResults(){
        Long start = System.currentTimeMillis();
        String sample = "B";
        List<String> words = trie.search(sample);

        Assert.assertFalse(words.isEmpty());
        Assert.assertEquals(words.size(), 35);
        Reporter.log("searchMultipleResults: " + (System.currentTimeMillis() - start) + " millis", true );
    }
}

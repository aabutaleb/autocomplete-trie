import org.abutaleb.amin.ticketmachine.algorithm.Trie;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.*;
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
        File file = new File(classLoader.getResource("data").getFile());
        BufferedReader br = new BufferedReader(new FileReader(file));

        String sCurrentLine;
        while ((sCurrentLine = br.readLine()) != null) {
            trie.insert(sCurrentLine.trim());
        }
    }

    @Test(description = "Searches for one of the words")
    public void searchComplete(){
        String sample = "Amin";

        List<String> words = trie.search(sample);

        Assert.assertFalse(words.isEmpty());
        Assert.assertEquals(words.size(), 1);
        Assert.assertEquals(words.get(0), sample);
    }
}

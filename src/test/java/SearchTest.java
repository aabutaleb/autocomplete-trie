import org.abutaleb.amin.autocomplete.Trie;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
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
        Reporter.log("searchComplete: " + (System.currentTimeMillis() - start) + " millis", true );

        Assert.assertFalse(words.isEmpty());
        Assert.assertEquals(words.size(), 1);
        Assert.assertEquals(words.get(0), sample);
    }

    @Test(description = "Searches for the first half of one of the words")
    public void searchFirstHalf(){
        Long start = System.currentTimeMillis();
        String sample = "Bellin";

        List<String> words = trie.search(sample);
        Reporter.log("searchFirstHalf: " + (System.currentTimeMillis() - start) + " millis", true );

        Assert.assertFalse(words.isEmpty());
        Assert.assertEquals(words.size(), 1);
        Assert.assertEquals(words.get(0), "Bellingham");

    }

    @Test(description = "Searches for the first half of one of the words")
    public void searchMultipleResults(){
        Long start = System.currentTimeMillis();
        String sample = "B";
        List<String> words = trie.search(sample);
        Reporter.log("searchMultipleResults: " + (System.currentTimeMillis() - start) + " millis", true );

        Assert.assertFalse(words.isEmpty());
        Assert.assertEquals(words.size(), 35);
    }

    @Test(description = "Searches for the first half of one of the words")
    public void loadMultipleSearches(){
        Long start = System.currentTimeMillis();
        List<String> words1 = trie.search("Car");
        List<String> words2 = trie.search("Clapham ");
        List<String> words3 = trie.search("Sutton");
        List<String> words4 = trie.search("Upper");
        Reporter.log("loadMultipleSearches: " + (System.currentTimeMillis() - start) + " millis", true);

        Assert.assertFalse(words1.isEmpty());
        Assert.assertFalse(words2.isEmpty());
        Assert.assertFalse(words3.isEmpty());
        Assert.assertFalse(words4.isEmpty());
        Assert.assertEquals(words1, Arrays.asList(new String[]{"Carpenders Park", "Carshalton", "Carshalton Beeches"}));
        Assert.assertEquals(words2, Arrays.asList(new String[]{"Clapham High Street", "Clapham Junction"}));
        Assert.assertEquals(words3, Arrays.asList(new String[]{"Sutton", "Sutton Common"}));
        Assert.assertEquals(words4, Arrays.asList(new String[]{"Upper Holloway", "Upper Warlingham"}));
    }
}

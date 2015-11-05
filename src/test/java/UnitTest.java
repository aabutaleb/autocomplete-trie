import org.abutaleb.amin.autocomplete.Trie;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by taleba on 27/04/2015.
 */
public class UnitTest {

    @Test
    public void emptyTrieTest(){
        Trie trie = new Trie();
        List<String> results = trie.search("Search string");

        Assert.assertNotNull(results);
        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void nullSearchTest(){
        Trie trie = new Trie();
        List<String> results = trie.search(null);

        Assert.assertNotNull(results);
        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void emptySearchTest(){
        Trie trie = new Trie();
        List<String> results = trie.search("");

        Assert.assertNotNull(results);
        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void spaceInContainedElementSearch(){
        Trie trie = new Trie();
        List<String> expected = Arrays.asList(new String[]{"To me"});

        trie.insert("To");
        trie.insert("To me");

        List<String> results = trie.search("to ");

        Assert.assertNotNull(results);
        Assert.assertFalse(results.isEmpty());
        Assert.assertEquals(results.size(), 1);
        Assert.assertEquals(results, expected);
    }

    @Test
    public void containedElementSearch(){
        Trie trie = new Trie();
        List<String> expected = Arrays.asList(new String[]{"Home", "HomeWork", "HomeWork repeated"});

        trie.insert("Home");
        trie.insert("HomeWork");
        trie.insert("HomeWork repeated");

        List<String> results = trie.search("Home");

        Assert.assertNotNull(results);
        Assert.assertFalse(results.isEmpty());
        Assert.assertEquals(results.size(), 3);
        Assert.assertEquals(results, expected);
    }

    @Test
    public void intruderWords(){
        Trie trie = new Trie();

        trie.insert("Home");
        trie.insert("New Home");

        List<String> results = trie.search("Home");

        Assert.assertNotNull(results);
        Assert.assertFalse(results.isEmpty());
        Assert.assertEquals(results.size(), 1);
        Assert.assertEquals(results, Arrays.asList(new String[]{"Home"}));
    }
}

package org.abutaleb.amin.ticketmachine.algorithm;

import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by Amin Abu-Taleb on 27/04/2015.
 */

/**
 * Trie is an implementation of the Trie data structure, perfect for this kind of searches:
 * http://en.wikipedia.org/wiki/Trie
 *
 * It creates a new level in the tree with each letter of the word. This way you can find all the words
 * starting by the given combination.
 *
 * Its super fast for readings but keeps all the dictionary in memory. Although seems to fit with the requirements of
 * the problem
 *
 */
public class Trie {

    private Node root = new Node();

    /**
     * Each node is a map of its children, with the corresponding lable.
     *
     * If the node is also the final letter of a word, {@code isEdge()} will be true
     * and {@code getValue()} will return the word itself. So that a word can contain
     * multiple words at the same time.
     *
     */
    private class Node extends TreeMap<Character, Node>{
        private Character label;
        private boolean edge = false;
        private String value;

        public Node() {
            super();
        }

        public Node(Character character){
            super();
            this.setLabel(character);
        }

        public Node search(Character character){
            return this.get(character);
        }

        public Node insert(Character character){
            Node n = get(character);
            if (n == null){
                n = new Node(character);
                put(character, n);
                return n;
            }
            else{
                return get(character);
            }
        }

        public Character getLabel() {
            return label;
        }

        public void setLabel(Character label) {
            this.label = label;
        }

        public boolean isEdge() {
            return edge;
        }

        public void setEdge(boolean edge) {
            this.edge = edge;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "label=" + label +
                    ", edge=" + edge +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    /**
     * This method will insert words into the Trie
     *
     * @param string
     */
    public void insert(String string){
        Node node = root;
        char[] cArray = string.toLowerCase().toCharArray();
        for (char c: cArray){
            node = node.insert(c);
        }
        node.setValue(string);
        node.setEdge(true);
    }

    /**
     * This method searches words inside the Trie
     *
     * @param s String (case insensitive)
     * @return null if none of the words matches the pattern, {@code List} of words any of them does.
     */
    public List<String> search(String s){
        List<String> results = new ArrayList<String>();
        Node node = root;

        if (s != null && !s.isEmpty()) {
            char[] cArray = s.toLowerCase().toCharArray();

            for (int i = 0; i < cArray.length; i++) {
                char c = cArray[i];
                Node child = node.search(c);
                if (child == null || child.isEmpty()) {
                    break;
                } else if (!((i == cArray.length-1) && child.isEdge())){ //Last element contains a word itself
                    node = child;
                }
            }

            results = findWords(node);
        }

        return results;
    }

    /**
     * Private helper method to find all the words under the given node
     *
     * @param node Last letter of the pattern
     * @return List of the words that match the given pattern. Internally all the word-nodes under the given node.
     */
    private List<String> findWords(Node node) {
        List<String> words = new ArrayList<String>();

        if (node != null && !node.isEmpty()) {
            Set<Map.Entry<Character, Node>> entrySet = node.entrySet();
            for (Map.Entry<Character, Node> entry : entrySet) {
                Node child = entry.getValue();
                if (child.isEdge()) {
                    words.add(child.getValue());
                }
                words.addAll(findWords(child));
            }
        }
        return words;
    }
}

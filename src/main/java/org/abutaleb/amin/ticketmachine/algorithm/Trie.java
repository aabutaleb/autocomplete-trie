package org.abutaleb.amin.ticketmachine.algorithm;

import java.util.*;

/**
 * Created by Amin Abu-Taleb on 27/04/2015.
 */
public class Trie {

    private Node root = new Node();

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

    public void insert(String string){
        Node node = root;
        char[] cArray = string.toLowerCase().toCharArray();
        for (char c: cArray){
            node = node.insert(c);
        }
        node.setValue(string);
        node.setEdge(true);
    }

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

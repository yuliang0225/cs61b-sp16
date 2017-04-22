import java.nio.file.*;
import java.io.*;
import java.util.*;

public class Trie {

    // Trie节点类
    private class TrieNode {
        private Map<Character, TrieNode> children;
        private boolean wordEnd;

        public TrieNode() {
            children = new HashMap<Character, TrieNode>();
            wordEnd = false;
        }
    }


    private TrieNode root;


    public Trie() {
        root = new TrieNode();
    }


    // 插入字符串
    public void insert(String word) {
        TrieNode node = root;
        int length = word.length();

        for (int i=0; i<length; i++) {
            char letter =  word.charAt(i);
            if ( !node.children.containsKey(letter) ) {
                node.children.put(letter, new TrieNode());
            }
            node = node.children.get(letter);
        }
        node.wordEnd = true;
    }


    // 输出所以字符串
    public void print() {
        StringBuilder word = new StringBuilder();
        TrieNode node = root;
        print(node ,word);
    }

    private void print(TrieNode node, StringBuilder word) {
        if ( node.wordEnd ) {
            System.out.println(word);
        }

        if ( node.children.isEmpty() ) {
            return;
        }

        Iterator iter = node.children.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            print((TrieNode)entry.getValue(), word.append((Character)entry.getKey()));
            word.deleteCharAt(word.length() - 1);
        }

    }


    // 查找字符串
    public boolean find(String word) {
        TrieNode node = root;
        int length = word.length();

        for (int i=0; i<length; i++) {
            char letter = word.charAt(i);
            if ( !node.children.containsKey(letter) ) {
                return false;
            }
            node = node.children.get(letter);
        }

        return node.wordEnd;
    }


    // 查找前缀
    public boolean findPrefix(String word) {
        TrieNode node = root;
        int length = word.length();

        for (int i=0; i<length; i++) {
            char letter = word.charAt(i);
            if ( !node.children.containsKey(letter) ) {
                return false;
            }
            node = node.children.get(letter);
        }

        return true;
    }


    // 删除字符串
    public void delete(String word) {
        if ( !find(word) ) {
            System.out.printf("No word %s", word);
            return;
        }

        TrieNode node = root;
        delete(node, word);
    }

    private boolean delete(TrieNode node, String word) {
        int length = word.length();

        if ( length == 0 ) {
            node.wordEnd = false;
            return node.children.isEmpty(); // 是否是某一单词的子串，是则完成删除
        }

        char letter = word.charAt(0);
        String subword = word.substring(1);
        // 不是子串，则应删除字符，直到不为空，或者是某一单词结尾
        if ( delete(node.children.get(letter), subword) ) {
            node.children.remove(letter);
            if ( node.children.isEmpty() && node.wordEnd==false ) {
                return true;
            }
        }

        return false;
    }

}

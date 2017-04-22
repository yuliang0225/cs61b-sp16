import java.nio.file.*;
import java.io.*;
import java.util.*;

public class Trie {

    // Trie�ڵ���
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


    // �����ַ���
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


    // ��������ַ���
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


    // �����ַ���
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


    // ����ǰ׺
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


    // ɾ���ַ���
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
            return node.children.isEmpty(); // �Ƿ���ĳһ���ʵ��Ӵ����������ɾ��
        }

        char letter = word.charAt(0);
        String subword = word.substring(1);
        // �����Ӵ�����Ӧɾ���ַ���ֱ����Ϊ�գ�������ĳһ���ʽ�β
        if ( delete(node.children.get(letter), subword) ) {
            node.children.remove(letter);
            if ( node.children.isEmpty() && node.wordEnd==false ) {
                return true;
            }
        }

        return false;
    }

}

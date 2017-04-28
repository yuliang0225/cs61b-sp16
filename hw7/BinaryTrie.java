import java.util.*;
import java.io.*;

public class BinaryTrie implements Serializable {

    // �ڵ�
    private class Node implements Serializable {
        private char ch;
        private int freq;
        private Node left, right;

        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public Node(char ch, int freq) {
            this(ch, freq, null, null);
        }

        public char character() {
            return ch;
        }

        public int frequency() {
            return freq;
        }

        public Node child(int i) {
            if (i==0) {
                return left;
            } else {
                return right;
            }
        }

        public boolean isLeaf() {
            return left==null && right==null;
        }

        /*
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
        */
    }

    // ���ڵ�
    private Node root;

    /**
    * ͨ�����ȶ��н�����������
    * ��ʼ�����ȶ��У�����ÿ���ַ���Ҷ�ڵ㣬���������
    * �����ȶ�����ȡ��Ƶ����С�������ڵ㣬�ϲ����½ڵ㣬�ٷŻض�����
    * ѭ��ֱ�����ȶ�����ֻ��һ���ڵ�
    */
    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
            public int compare(Node a, Node b)
            {
                return a.frequency() - b.frequency();
            }
        });

        for (Map.Entry<Character, Integer> entry : frequencyTable.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            pq.add(node);
        }

        while (pq.size() > 1) {
            Node p = pq.poll();
            Node q=  pq.poll();
            pq.add(new Node(' ', p.frequency()+q.frequency(), p, q));
        }

        root = pq.poll();
    }


    public Match longestPrefixMatch(BitSequence querySequence) {
        int length = querySequence.length();
        Node node = root;
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<=length; i++) {
            if (node.isLeaf()) {
                return new Match(new BitSequence(sb.toString()), node.character());
            }
            int bit = querySequence.bitAt(i);
            sb.append(String.valueOf(bit));
            node = node.child(bit);
        }

        return null;
    }


    public Map<Character, BitSequence> buildLookupTable() {
        HashMap<Character, BitSequence> expected = new HashMap<Character, BitSequence>();
        Node node = root;
        StringBuilder sb = new StringBuilder();

        dfs(expected, node, sb);
        return expected;
    }

    private void dfs(HashMap<Character, BitSequence> expected, Node node, StringBuilder sb) {
        if (node.isLeaf()) {
            expected.put(node.character(), new BitSequence(sb.toString()));
        } else {
            sb.append('0');
            dfs(expected, node.child(0), sb);
            sb.deleteCharAt(sb.length()-1);

            sb.append('1');
            dfs(expected, node.child(1), sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }

}

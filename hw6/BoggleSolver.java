import java.nio.file.*;
import java.io.*;
import java.util.*;

public class BoggleSolver {

    private Trie trie;

    // Initializes the data structure using the given array of strings as the dictionary.
    public BoggleSolver(String filename) {
        trie = new Trie();

        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            int length = lines.size();

            for (int i=0; i<length; i++) {
                trie.insert(lines.get(i));
            }
        } catch (IOException e) {
            System.out.printf("Open %s failed, %s\n", filename, e);
        }

    }


    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        Set<String> avw = new TreeSet<String>( new Comparator<String>() {
                public int compare(String a, String b)
                {
                    if ( a.length() > b.length() ) {
                        return -1;
                    }

                    if ( a.length() < b.length() ) {
                        return 1;
                    }

                    return a.compareTo(b);
                }
            }
        );    // 所有单词
        StringBuilder word = new StringBuilder();   // 单词
        Set<Integer> visit = new HashSet<Integer>();   // 保存路径，供dfs使用

        int M = board.rows();
        int N = board.cols();

        for (int i=0; i<M; i++) {
            for (int j=0; j<N; j++) {
                word = new StringBuilder();
                char letter = board.getLetter(i, j);
                word.append(letter);
                if (letter=='q') {
                    word.append('u');
                }

                visit.clear();
                visit.add(M*i + j);

                dfs(avw, word, visit, board, i, j);
            }
        }

        return avw;
    }

    private void dfs(Set<String> avw, StringBuilder word, Set<Integer> visit, BoggleBoard board, int m, int n) {
        for (int i=m-1; i<=m+1; i++) {
            for (int j=n-1; j<=n+1; j++) {
                if ( !board.isInBoard(i, j) || visit.contains(i*board.rows() + j) ) {
                    continue;
                }

                visit.add(i*board.rows() + j);
                char letter = board.getLetter(i, j);
                int letterNum = 1;
                word.append(letter);
                if (letter == 'q') {
                    word.append('u');
                    letterNum += 1;
                }

                String sword = word.toString();
                if ( trie.find(sword) ) {
                    avw.add(sword);
                }

                if ( trie.findPrefix(sword) ) {
                    dfs(avw, word, visit, board, i, j);
                }

                visit.remove(i*board.rows() + j);
                word.delete(word.length()-letterNum, word.length());
            }
        }

    }

}

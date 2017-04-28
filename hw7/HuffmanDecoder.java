import java.util.*;

public class HuffmanDecoder {

    public static void main(String[] args) {
        ObjectReader or = new ObjectReader(args[0]);
        BinaryTrie trie = (BinaryTrie) or.readObject();
        int num = (Integer) or.readObject();
        BitSequence bs = (BitSequence) or.readObject();
        char[] text = new char[num];

        for (int i=0, N=0; i<num; i++) {
            Match match = trie.longestPrefixMatch(bs.allButFirstNBits(N));
            text[i] = match.getSymbol();
            N += match.getSequence().length();
        }

        FileUtils.writeCharArray(args[1], text);
    }

}

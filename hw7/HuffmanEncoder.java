import java.util.*;


public class HuffmanEncoder {

    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character, Integer> frequencyTable = new HashMap<Character, Integer>();

        for (int i=0; i<inputSymbols.length; i++) {
            int num = frequencyTable.getOrDefault(inputSymbols[i], 0) + 1;
            frequencyTable.put(inputSymbols[i], num);
        }

        return frequencyTable;
    }


    public static void main(String[] args) {
        char[] inputSymbols = FileUtils.readFile(args[0]);
        Map<Character, Integer> frequencyTable = HuffmanEncoder.buildFrequencyTable(inputSymbols);
        BinaryTrie trie = new BinaryTrie(frequencyTable);
        ObjectWriter ow = new ObjectWriter(args[0] + ".huf");
        ow.writeObject(trie);
        ow.writeObject(new Integer(inputSymbols.length));

        Map<Character, BitSequence> table = trie.buildLookupTable();
        List<BitSequence> lbs = new ArrayList<BitSequence>();
        for (int i=0; i<inputSymbols.length; i++) {
            lbs.add(table.get(inputSymbols[i]));
        }
        BitSequence bs = BitSequence.assemble(lbs);
        ow.writeObject(bs);
    }

}

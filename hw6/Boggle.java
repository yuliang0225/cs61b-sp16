import java.util.*;

public class Boggle {

    private BoggleBoard bb;
    private BoggleSolver bs;
    private Iterable<String> avw;
    private int k;


    private String[] command(String[] args) {
        String [] cmd = new String[]{null, null, null, null, null};

        for (int i=0; i<args.length; i+=2) {
            switch (args[i]) {
                case "-k":
                    cmd[0] = args[i+1];
                break;

                case "-n":
                    cmd[1] = args[i+1];
                break;

                case "-m":
                    cmd[2] = args[i+1];
                break;

                case "-d":
                    cmd[3] = args[i+1];
                break;

                case "-r":
                    cmd[4] = args[i+1];
                break;
            }
        }

        return cmd;
    }


    Boggle (String[] args) {
        String[] cmd = command(args);

        if (cmd[0] == null) {
            k = 1;
        } else {
            k = Integer.parseInt(cmd[0]);
        }

        if (cmd[1]==null || cmd[2]==null) {
            if (cmd[4] == null) {
                bb = new BoggleBoard();
            } else {
                bb = new BoggleBoard(cmd[4]);
            }
        } else {
            bb = new BoggleBoard(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]));
        }

        if (cmd[3] == null) {
            bs = new BoggleSolver("words");
        } else {
            bs = new BoggleSolver(cmd[3]);
        }

        avw = bs.getAllValidWords(bb);
    }


    public void printKLongestWords() {
        Iterator iter = avw.iterator();
        for(int i=0; iter.hasNext() && i<k; i++) {
            System.out.println( iter.next() );
        }
    }


    public static void main(String[] args) {
        Boggle bg = new Boggle(args);
        bg.printKLongestWords();
    }

}

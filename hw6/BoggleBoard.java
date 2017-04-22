import java.nio.file.*;
import java.io.*;
import java.util.*;

public class BoggleBoard {

    private char[][] board;
    private int M, N;   // M -> height, N -> width


    // Initializes a random 4-by-4 Boggle board.
    public BoggleBoard() {
        M = 4;
        N = 4;
        board = new char[M][N];

        for (int i=0; i<M; i++) {
            for (int j=0; j<N; j++) {
                board[i][j] = (char)(Math.random()*26 + 97);
            }
        }
    }


    // Initializes a random m-by-n Boggle board.
    public BoggleBoard(int m, int n) {
        M = m;
        N = n;
        board = new char[M][N];

        Scanner sc = new Scanner(System.in);
        for (int i=0; i<M; i++) {
            String[] line = sc.nextLine().split("\\s+");

            for (int j=0; j<N; j++) {
                board[i][j] = line[j].charAt(0);
            }
        }
    }


    // Initializes a Boggle board from the specified filename.
    public BoggleBoard(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            M = lines.size();
            N = lines.get(0).length();
            board = board = new char[M][N];

            for (int i=0; i<M; i++) {
                String line = lines.get(i);
                for (int j=0; j<N; j++) {
                    board[i][j] = line.charAt(j);
                }
            }
        } catch (IOException e) {
            System.out.printf("Open %s failed, %s\n", filename, e);
        }

    }

    // Returns the number of rows.
    public int rows() {
        return M;
    }


    // Returns the number of columns.
    public int cols() {
        return N;
    }


    public boolean isInBoard(int i, int j) {
        if (i<0 || i>=M || j<0 || j>=N) {
            return false;
        }
        return true;
    }


    // Returns the letter in row i and column j.
    public char getLetter(int i, int j) {
        return board[i][j];
    }


    // Returns a string representation of the board.
    public String toString() {
        String str = "";

        int M = rows(), N = cols();
        for (int i=0; i<M; i++) {
            for (int j=0; j<N; j++) {
                str += board[i][j] + " ";
            }
            str += "\n";
        }

        return str;
    }

}

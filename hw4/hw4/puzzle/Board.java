package hw4.puzzle;

public class Board {

    private int[][] tiles;
    private int size;
    private int hamming;
    private int manhattan;

    public Board(int[][] tiles) {
        size = tiles.length;
        this.tiles = new int[size][size];
        for (int i=0; i<size; ++i) {
            System.arraycopy(tiles[i], 0, this.tiles[i], 0, size);
        }

        // hamming distance
        /*
        hamming = 0;
        for (int i=0; i<size; ++i) {
            for (int j=0; j<size; ++j) {
                int tile = tileAt(i, j);
                if ( tile != i*size+j+1 ) {
                    ++hamming;
                }
            }
        }
        --hamming;
        */

        // manhattan distance
        manhattan = 0;
        for (int i=0; i<size; ++i) {
            for (int j=0; j<size; ++j) {
                int tile = tileAt(i, j);
                if (tile != 0 && tile != i*size+j+1 ) {
                    manhattan += Math.abs(i-(tile-1)/size) + Math.abs(j-(tile-1)%size);
                }
            }
        }
    }

    public int tileAt(int i, int j) {
        /*
        boolean bound = i>=0 && i<size && j>=0 && j<size;
        if (!boolean) {
            throw new IndexOutOfBoundsException("Out of bound size!");
        }
        */
        return tiles[i][j];
    }

    public int size() {
        return size;
    }

    public int hamming() {
        return hamming;
    }

    public int manhattan() {
        return manhattan;
    }

    public boolean isGoal() {
        // return hamming() == 0;
        return manhattan() == 0;
    }

    // @Override
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null || y.getClass() != this.getClass()) {
            return false;
        }

        Board that = (Board) y;
        int N = size();
        if (N != that.size()) {
            return false;
        }
        for (int i=0; i<N; ++i) {
            for (int j=0; j<N; ++j) {
                if (tileAt(i,j) != that.tileAt(i,j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hasCode() {
        int N = size();
        int hash = 17;
        for (int i=0; i<N; ++i) {
            for (int j=0; j<N; ++j) {
                int tile = tileAt(i, j);
                hash = hash*31 + tile;
            }
        }
        return hash;
    }

    /** Returns the string representation of the board.
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        // s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}

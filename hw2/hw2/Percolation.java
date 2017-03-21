package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int N;
    private boolean[][] grid;
    private int sizeOfOpenSites;
    private int top, bottom;
    private WeightedQuickUnionUF wquTop, wquTopBottom;

    // return position of row, col
    private int position(int row, int col) {
        return row*N + col;
    }

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        try {
            if (N <= 0) {
                throw new IllegalArgumentException("N should bigger than 0!");
            }

            this.N = N;
            grid = new boolean[N][N];  // open->true, blocked->false, defalut blocked
            sizeOfOpenSites = 0;
            top = N*N;
            bottom = N*N + 1;
            wquTop = new WeightedQuickUnionUF( (N*N)+1 );
            wquTopBottom = new WeightedQuickUnionUF( (N*N)+2 );

            for (int i=0; i<N; i++) {
                wquTop.union(top, position(0,i));
                wquTopBottom.union(top, position(0,i));
                wquTopBottom.union(bottom, position(N-1,i));
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if ( isOpen(row, col) ) {
            return;
        }
        grid[row][col] = true;
        sizeOfOpenSites++;

        int[] surround = new int[]{row-1,col, row+1,col, row,col-1, row,col+1};
        for (int i=0; i<8; i=i+2) {
            int r = surround[i], c = surround[i+1];
            if ( isOpen(r, c) ) {
                wquTop.union(position(row,col), position(r,c));
                wquTopBottom.union(position(row,col), position(r,c));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row<0 || row>=N || col<0 || col>=N) {
            return false;
        }
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if ( !isOpen(row, col) ) {
            return false;
        }
        return wquTop.connected(top, position(row,col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return sizeOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return wquTopBottom.connected(top, bottom);
    }

    // unit testing (not required)
    public static void main(String[] args) {
        Percolation pc = new Percolation(4);
        pc.open(0,1);
        pc.open(1,2);
        pc.open(1,1);
        System.out.println(pc.isOpen(1,1));
        System.out.println(pc.isOpen(2,1));
        System.out.println(pc.isFull(1,2));

        pc.open(3,2);
        System.out.println(pc.percolates());
        pc.open(2,2);
        System.out.println(pc.percolates());
    }

}

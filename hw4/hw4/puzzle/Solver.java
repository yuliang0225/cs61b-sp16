package hw4.puzzle;
import edu.princeton.cs.algs4.*;
import java.util.HashSet;

public class Solver {
    private Stack<Board> boards;
    private boolean isSolvable;


    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private int moves;
        private SearchNode previous;
        private int priority;

        SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
            priority = moves + board.manhattan();
        }

        private int priority() {
            return priority;
        }

        public int moves() {
            return moves;
        }

        public Board board() {
            return board;
        }

        public SearchNode previous() {
            return previous;
        }

        @Override
        public int compareTo(SearchNode that) {
            if (this.priority() < that.priority()) {
                return -1;
            }
            if (this.priority() > that.priority()) {
                return +1;
            }
            return 0;
        }
    }

    public Solver(Board initial) {
        boards = new Stack<Board>();

        checkSolvable(initial);
        if (!isSolvable()) {
            return;
        }

        MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>();
        HashSet<SearchNode> set = new HashSet<SearchNode>();

        SearchNode node = new SearchNode(initial, 0, null);
        minPQ.insert(node);
        set.add(node);

        while ( true ) {
            node = minPQ.delMin();
            if (node.board().isGoal()) {
                break;
            }
            for ( Board bd : BoardUtils.neighbors(node.board()) ) {
                SearchNode next = new SearchNode(bd, node.moves()+1, node);
                if ( !set.contains(next) ) {
                    minPQ.insert(next);
                    set.add(next);
                }
            }
        }

        while (node != null) {
            boards.push( node.board() );
            node = node.previous();
        }
    }

    /* Check whether the solution is available */
    private void checkSolvable(Board initial) {
        isSolvable = false;
        int N = initial.size();
        int[] ls = new int[N*N-1];
        int bp = 0, index = 0;
        for (int i=0; i<N; ++i) {
            for (int j=0; j<N; ++j) {
                int tile = initial.tileAt(i,j);
                if (tile == 0) {
                    bp = i;
                } else {
                    ls[index] = tile;
                    ++index;
                }
            }
        }
        // Solve the inverse order number
        int ron = 0;
        for (int i=0; i<ls.length-1; ++i) {
            for (int j=i; j<ls.length; ++j) {
                if ( ls[i] > ls[j] ) {
                    ++ron;
                }
            }
        }
        // Determine whether the solution is solvable
        if ( (N%2==1 && ron%2==0) || (N%2==0 && (ron+N-bp-1)%2==0) ) {
            isSolvable = true;
        }
    }


    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        if (isSolvable()) {
            return boards.size() - 1;
        }
        return -1;
    }


    public Iterable<Board> solution() {
        if (isSolvable()) {
            return boards;
        }
        return null;
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
            }
        }

        long startMili = System.currentTimeMillis();
        Board initial = new Board(tiles);
        Solver solver = new Solver(initial);

        StdOut.println("\nMinimum number of moves = " + solver.moves() + "\n");
        for (Board board : solver.solution()) {
            StdOut.println(board);
       }
       StdOut.printf("Run time: %d ms", System.currentTimeMillis() - startMili);
    }

}

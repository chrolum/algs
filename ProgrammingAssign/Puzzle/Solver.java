package ProgrammingAssign.Puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class Solver {
    Stack<Board> path = new Stack<>();
    private boolean isSolvable;
    private int moves = 0;

    // packing the Board node to implement Comparable interface for caclulate the priority
    private class SNode implements Comparable<SNode>{
        private final Board board;
        private SNode prev = null;
        private int moves = 0;
        private boolean fromOrignal;
        private int priority; //cache for avoiding repeated calculation in compareTo()

        public SNode(Board board, SNode prev) {
            if (board == null || prev == null) throw new NullPointerException();
            this.board = board;
            this.prev = prev;
            this.moves = prev.moves + 1;
            this.fromOrignal = prev.fromOrignal;
            this.priority = this.board.manhattan() + this.moves;
        }

        public SNode(Board board, boolean origninStatus) {//the init constructor
            if (board == null) throw new NullPointerException();
            this.board = board;
            this.fromOrignal = origninStatus;
            this.priority = this.board.manhattan() + this.moves;

        }

        @Override
        public int compareTo(SNode that) {
            return Integer.compare(this.priority, that.priority);
        }
    }

    // construct a board from an n-by-n array of blocks
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        MinPQ<SNode> pq = new MinPQ<>();
        pq.insert(new SNode(initial, true));
        pq.insert(new SNode(initial.twin(), false));
        SNode curr = pq.delMin();

        while (!curr.board.isGoal()) {
            for (Board b : curr.board.neighbors()) {
                if (curr.prev == null || !b.equals(curr.prev.board)) {// avoid add prev node into pq
                    pq.insert(new SNode(b, curr));
                }
            }
            curr = pq.delMin();
        }

        this.isSolvable = curr.fromOrignal;

        //generlate the path and record with a stack
        while (curr != null) {
            this.path.push(curr.board);
            this.moves++;
            curr = curr.prev;
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return this.isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) return -1;
        return this.moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        return this.path;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}

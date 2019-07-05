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
    private MinPQ<SNode> pq = new MinPQ<>();
    Stack<Board> path = new Stack<>();
    private SNode initalSNode;
    private int moves = 0;

    // construct a board from an n-by-n array of blocks
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        this.initalSNode = new SNode(initial);
        pq.insert(initalSNode);
        SNode curr = pq.delMin();
        while (!curr.board.isGoal()) {
            for (Board b : curr.board.neighbors()) {
                if (curr.getPrev() == null || !b.equals(curr.getPrev().board)) {// avoid add prev node into pq
                    pq.insert(new SNode(b, curr, curr.getMoves() + 1));
                }
            }
            curr = pq.delMin();
//            System.out.println("Current priority is " + curr.board.manhattan());
        }
        //generlate the path and record with a stack
        while (curr != null) {
            this.path.push(curr.board);
            this.moves++;
            curr = curr.prev;
        }
    }
    // is the initial board solvable?
    public boolean isSolvable() {
//        if (initalSNode.board.twin().isGoal()) return false;
        return true;
    }
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return this.moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return this.path;
    }

    // packing the Board node to implement Comparable interface for caclulate the priority
    private class SNode implements Comparable<SNode>{
        private final Board board;
        private SNode prev = null;
        private int moves = 0;

        public SNode(Board board) {
            this.board = board;
        }

        public SNode(Board board, SNode prev, int moves) {
            this.board = board;
            this.prev = prev;
            this.moves = moves;
        }

        public SNode getPrev() {
            return prev;
        }

        public void setPrev(SNode prev) {
            this.prev = prev;
        }

        public int getMoves() {
            return this.moves;
        }

        @Override
        public int compareTo(SNode that) {
            return Integer.compare(this.board.manhattan() + this.moves, that.board.manhattan() + that.moves);
        }
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

package ProgrammingAssign.Puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class Solver {
    private MinPQ<SNode> pq;
    private LinkedList<Board> path;
    private SNode initalSNode;
    private HashSet<SNode> visitedSet = new HashSet<>();//record the visited node to optimal
    private HashMap<SNode, SNode> comrFrom = new HashMap<>();
    private int moves = 0;

    // construct a board from an n-by-n array of blocks
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        initalSNode = new SNode(initial);
        pq.insert(initalSNode);
        SNode curr = pq.delMin();
        path.add(curr.board);
        while (!curr.board.isGoal()) {
            for (Board b : curr.board.neighbors()) {
                if (!b.equals(curr.getPrev().board)) {// avoid add prev node into pq
                    pq.insert(new SNode(b, curr, curr.getMoves() + 1));
                }
            }
            curr = pq.delMin();
            path.add(curr.board);
        }
    }
    // is the initial board solvable?
    public boolean isSolvable() {
        if (initalSNode.board.twin().isGoal()) return false;
        return true;
    }
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return moves;
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

    }
}

package ProgrammingAssign.Puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class Solver {
    private MinPQ<Board> pq;
    private Stack<Board> path;
    private Board inital;
    private HashMap<Board, Boolean> isVisited = new HashMap<>();
    private int moves = 0;

    // construct a board from an n-by-n array of blocks
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        this.inital = initial;
        pq.insert(initial);
        while (!pq.isEmpty()) {
            Board curr = pq.delMin();
            if (curr.isGoal()) {
                path.push(curr);
                break;
            } else {
                for (Board n : curr.neighbors()) {
                    pq.insert(n);
                }
            }
        }
    }
    // is the initial board solvable?
    public boolean isSolvable() {
        if (inital.twin().isGoal()) return false;
        return true;
    }
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        LinkedList<Board> res = new LinkedList<>();
        for ()
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {

    }
}

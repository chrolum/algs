package ProgrammingAssign.Puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/

/**
 * use Manhattan priority with A* algs to solve the 8-puzzle problem
 */

public class Solver {
    private MinPQ<Board> pq;
    private Stack<Board> solutions;
    private int moves = -1;

    // construct a board from an n-by-n array of blocks
    public Solver(Board initial) {
        pq.insert(initial);
        while (!pq.isEmpty()) {
            Board curr = pq.delMin();
            for (Board neighbors : curr.neighbors()) {
                pq.insert(curr);
            }
        }
    }
    // is the initial board solvable?
    public boolean isSolvable() {

    }
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return this.moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {

    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {

    }
}

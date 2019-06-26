package ProgrammingAssign.Puzzle;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/

/**
 * use a array with 9 length to store the baord status
 */
public class Board {
    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    private int[][] board;

    public Board(int[][] blocks) {
        this.board = blocks;
    }

    // board dimension n
    public int dimension() {

    }

    // number of blocks out of place
    public int hamming() {

    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {

    }

    // is this board the goal board?
    public boolean isGoal() {

    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {


    }

    // does this board equal y?
    public boolean equals(Object y) {
        for (int i = 0) {
            
        }
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {

    }

    // string representation of this board (in the output format specified below)
    public String toString() {

    }

    // unit tests (not graded)
    public static void main(String[] args) {

    }
}

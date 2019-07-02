package ProgrammingAssign.Puzzle;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * use a array with 9 length to store the baord status
 */
public class Board {
    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    protected final int[][] board;
    private int[][] goal= new int[][]{{1,2,3},{4,5,6},{7,8,0}};
    private int blankRow = -1;
    private int blankCol = -1;

    public Board(int[][] blocks) {
        this.board = blocks;
        for (int i = 0; i < this.dimension(); i++) {
            for (int j = 0; j < this.dimension(); j++) {
                if (board[i][j] == 0) {
                    blankRow = i; blankCol = j;
                    break;
                }
            }
        }
    }

    // board dimension n
    public int dimension() {
        return this.board.length;
    }

    // number of blocks out of place
    public int hamming() {
        int dist = 0;
        for (int i = 0; i < this.dimension(); i++) {
            for (int j = 0; j < this.dimension(); j++) {
                if (board[i][j] != this.goal[i][j] && board[i][j] != 0) dist++;
            }
        }
        return dist;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int horizonal = 0, vertical = 0;
        for (int i = 0; i < this.dimension(); i++) {
            for (int j = 0; j < this.dimension(); j++) {
                if (board[i][j] == 0) continue;
                horizonal = Math.abs((board[i][j] / this.dimension()) - i);
                vertical = Math.abs((board[i][j] % this.dimension()) - j);
            }
        }
        return horizonal + vertical;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return this.hamming() == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int[][] twinBlocks = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            twinBlocks[i] = board[i].clone();
        }
        return new Board(twinBlocks);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (this.dimension() != that.dimension()) return false;
        for(int i = 0; i < this.dimension(); i++) {
            for (int j = 0; j < this.dimension(); j++) {
                if (this.board[i][j] != that.board[i][j]) return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new LinkedList<>();

        if (blankRow > 0) neighbors.add(new Board(exch(board, blankRow, blankCol, blankRow - 1, blankCol)));
        if (blankCol > 0) neighbors.add(new Board(exch(board, blankRow, blankCol, blankRow, blankCol - 1)));
        if (blankRow < this.dimension()) neighbors.add(new Board(exch(board, blankRow, blankCol, blankRow + 1, blankCol)));
        if (blankCol < this.dimension()) neighbors.add(new Board(exch(board, blankRow, blankCol, blankRow, blankCol + 1)));

        return neighbors;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(this.dimension());
        str.append('\n');
        for (int r = 0; r < this.dimension(); r++) {
            for (int c = 0; c < this.dimension(); c++) {
                str.append(" ");
                str.append(board[r][c]);
            }
            str.append('\n');
        }
        return str.toString();
    }

    private int[][] exch(int[][] a, int this_x, int this_y, int that_x, int that_y) {
        int tmp = a[this_x][this_y];
        a[this_x][this_y] = a[that_x][that_y];
        a[that_x][that_y] = tmp;
        return a;
    }

    // unit tests (not graded)
    public static void main(String[] args) {
        int[][] blocks = new int[][]{{2,3,1},{4,5,6},{8,7,0}};
        int[][] equitmentTest = new int[][]{{2,3,1},{4,5,6},{8,7,0}};
        int[][] disequitmentTest = new int[][]{{2,3,1},{4,5,6},{7,8,0}};

        Board b = new Board(blocks);

        //funciton test
        System.out.println("hamning distance is " + b.hamming());
        System.out.println("manhatan distance is " + b.manhattan());

        //toString() Test
        System.out.println(b);
    }
}

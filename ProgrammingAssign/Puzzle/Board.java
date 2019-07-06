package ProgrammingAssign.Puzzle;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/

import java.util.LinkedList;
import java.util.List;

/**
 * use a array with 9 length to store the baord status
 */
public class Board {
    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    private final int[][] board;
//    private int[][] goal= new int[][]{{1,2,3},{4,5,6},{7,8,0}};
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
    //support > 3 dimension blocks now
    public int hamming() {
        int dist = 0;
        for (int i = 0; i < this.dimension(); i++) {
            for (int j = 0; j < this.dimension(); j++) {
                if (board[i][j] != (i * this.dimension() + j + 1) && board[i][j] != 0) dist++;
            }
        }
        return dist;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int horizontal = 0, vertical = 0, dist = 0;
        for (int i = 0; i < this.dimension(); i++) {
            for (int j = 0; j < this.dimension(); j++) {
                if (board[i][j] == 0) continue;
                horizontal = Math.abs((board[i][j] - 1) % this.dimension() - j);
                vertical = Math.abs((board[i][j] - 1) / this.dimension() - i);
                dist += horizontal + vertical;
            }
        }
        return dist;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return this.hamming() == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int[][] twinBlocks = makeBoardCopy();
        for (int i = 0; i < board.length; i++) {
            twinBlocks[i] = board[i].clone();
        }
        int p1_r = blankRow == 0 ? 1 : 0, p1_c = 0, p2_r = blankRow == 0 ? 1 : 0, p2_c = 1;//tick of the blank block
        exch(twinBlocks, p1_r, p1_c, p2_r, p2_c);
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

        if (blankRow > 0) neighbors.add(new Board(exch(makeBoardCopy(), blankRow, blankCol, blankRow - 1, blankCol)));
        if (blankCol > 0) neighbors.add(new Board(exch(makeBoardCopy(), blankRow, blankCol, blankRow, blankCol - 1)));
        if (blankRow < this.dimension()-1) neighbors.add(new Board(exch(makeBoardCopy(), blankRow, blankCol, blankRow + 1, blankCol)));
        if (blankCol < this.dimension()-1) neighbors.add(new Board(exch(makeBoardCopy(), blankRow, blankCol, blankRow, blankCol + 1)));

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

    private int[][] makeBoardCopy() {
        int[][] copy = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }

    // unit tests (not graded)
    public static void main(String[] args) {
        int[][] blocks = new int[][]{{8,7,1},{4,0,6},{2,3,5}};
        int[][] equitmentTest = new int[][]{{8,7,1},{4,0,6},{2,3,5}};
        int[][] disequitmentTest = new int[][]{{2,3,1},{4,5,6},{7,8,0}};
        int[][] goalTest = new int[][]{{1,2,3},{4,5,6},{7,8,0}};

        Board b = new Board(blocks);

        //funciton test
        System.out.print("dimension() test: ");
        if (b.dimension() == 3) System.out.println("pass");
        else System.out.println("failed");
        System.out.print("hamning() test: ");
        if (b.hamming() == 6) System.out.print("pass");
        else System.out.print("failed");
        System.out.println(" ,hamning distance is " + b.hamming());//return 5
        System.out.print("mahattan() test : ");
        if (b.manhattan() == 16) System.out.print("pass");
        else System.out.print("failed");
        System.out.println(" ,manhatan distance is " + b.manhattan());//return 5
        //equal() test
        System.out.print("equals() test :");
        if (b.equals(new Board(equitmentTest)) && !b.equals(new Board(disequitmentTest))) System.out.println("pass");
        else System.out.println("failed");
        //toString() Test
        System.out.println("toString() test:");
        System.out.println(b);
        //isGoal() test
        Board boardGoal = new Board(goalTest);
        System.out.println("isGoal() test :" + (boardGoal.isGoal() ? " pass" : " failed"));
        //twin() test
        System.out.println("twins is ");
        System.out.println(b.twin());
        //neighbor() test
        int num = 1;
        for (Board n : b.neighbors()) {
            System.out.println("neighbor " + num + ":");
            System.out.println(n);
            num++;
        }
    }
}


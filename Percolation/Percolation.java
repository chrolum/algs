package Percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Note: avoid the backwash
 */

public class Percolation
{
    public int n;//gird size
    private WeightedQuickUnionUF uf;
    private int count = 0;//open size numbers
    private boolean[][] isOpen;//zero is blocked, one is open site
    private int top;//virtual top node index
    private int bottom;

    public Percolation(int n)

    {
        uf = new WeightedQuickUnionUF(n*n + 2);//the last two extra node for checking the status of openning
        isOpen = new boolean[n][n];
        this.n = n;
        this.top = n*n;
        this.bottom = n*n+1;

        for (int i = 1; i <= n; i++)
        {
            uf.union(realPostion(1, i), top);//union the first row and the top virtual node(index is n*n)
            uf.union(realPostion(n, i), bottom);//union the last row and the bottom virtual node
        }

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                isOpen[i][j] = false;

//        StartSimulatie();//make the system be in percolate site
    }

    public void open(int row, int col)
    {
        valided(row, col);
        if (!isOpen(row, col))
        {
            isOpen[row-1][col-1] = true;

            //if the site open in top or bottom row, connected to the virtual node
//            if (row == 1) {uf.union(realPostion(row, col), top);}
//            if (row == n) {uf.union(realPostion(row, col), bottom);}

            //check left, right, up, and down;Note the invalid postion and the adj postion whether open
            if (row != n && isOpen(row+1, col)) {uf.union(realPostion(row, col), realPostion(row+1, col));}//down
            if (row != 1 && isOpen(row-1, col)) {uf.union(realPostion(row, col), realPostion(row-1, col));}//up
            if (col != n && isOpen(row, col+1)) {uf.union(realPostion(row, col), realPostion(row, col+1));}//right
            if (col != 1 && isOpen(row, col-1)) {uf.union(realPostion(row, col), realPostion(row, col-1));}//down

            //count the opensite
//            if (newSite) {count++;}

            this.count++;
        }
    }

    public boolean isOpen(int row, int col)
    {
        valided(row, col);
        return isOpen[row-1][col-1];
    }

    public boolean isFull(int row, int col)
    {
        valided(row, col);
        return uf.connected(top, realPostion(row, col)) && isOpen(row, col);//if the site is full of water, if has been connected to the top node
    }

    public boolean percolates()
    {
        return uf.connected(top, bottom);//if the virtual top and bottom node is connected, the system is percolated
    }

    public int numberOfOpenSites()
    {
        return this.count;
    }

    private int realPostion(int row, int col)//calculate the real index in id array
    {
        valided(row, col);
        return (row - 1) * n + (col - 1);
    }

    private void valided(int row, int col)
    {
        if (col < 1 || col > n || row < 1 || row > n)
        {
            throw new IllegalArgumentException("col or row is invalid" + "col:" + col + "row:" + row);
        }
    }


//    private void StartSimulatie()
//    {
//        int[] randomIndex = new int[n*n];//create an shuffle index to randomly open the blocked site
//        for (int i = 0; i < n*n; i++)
//        {
//            randomIndex[i] = i;
//        }
//        StdRandom.shuffle(randomIndex);
//        int i = 0;
//        int index;
//        while(!this.percolates())//start simulation until the system is percolation
//        {
//            index = randomIndex[i];
//            int row = (index / n) + 1;
//            int col = ((index % n) + 1);
////            System.out.println("index:" + index + " " + "col:" + col + " " + "row:" + row);
//            open(row, col);
//            i++;
//        }
//    }
}

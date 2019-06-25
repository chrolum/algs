package ProgrammingAssign.Percolation;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class PercolationStats
{
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    public PercolationStats(int n, int trials)
    {

        double[] testData = new double[trials];//record the trials data
        for (int i = 0; i < trials; i++)//do trials time simulation
        {
            Percolation perc = new Percolation(n);
            StartSimulatie(perc);
            testData[i] = perc.numberOfOpenSites() / (double)(n*n);
        }

        //init stats infomation
        //mean
        this.mean = StdStats.mean(testData);
        //stddev
        this.stddev = StdStats.stddev(testData);
        //confLo
        this.confidenceLo = this.mean - 1.96 * Math.sqrt(this.stddev) / Math.sqrt(trials);
        //confHi
        this.confidenceHi = this.mean + 1.96 * Math.sqrt(this.stddev) / Math.sqrt(trials);

    }

    public double mean()
    {
        return this.mean;
    }

    public double stddev()
    {
        return this.stddev;
    }

    public double confidenceLo()
    {
        return this.confidenceLo;
    }

    public double confidenceHi()
    {
        return this.confidenceHi;
    }

    //start single simulation
    private void StartSimulatie(Percolation perc)
    {
        int n = perc.n;
        int[] randomIndex = new int[n*n];//create an shuffle index to randomly open the blocked site
        for (int i = 0; i < n*n; i++)
        {
            randomIndex[i] = i;
        }
        StdRandom.shuffle(randomIndex);
        int i = 0;
        int index;
        while(!perc.percolates())//start simulation until the system is percolation
        {
            index = randomIndex[i];
            int row = (index / n) + 1;
            int col = ((index % n) + 1);
//            System.out.println("index:" + index + " " + "col:" + col + " " + "row:" + row);
            perc.open(row, col);
            i++;
        }
    }
    public static void main(String[] args)
    {
        int n = StdIn.readInt();
        int T = StdIn.readInt();
        PercolationStats percStats = new PercolationStats(n, T);
        System.out.println("mean                    = " + percStats.mean());
        System.out.println("stddev                  = " + percStats.stddev());
        System.out.println("95% confidence interval = " + "[" + percStats.confidenceLo() + ", " + percStats.confidenceHi() + "]");
    }
}

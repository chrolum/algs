package Percolation;

import edu.princeton.cs.algs4.StdIn;

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

        int[] testData = new int[trials];//record the trials data
        for (int i = 0; i < trials; i++)//do trials time simulation
        {
            Percolation perc = new Percolation(n);
            testData[i] = perc.getCount();
        }

        //init stats infomation
        //mean
        double total = 0.0;
        for (int i = 0; i < trials; i++)
        {
            total += testData[i];
        }
        this.mean = total / trials;
        //stddev
        total = 0.0;
        for (int i = 0; i < trials; i++)
        {
            total += Math.pow((testData[i] - mean), 2);
        }
        this.stddev = total / (trials - 1);
        //confLo
        this.confidenceLo = this.mean - 1.96 * Math.sqrt(this.stddev) / Math.sqrt(trials);
        //confHi

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

    public static void main(String... args)
    {
        int n = StdIn.readInt();
        int T = StdIn.readInt();
        PercolationStats percStats = new PercolationStats(n, T);
        System.out.println("mean" + "=" + percStats.mean);
        System.out.println("stddev" + "=" + percStats.stddev);
    }
}

package Percolation;

import edu.princeton.cs.algs4.StdIn;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class PercolationStats
{
    private int[] testData;
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;
    private int trials;



    public PercolationStats(int n, int trials)
    {

        this.trials = trials;
        this.testData = new int[trials];//record the trials data
        for (int i = 0; i < trials; i++)//do trials time simulation
        {
            Percolation perc = new Percolation(n);
            monteCarloSimulation sim = new monteCarloSimulation(perc);
            testData[i] = sim.getNumberOfOpenSite();
        }
    }

    public double mean()
    {
        for (int i = 0; i < testData.length; i++)
        {

        }
    }

    public double stddev()
    {

    }

    public double confidenceLo()
    {

    }

    public double confidenceHi()
    {

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

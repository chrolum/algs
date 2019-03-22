package Percolation;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Finish the simulaition
 * Choose a site uniformly at random among all blocked sites.
 * Open the site.
 * Repeat the following until the system percolates:
 * @author crkylin
 * Email:crkylin@gmail.com
 * To-do: design this class as interface, perolation implement this interface
 **/
public class monteCarloSimulation
{
    private int numberOfOpenSite;
    public monteCarloSimulation(Percolation perc)
    {
        while (!perc.percolates())//Repeat the following until the system percolates
        {
            int row = StdRandom();//random a blocked site
            int col = StdRandom();
            perc.open();
        }
        this.numberOfOpenSite = perc.numberOfOpenSites();
    }

    public int getNumberOfOpenSite()
    {
        return this.numberOfOpenSite;
    }
}

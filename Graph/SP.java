package Graph;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Stack;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class SP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;


    public SP(EdgeWeightedDigraph G, int s) {
        this.distTo = new double[G.V()];
        this.edgeTo = new DirectedEdge[G.E()];
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
}

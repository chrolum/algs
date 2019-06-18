package Graph;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;

import java.util.HashSet;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class Degrees {

//    private Queue<Integer> queue;
    private boolean[] marked;
    private int[] indegrees;// the indegrees of a vetrix,
    private int[] outdegrees;
    private HashSet<Integer> sources;
    private HashSet<Integer> sinks;


    public Degrees(Digraph G) {
        this.indegrees = new int[G.V()];
        this.outdegrees = new int[G.V()];
        this.marked = new boolean[G.V()];
        // init the degrees infomation
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) bfs(G, v);
        }
        for (int v = 0; v < indegrees.length; v++) {
            if (indegree(v) == 0) sources.add(v);
            if (outdegree(v) == 0) sinks.add(v);
        }
    }

    private void bfs(Digraph G, int s) {
        Queue<Integer> q = new Queue<>();
        marked[s] = true;
        q.enqueue(s);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w])
                {
                    marked[w] = true;
                    q.enqueue(w);
                }
                indegrees[w] += 1;
                outdegrees[v] += 1;
            }
        }
    }

    public int indegree(int v) {
        return indegrees[v];
    }

    public int outdegree(int v) {
        return outdegrees[v];
    }

    public Iterable<Integer> sources() {
        return sources;
    }

    public Iterable<Integer> sinks() {
        return sinks;
    }

    public boolean isMap() {
        
    }
}

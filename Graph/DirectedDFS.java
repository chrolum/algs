package Graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int w : sources) {
            if (!marked[w]) dfs(G, w);
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    private boolean isMarked(int v) {
        return marked[v];
    }

    public static void main(String... args) {
        Digraph G = new Digraph(new In(args[0]));

        Bag<Integer> sources = new Bag<Integer>();
        for (int i = 1; i <args.length; i++) {
            sources.add(Integer.parseInt(args[i]));
        }

        DirectedDFS reachalble = new DirectedDFS(G, sources);

        for (int v = 0; v < G.V(); v++) {
            if (reachalble.isMarked(v)) StdOut.print(v + " ");
        }
        StdOut.println();
    }
}

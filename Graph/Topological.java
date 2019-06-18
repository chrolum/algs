package Graph;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph G) {
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.getReversePost();
        }
    }

    public Iterable<Integer> getOrder() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }

}

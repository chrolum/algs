package Graph;

import edu.princeton.cs.algs4.Digraph;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph G) {

    }

    public Iterable<Integer> getOrder() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }

}

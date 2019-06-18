package Graph;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class DepthFirstOrder {
    private boolean[] hasVisited;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph G) {
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        hasVisited = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!hasVisited[v]) dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v) {
        pre.enqueue(v);
        hasVisited[v] = true;
        for (int w : G.adj(v)) {
            if (!hasVisited[w]) dfs(G, w);
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> getPre() {
        return pre;
    }

    public Iterable<Integer> getPost() {
        return post;
    }

    public Iterable<Integer> getReversePost() {
        return reversePost;
    }

}

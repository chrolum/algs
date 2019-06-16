package Graph;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!isMarked(w)) dfs(G, w);
        }
    }

    private boolean isMarked(int w) {
        return marked[w];
    }

    public int getCount() {
        return count;
    }
}

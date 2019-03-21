package unionFound;

/**
 * In common quick-union unionfound, the union operation will union the p to the q,
 * whatever the the tree's length of p is higher than q
 * we eager that the small size of set attach to the larger root(the new tree height is still is height of larger tree)
 * if we attach the large tree to the smaller one, the height of new tree will increase by one
 *
 * Therefore, the class will override the union method that check which tree is smaller, and the
 * attath the smaller on to the bigger on in order to decrease the height of tree
 *
 * we need a new array to recode the size of tree
 */

public class WeightedQUUF extends QUUF
{
    private int[] sz;//to implement the optimze algs

    public WeightedQUUF(int N)
    {
        super(N);
        sz = new int[N];
        for (int i = 0;i < sz.length; i++) {sz[i] = 1;}//all set size are init to 1
    }

    @Override
    public void union(int p, int q)
    {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;//they are the same site
        //the smaller one attach to the bigger one
        if (sz[pRoot] < sz[qRoot]) {id[pRoot] = qRoot; sz[qRoot] += sz[pRoot];}
        else {id[qRoot] = pRoot; sz[pRoot] += sz[qRoot];}
        count--;
    }
}

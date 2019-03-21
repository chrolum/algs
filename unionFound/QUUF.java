package unionFound;

/**
 * This class implememt the quick union algs
 * union(p, q) will make id[q] point to q, the set will look like a linkedList, where all node will point to the same root node
 * it take O(1)
 * find() will take a little bit slow to find the set they belong
 * it will update the pointer, until id[p] == p, which mean this node is the end of the set list, take O(N) time
 *
 * different set have different root node
 *
 * Technically, we use a id[] array descript a forest, each tree in forest is set
 *
 * But, there will be a problem: the height of tree may be very large, we may think a algs to optimize (WeightedQuickUnion)
 */

public class QUUF extends AbstractUF
{
    public QUUF(int N)
    {
        super(N);
    }

    @Override
    public int find(int p)
    {
        while (p != id[p]) {p = id[p];}//get the front node until arrive the root
        return p;
    }

    @Override
    public void union(int p, int q)//DEFINE: reset p root point to q
    {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        id[pRoot] = qRoot;

        count--;//number of set decrease by one
    }
}

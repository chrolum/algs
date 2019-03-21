package unionFound;

import edu.princeton.cs.algs4.StdIn;

public abstract class AbstractUF
{
    protected int[] id;
    protected int count;

    public AbstractUF(int N)
    {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
        }
    }

    public int getCount()
    {
        return count;
    }

    public abstract int find(int p);//what set is p belong to, can be implement in different way

    public abstract void union(int p, int q);//union two set, can be implement in different way

    public boolean connectd(int p, int q)
    {
        return find(p) == find(q);
    }

}

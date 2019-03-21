package unionFound;

/**
 * The class implement a Quick-find alg, if two node are belong to the same set, there id will be the same
 * while find what set is node belong, just return id[i], the time complexity is o(1)
 * union() method is a slow operation; we define that union(q ,p), we will set all id[i] == q into pID with iterate the whole id array
 * for each union operation, it will scan the whole id[] array
**/
public class QUF extends AbstractUF
{

    public QUF(int N)
    {
        super(N);
    }

    @Override
    public int find(int p)//if id[p] == id [q] p and q are connected
    {
        return id[p];
    }

    @Override
    public void union(int p, int q)//if id[p] == id [q] p and q are connected
    {
        // use qID to remark the new set
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) return;

        for (int i = 0; i < id.length; i++)
        {
            if (id[i] == pID) id[i] = qID;//find all node that belong to set pID, then reset them to qID
        }
        count--;// union two set , the set number will decrese one
    }
}

package unionFound;

import java.util.LinkedList;
import java.util.List;

/**
 * In find operation , it will do a loop to find the tree loop
 * if we can modify each node repoint to the root in tree path, after the loop , ALL NODE will point to the root directly
 * After long time running and fing operation, the find() time complexity will be O(1)
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class pathCompressionQUUF extends QUUF
{
    public pathCompressionQUUF(int N)
    {
        super(N);
    }

    @Override
    public int find(int p)// make the node in tree path repoint to the tree root
    {
        List<Integer> temp = new LinkedList<>();
        while (id[p] != p)
        {
            temp.add(p);
            p = id[p];
        }

        for (Integer node : temp)
        {
            id[node] = p;//make all tree node point to root directly
        }
        return p;
    }
}

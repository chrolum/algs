package ProgrammingAssign.DequesAndRandomizedQueues;

import edu.princeton.cs.algs4.StdIn;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class Permutation {
    public static void main(String[] args)//randomly chose k element from inout
    {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while (!StdIn.isEmpty())
        {
            rq.enqueue(StdIn.readString());
        }

        while (k > 0)
        {
            System.out.println(rq.dequeue());
            k--;
        }
    }
}
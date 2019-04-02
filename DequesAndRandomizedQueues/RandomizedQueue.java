package DequesAndRandomizedQueues;

import Percolation.Percolation;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Note: submit the file after removing the package statement
 * a randomizedqueue must use the array structure to implement
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int N;


    public RandomizedQueue()                 // construct an empty randomized queue
    {
        items = (Item[]) (new Object[10]);
        this.N = 0;

    }
    public boolean isEmpty()                 // is the randomized queue empty?
    {
        return N == 0;
    }
    public int size()                        // return the number of items on the randomized queue
    {
        return this.N;
    }
    public void enqueue(Item item)           // add the item
    {
        if (item == null) throw new IllegalArgumentException();
        if (items.length == N) resize(2 * items.length);
        items[N++] = item;
    }
    public Item dequeue()                    // remove and return a random item
    {
        if (isEmpty()) throw new NoSuchElementException();

        int i = StdRandom.uniform(0,N);

        Item item = items[i];
        items[i] = items[--N];//randomly choose a element and use queue end element refill it
        items[N] = null;//recycle the memory

        if (N == items.length / 4) resize(items.length / 2);
        return item;
    }
    public Item sample()                     // return a random item (but do not remove it)
    {
        if (isEmpty()) throw new NoSuchElementException();
        int i = StdRandom.uniform(N);
        return items[i];
    }
    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private Item[] randomItems;
        private int i;

        public RandomizedQueueIterator()
        {
            randomItems = Arrays.copyOf(items, N);//can not use items.lenght! items has null element
            StdRandom.shuffle(randomItems);
        }

        @Override
        public boolean hasNext()
        {
            return i < N;
        }

        @Override
        public Item next()
        {
            if (i >= N) throw new NoSuchElementException();
            return randomItems[i++];
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < this.N; i++)
        {
            copy[i] = this.items[i];
        }
        this.items = copy;
    }

    public static void main(String[] args)  // unit testing (optional)
    {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();

        //case 1: en and dequeue test
        for (int i = 0; i < 100; i++)//resize
        {
            rq.enqueue(i);
        }

        for (int i = 0; i < 90; i++)//resize
        {
            rq.dequeue();
        }

        System.out.println("current queue size is" + rq.size());

        //case2: sample() test
        System.out.println("sample is " + rq.sample());

        //case 3: iterable interface test
        for (Integer n : rq)
        {
            System.out.println(n);
        }
    }
}

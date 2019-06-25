package PriorityQueue;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/

/**
 *
 * This class is max prioruty queue implemented with heap
 */

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;//heap, pq[0] not use
    private int N = 0;

    public MaxPQ(int max) {
        pq = (Key[]) new Comparable[max+1];
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key max() {
        return pq[1];
    }

    public Key delMax() {
        Key max =pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        return max;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k ,j)) break;
            exch(k, j);
            k = j;
        }
    }
}

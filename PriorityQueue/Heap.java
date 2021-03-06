package PriorityQueue;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class Heap {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int k = N/2; k >= 1; k--) {
            sink(a, k, N);
        }

        while (N > 1) {
            exch(a, 1, N);
            sink(a, 1, --N);
        }
    }

    private static void sink(Comparable[] a, int k, int N) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(a, j ,j+1)) j++;
            if (!less(a, k, j)) break;
            exch(a, k, j);
            k = j;

        }
    }

    private static boolean less(Comparable[] a, int i, int j) {
        if (i >= a.length || j >= a.length) throw new IllegalArgumentException();
        return a[i].compareTo(a[j]) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

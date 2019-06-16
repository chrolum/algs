package sort;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class MergeSort {
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(aux, a, lo, mid);
        sort(aux, a, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid+1;
        for(int k = lo; k <= hi; k++) {
            if (i > mid) aux[k] = a[j++];
            else if (j > hi) aux[k] = a[i++];
            else if (a[j].compareTo(a[i]) == -1) aux[k] = a[j++];
            else aux[k] = a[i++];
        }
    }
}

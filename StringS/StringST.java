package StringS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class StringST<Value> {
    private static int R = 256;//ascii table
    private Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public StringST(){

    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        if (key.length() == d) {x.val = val; return x;}//update the val if key exits
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }

    public Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;//return the last one node
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }

    public void delet(String key) {

    }

    public boolean contains(String key) {
        return get(key) != null;
    }


    public boolean isEmpty() {

    }

    public String longestPrefixOf(String s) {

    }

    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new LinkedList<>();
        collect(get(root, pre, 0), pre, q);
    }

    private void collect(Node x, String pre, Queue<String> q) {
        if (x == null) return;
        if (x.val != null) q.offer(pre);
        for (char c = 0; c < R; c++) {
            collect(x.next[c], pre + c, q);
        }
    }

    public Iterable<String> keysThatMatch(String s) {

    }

    public Iterable<String> keys() {

    }
}


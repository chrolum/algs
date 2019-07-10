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
        delet(root, key, 0);
    }

    private Node delet(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) x.val = null;
        else {
            char c = key.charAt(d);
            x.next[c] = delet(x, key, d+1);
        }

        if (x.val != null) return x;

        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) return x;
        }
        return null;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }


    public boolean isEmpty() {

    }

    public String longestPrefixOf(String s) {
        int length = searchPrefix(root, s, 0, 0);
        return s.substring(0, length);
    }
    //return the longest prefix length, prefix should be a key
    private int searchPrefix(Node x, String s, int d, int length) {
        if (x == null) return length;
        if (x.val != null) length = d;//meet not empty node and update the length
        int c = s.charAt(d);
        return searchPrefix(x.next[c], s, d+1, length);
    }

    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new LinkedList<>();
        collect(get(root, pre, 0), pre, q);//start from the end node of prefix string key
        return q;
    }
    //private method for keysWithPrefix, this function collection each node in prefix path
    private void collect(Node x, String pre, Queue<String> q) {
        if (x == null) return;
        if (x.val != null) q.offer(pre);//find a key with the same prefix
        //iterate all child node
        for (char c = 0; c < R; c++) {
            collect(x.next[c], pre + c, q);
        }
    }

    // match key with pattern
    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> q = new LinkedList<>();
        collect(root, "", pat, q);
        return q;
    }

    /**
     *
     * @param x
     * @param pre the tmp match substring
     * @param pat the match pattern, which mean while match the '.', it should recurse all the child node
     * @param q the recorded queue
     */
    private void collect(Node x, String pre, String pat, Queue<String> q) {
        if (x == null) return;
        int d = pre.length();
        if (d == pat.length() && x.val != null) q.offer(pre);
        if (d == pat.length()) return;
        char next = pat.charAt(d);//current matching charater
        for (char c = 0; c < R; c++) {
            if (next == '.' || next == c) {
                collect(x.next[c], pre + c, pat, q);
            }
        }
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");//prefix is null,
    }
}


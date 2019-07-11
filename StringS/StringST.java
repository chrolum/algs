package StringS;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class StringST<Value> {
    private static int R = 256;//ascii table
    private Node root;
    private int n;//size;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public StringST(){

    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
        n++;//key nums
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
        if (key == null) throw new IllegalArgumentException();
        root = delet(root, key, 0);
    }

    private Node delet(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {x.val = null; n--;}
        else {
            char c = key.charAt(d);
            x.next[c] = delet(x.next[c], key,d+1);
        }
        if (x.val != null) return x;//not empty node, just return

        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) return x;
        }
        return null;//if both node val and child are null, this node should be deleted
    }

    public boolean contains(String key) {
        return get(key) != null;
    }


    public boolean isEmpty() {
        return root == null;
    }

    public String longestPrefixOf(String s) {
        int length = searchPrefix(root, s, 0, 0);
        return s.substring(0, length);
    }
    //return the longest prefix length, prefix should be a key
    private int searchPrefix(Node x, String s, int d, int length) {
        if (x == null) return length;
        if (x.val != null) length = d;//meet not empty node and update the length
        if (d == s.length()) return length;
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
        collect(root, new StringBuilder(), pat, q);
        return q;
    }


    /**
     *
     * @param x
     * @param pre the tmp match substring
     * @param pat the match pattern, which mean while match the '.', it should recurse all the child node
     * @param q the recorded queue
     */
    private void collect(Node x, StringBuilder pre, String pat, Queue<String> q) {
        if (x == null) return;
        int d = pre.length();
        if (d == pat.length() && x.val != null) q.offer(pre.toString());
        if (d == pat.length()) return;
        char next = pat.charAt(d);
        for (char c = 0; c < R; c++) {
            if (next == '.' || next == c) {
                collect(x.next[c], pre.append(c), pat, q);
                pre.deleteCharAt(pre.length() - 1);
            }
        }
    }
    public Iterable<String> keys() {
        return keysWithPrefix("");//prefix is null,
    }

    public int size() {
        return n;
    }

    public static void main(String[] args) {
        // build symbol table from standard input
        StringST<Integer> st = new StringST<>();
        In in = new In(args[0]);
        for (int i = 0; !in.isEmpty(); i++) {
            String key = in.readString();
            st.put(key, i);
        }

        // print results
        if (st.size() < 100) {
            StdOut.println("keys(\"\"):");
            for (String key : st.keys()) {
                StdOut.println(key + " " + st.get(key));
            }
            StdOut.println();
        }

        StdOut.println("longestPrefixOf(\"shellsort\"):");
        StdOut.println(st.longestPrefixOf("shellsort"));
        StdOut.println();

        StdOut.println("longestPrefixOf(\"shell\"):");
        StdOut.println(st.longestPrefixOf("shell"));
        StdOut.println();

        StdOut.println("keysWithPrefix(\"shor\"):");
        for (String s : st.keysWithPrefix("shor"))
            StdOut.println(s);
        StdOut.println();

        StdOut.println("keysThatMatch(\".he.l.\"):");
        for (String s : st.keysThatMatch(".he.l."))
            StdOut.println(s);

        StdOut.println("keysStartWith(\"she\"):");
        for (String s : st.keysWithPrefix("she"))
            StdOut.println(s);

        StdOut.println("The Trie contains the key \"she\":" + st.contains("she"));
        st.delet("she");
        StdOut.println("The Trie contains the key \"she\":" + st.contains("she"));
    }
}


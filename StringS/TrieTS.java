package StringS;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TrieTS<Value> {
    private static int R = 256;//ascii table
    private Node root;
    private int n;//size;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public TrieTS(){

    }

    public void put(String key, Value val) {
        if (root == null) root = new Node();
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            if (curr == null) curr = new Node();
            char c = key.charAt(i);
            curr = curr.next[c];
        }
        curr.val = val;
        n++;
    }

    public Value get(String key) {
        Node curr = root;
        int d = 0;
        while (curr != null && d < key.length()) {
            char c = key.charAt(d++);
            curr = curr.next[c];
        }
        if (curr == null) return null;
        return (Value) curr.val;
    }


    public void delet(String key) {
        if (key == null) throw new IllegalArgumentException();
        Stack<Node> stack = new Stack<>();
        Node curr = root;

        //fing the last node, and put path node into stack
        for (int i = 0; i < key.length(); i++) {
            if (curr == null) return;//no such key exist
            stack.push(curr);
            char c = key.charAt(i);
            curr = curr.next[c];
        }

        curr.val = null; n--;
        stack.pop();//pop the last one
        Node prev;

        for (int i = key.length()-1; i >= 0; i--) {
            prev = stack.pop();
            curr = prev.next[key.charAt(i)];
            if (curr.val == null) {
                for (char c = 0; c < R; c++) {
                    if (curr.next[c] != null) break;
                    if (c == R - 1) prev.next[key.charAt(i)] = null;//delet the node if the node do not have val and children
                }
            }
        }
    }


    public boolean contains(String key) {
        return get(key) != null;
    }


    public boolean isEmpty() {
        return root == null;
    }

    public String longestPrefixOf(String s) {

    }
    //return the longest prefix length, prefix should be a key

    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new LinkedList<>();
        return q;
    }

    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> q = new LinkedList<>();
        return q;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");//prefix is null,
    }

    public int size() {
        return n;
    }

    public static void main(String[] args) {
        // build symbol table from standard input
//        StringST<Integer> st = new StringST<>();
//        In in = new In(args[0]);
//        for (int i = 0; !in.isEmpty(); i++) {
//            String key = in.readString();
//            st.put(key, i);
//        }
//
//        // print results
//        if (st.size() < 100) {
//            StdOut.println("keys(\"\"):");
//            for (String key : st.keys()) {
//                StdOut.println(key + " " + st.get(key));
//            }
//            StdOut.println();
//        }
//
//        StdOut.println("longestPrefixOf(\"shellsort\"):");
//        StdOut.println(st.longestPrefixOf("shellsort"));
//        StdOut.println();
//
//        StdOut.println("longestPrefixOf(\"shell\"):");
//        StdOut.println(st.longestPrefixOf("shell"));
//        StdOut.println();
//
//        StdOut.println("keysWithPrefix(\"shor\"):");
//        for (String s : st.keysWithPrefix("shor"))
//            StdOut.println(s);
//        StdOut.println();
//
//        StdOut.println("keysThatMatch(\".he.l.\"):");
//        for (String s : st.keysThatMatch(".he.l."))
//            StdOut.println(s);
//
//        StdOut.println("keysStartWith(\"she\"):");
//        for (String s : st.keysWithPrefix("she"))
//            StdOut.println(s);
//
//        StdOut.println("The Trie contains the key \"she\":" + st.contains("she"));
//        st.delet("she");
//        StdOut.println("The Trie contains the key \"she\":" + st.contains("she"));
    }
}

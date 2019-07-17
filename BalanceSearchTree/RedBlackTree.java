package BalanceSearchTree;

import java.util.TreeMap;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class RedBlackTree<Key extends Comparable<Key>, Value> implements SymbolTable {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    TreeMap

    private Node root;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        boolean color;//color of parent link

        public Node(Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }

    public RedBlackTree() {

    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    @Override
    public void put(Key key, Value val) {
        this.root = put(root, key, val);
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val, RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);//right-leaning case, therefore rotatelrft
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);//continuely two red-link
        if (isRed(h.left) && isRed(h.right)) flipColors(h);//two-red children node

        return h;
    }


    @Override
    public Object get(Comparable comparable) {
        return null;
    }

    @Override
    public void delet(Comparable comparable) {

    }

    @Override
    public void deletMax() {

    }

    private Node deletMax(Node h) {

    }

    //Isometry Maintenance Operation
    private Node rotateLeft(Node h) {
        assert isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        assert isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    //temporary 4-node
    private void flipColors(Node h) {
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);
        h.color = RED;//the mid key was up to the parent node, it at least a 3-node
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
}

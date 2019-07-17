package BalanceSearchTree;

public interface SymbolTable<Key extends Comparable<Key>, Value> {
    public Value get(Key key);

    public void put(Key key, Value val);

    public void delet(Key key);

    public void deletMax();

    public void deletMin();

    public Key ceiling(Key key);

    public Key floor(Key key);

    public Key min();

    public Key max();

    public int rank();//return the rank of key

    public Key select();

}

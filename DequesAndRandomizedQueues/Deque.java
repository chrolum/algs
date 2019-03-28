package DequesAndRandomizedQueues;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author crkylin
 * Email:crkylin@gmail.com
 **/
public class Deque<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int size = 0;

    private class Node<Item>
    {
        private Item element;
        private Node front;
        private Node next;

        public Node()
        {

        }
    }

    public Deque()// construct an empty deque
    {
        Node head = new Node();
        Node tail = new Node();
    }
    public boolean isEmpty()                 // is the deque empty?
    {
        return head.next == null;
    }
    public int size()                        // return the number of items on the deque
    {
        return this.size;
    }
    public void addFirst(Item item)          // add the item to the front
    {

    }
    public void addLast(Item item) throws IllegalArgumentException        // add the item to the end
    {

    }
    public Item removeFirst() throws NoSuchElementException              // remove and return the item from the front
    {

    }
    public Item removeLast() throws UnsupportedOperationException             // remove and return the item from the end
    {

    }
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {

    }
    public static void main(String[] args)   // unit testing (optional)
    {

    }
}

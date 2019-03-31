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

    private class Node//use two way linkedlist to implement the queue
    {
        private Item item;
        private Node prev;
        private Node next;

        Node(Item item)
        {
            this.prev = null;
            this.next = null;
            this.item = item;
        }
    }

    public Deque()// construct an empty deque
    {
        head = new Node(null);
        tail = new Node(null);
        head.next = null;
        tail.prev = null;
    }

    public boolean isEmpty()                 // is the deque empty?
    {
        return head.next == null || tail.prev == null;
    }

    public int size()                        // return the number of items on the deque
    {
        return this.size;
    }
    public void addFirst(Item item)          // add the item to the front
    {

        if (item == null) throw new IllegalArgumentException();
        Node node = new Node(item);
        //if deque isEmpty, head and tail point node need to be reset
        if (isEmpty())
        {
            node.prev = null;
            node.next = null;//do point to tail or head, use current == null in iterator
            head.next = node;
            tail.prev = node;
        }
        else
        {
            node.prev = null;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }
        this.size++;
    }
    public void addLast(Item item)        // add the item to the end
    {
        if (item == null) throw new IllegalArgumentException();
        Node node = new Node(item);
        //if deque isEmpty
        if (isEmpty())
        {
            node.prev = null;
            node.next = null;
            head.next = node;
            tail.prev = node;
        }
        else
        {
            node.prev = tail.prev;
            node.next = null;
            tail.prev.next = node;
            tail.prev = node;
        }
        this.size++;
    }

    public Item removeFirst()             // remove and return the item from the front
    {
        if (isEmpty()) throw new NoSuchElementException();
        Node removeNode = head.next;

        if (removeNode.next == null)//only one element
        {
            head.next = null;
            tail.prev = null;
        }
        else
        {
            head.next = removeNode.next;
            removeNode.next.prev = null;
        }
        this.size--;
        return removeNode.item;
    }

    public Item removeLast() throws UnsupportedOperationException             // remove and return the item from the end
    {
        if (isEmpty()) throw new NoSuchElementException();

        Node removeNode = tail.prev;

        if (removeNode.prev == null)//one element left
        {
            head.next = null;
            tail.prev = null;
        }
        else
        {
            tail.prev = removeNode.prev;
            removeNode.prev.next = null;
        }
        this.size--;
        return removeNode.item;
    }

    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>//where is the constructor?
    {
        private Node current = head.next;
        @Override
        public boolean hasNext()
        {
            return current != null;
        }

        @Override
        public Item next()
        {
            if (current == null) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args)   // unit testing (optional)
    {
        //case 1: normal cse
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        for(Integer i : deque)
        {
            System.out.println(i);//print 3 2 1 3 2 1
        }

        System.out.println("remove element:" + deque.removeFirst());//return 3
        System.out.println("remove element:" + deque.removeFirst());//return 2
        System.out.println("remove element:" + deque.removeLast());//return 1
        System.out.println("remove element:" + deque.removeLast());//return 2

        System.out.println("current deque size is " + deque.size());//print 2

        //case two:add element after remove all element
        Deque<Integer> deque2 = new Deque<>();
        deque2.addLast(1);
        deque2.addLast(2);
        deque2.addFirst(3);
        deque2.addFirst(4);
        deque2.removeLast();
        deque2.removeLast();
        deque2.removeLast();
        deque2.removeLast();
        deque2.addLast(1);
        deque2.addFirst(2);
        deque2.addLast(3);
        for (Integer i : deque2)
        {
            System.out.println(i);//print 2 1 3
        }
        //case 3: invalid operation remove a empty deque
        Deque<Integer> d3 = new Deque<>();
        System.out.println(d3.isEmpty());//true
        d3.removeLast();//throw a exception
        //case 4 : invalided input
        d3.addFirst(null);
    }
}

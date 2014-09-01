import java.util.Iterator;
import java.lang.Integer;


/*
 * double linked list implementation
 */
public class Deque<Item> implements Iterable<Item> {
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    private class Node {
        Item item;
        Node next = null;
        Node prev = null;
    }


    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        /* check size, we can also check head and tail */
        if (0 == size)
            return true;
        else
            return false;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }   

    // insert the item at the front
    public void addFirst(Item item) {
        /* check item */
        if (null == item)
            throw new java.lang.NullPointerException();

        /* create a new node */
        Node node = new Node();
        node.item = item;

        /* 
         * add it into head.
         * if head is null, make head and tail point to new node.
         */ 
        if (null == head) {
            head = node;
            tail = node;
        } else {
            node.next   = head;
            head.prev   = node;

            head        = node;
        }

        size ++;
    }

    // insert the item at the end
    public void addLast(Item item) {
        /* check item */
        if (null == item)
            throw new NullPointerException();

        /* create a new node */
        Node node = new Node();
        node.item = item;

        /* 
         * add it into tail.
         * if tail is null, make head and tail point to new node.
         */                 
        if (null == tail) {
            head = node;
            tail = node;
        } else {
            node.prev   = tail;
            tail.next   = node;

            tail        = node;
        }

        size ++;
    }

    // delete and return the item at the front
    public Item removeFirst() {
        Item item;

        /* check whether queue is empty */
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        /* get item */
        item = head.item;
        size --;

        /* re-organize list */
        if (null == head.next) {
            head = null;
            tail = null;
        } else {
            Node tmp        = head.next; 

            head.next.prev  = null;
            head.next       = null;

            head            = tmp;
        }

        return item;
    }     

    // delete and return the item at the end
    public Item removeLast() {
        Item item;

        /* check whether queue is empty */
        if (isEmpty()) 
            throw new java.util.NoSuchElementException();

        /* get item */
        item = tail.item;
        size --;

        /* re-organize list */
        if (null == tail.prev) {
            head = null;
            tail = null;
        } else {
            Node tmp        = tail.prev;

            tail.prev.next  = null;
            tail.prev       = null;
            
            tail            = tmp;
        }

        return item;
    }      

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ListIterator();

    }

    private class ListIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            if (current != null) 
                return true;
            else
                return false;
        }

        public Item next() {
            /* check whether there is a node */
            if (! hasNext())
                throw new java.util.NoSuchElementException();

            Item item   = current.item;
            current     = current.next;

            return item;
        }

        public void remove() {
            /* not supported */
            throw new java.lang.UnsupportedOperationException();
        }
    }

    // unit testing
    public static void main(String[] args) {
        Deque<Integer> que = new Deque<Integer>();

        que.addFirst(1);
        for (Integer i: que) 
            System.out.printf("%d ", i);
        System.out.printf("\n");

        que.addLast(2);
        for (Integer i: que) 
            System.out.printf("%d ", i);
        System.out.printf("\n");

        que.removeFirst();
        for (Integer i: que) 
            System.out.printf("%d ", i);
        System.out.printf("\n");

        que.removeLast();
        for (Integer i: que) 
            System.out.printf("%d ", i);
        System.out.printf("\n");
    }

}

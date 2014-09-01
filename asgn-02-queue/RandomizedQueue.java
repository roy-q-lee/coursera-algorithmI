import java.util.Iterator;
import java.lang.Integer;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size = 0;
    private int capacity = 0;
    private final int MIN_CAPACITY = 64;
    private Item [] array;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;

        capacity = MIN_CAPACITY;
        array = (Item[]) new Object[MIN_CAPACITY];
    }

    // is the queue empty?
    public boolean isEmpty() {
        return 0 == size;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        /* check size first, if array is full, create a new array with 2*capacity */
        if (size == capacity) {
            /* create new array */
            Item [] copy = (Item[]) new Object[capacity*2];

            /* make a copy */
            for (int i = 0; i < size; i ++)
                copy[i] = array[i];

            /* fix capacity */
            capacity *= 2;

            /* switch to new array */
            array = copy;
        }

        /* insert item */
        array[size] = item;

        size ++;
    }

    // delete and return a random item
    public Item dequeue() {
        /* choose a random item */
        int i = (int) (Math.random() * size);
        Item item = array[i];

        /* swap last item this chosen item */
        if (i != size-1)
            array[i] = array[size-1];

        size --;

        /* halve array if its size is smaller than capacity/4 */
        if ((size < capacity/4) && (capacity > MIN_CAPACITY)) {
            /* create new array */
            Item [] copy = (Item[]) new Object[capacity/2];

            /* make a copy */
            for (int j = 0; j < size; j ++) 
                copy[j] = array[j];

            /* fix capacity */
            capacity /= 2;

            /* switch to new array */
            array = copy; 
        }

        return item;
    }

    // return (but do not delete
    public Item sample() {
        /* choose a random item */
        int i = (int) (Math.random() * size);

        return array[i];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int index = 0;

        public boolean hasNext() {
            return (0 != size) && (index < size);
        }

        public Item next() {
            if (! hasNext())
                throw new java.util.NoSuchElementException();

            index ++;

            return array[index-1];
        }

        public void remove() {
            /* not supported */
            throw new java.lang.UnsupportedOperationException();
        }
    }

    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<Integer> rque = new RandomizedQueue<Integer>();

        for (int i = 0; i < 10; i ++) {
            rque.enqueue(i); 
        }

        System.out.printf("size of queue: %d\n", rque.size());

        for (int i = 0; i < 10; i ++) {
            System.out.printf("removed: %d\n", rque.dequeue());
        }

    }
}

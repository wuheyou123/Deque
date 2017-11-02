import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizeQueue<Item> implements Iterable<Item> {

    private class Node{
        Item value;
        Node next;
        private Node(Item x) {
            value = x;
            next = null;
        }
        private Node(Item x, Node n) {
            value = x;
            next = n;
        }

    }
    private int size;
    private Node sentinel;
    // private Node last;

    public RandomizeQueue() {
        size = 0;
        sentinel = new Node(null);
        // last = sentinel;

    }

    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void enqueue(Item item)  {
        if (item == null) {
            throw new IllegalArgumentException("adding null to the queue.");
        }
        Node newnode = new Node(item);
        newnode.next = sentinel.next;
        sentinel.next = newnode;
        // if(size == 0) last = newnode;
        size++;
    }
    public Item dequeue()    {
        if(isEmpty()) {
            throw new NoSuchElementException("dequeue from an empty queue");
        }

        Node rnode = getRandomNodePrevious();
        Item item = rnode.next.value;
        rnode.next = rnode.next.next;
        size--;

        return item;
    }
    private Node getRandomNodePrevious() {


        Random r = new Random();
        int rint = r.nextInt(size);

        Node dnode = sentinel;

        for(int i = 0; i < rint; i++) {

            dnode = dnode.next;
        }
        return dnode;

    }

    public Item sample()   {
        if(isEmpty()) {
            throw new NoSuchElementException("sample from an empty queue");
        }

        Node rnode = getRandomNodePrevious();
        return rnode.next.value;

    }                  // return (but do not remove) a random item


    public Iterator<Item> iterator() {
        return new RQIterator();
    }       // return an independent iterator over items in random order

    private class RQIterator<Item> implements Iterator<Item> {

        private Node current = sentinel.next;
        @Override
        public Item next() {
            if(current == null) {
                throw new NoSuchElementException("end of the queue, no more elements");
            }

            Item vl = (Item) current.value;

            if(hasNext()) {

                current = current.next;
            }
            return vl;
        }

        @Override
        public boolean hasNext() {
            return (current != null) && (current.next != null);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove() in iterator is not implemented");
        }
    }
    public static void main(String[] args){

        RandomizeQueue<Integer> rq = new RandomizeQueue<>();


        for(int i = 0; i < 20; i++) {
            rq.enqueue((Integer)i);
        }

        rq.enqueue(21);
        rq.enqueue(22);


        System.out.print(rq.dequeue() + " " + rq.dequeue());
    }   // unit testing (optional)

}


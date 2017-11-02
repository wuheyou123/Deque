import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements  Iterable<Item> {


    private final Node<Item> sentinel;
    private int size;

    private class Node<Item> {
        private Item value;
        private Node<Item> next;
        private Node<Item> previous;

        private Node() {
            value = null;
            next = null;
            previous = null;

        }
        private Node(Item x, Node<Item> p, Node<Item> n) {
            value = x;
            previous = p;
            next = n;

        }
    }


    public Deque() {
        size = 0;
        sentinel = new Node<>();
        sentinel.next = sentinel;
        sentinel.previous = sentinel;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("adding null item. please check");
        }

        Node<Item> newnode = new Node<>(item, sentinel, sentinel.next);
        sentinel.next.previous = newnode;
        sentinel.next = newnode;
        size++;

    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("adding null item. please check");
        }
        Node<Item> newnode = new Node<>(item, sentinel.previous, sentinel);
        sentinel.previous.next = newnode;
        sentinel.previous = newnode;
        size++;
    }

    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException(" Remove from empty queue, please check!! ");
        }

        // Node<Item> firstnode = sentinel.next;
        Item first = sentinel.next.value;
        sentinel.next = sentinel.next.next;
        sentinel.next.previous = sentinel;

        size--;
        return  first;
    }

    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException(" Remove from empty queue, please check!! ");
        }
        // Node<Item> lastnode = sentinel.previous;
        Item last = sentinel.previous.value;
        sentinel.previous = sentinel.previous.previous;
        sentinel.previous.next = sentinel;
        size--;
        return last;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LLIterator();
    }

    private class LLIterator implements  Iterator<Item> {
        private Node<Item> current = sentinel.next;
        @Override
        public boolean hasNext() {
            return (size > 0) && (current != sentinel);
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("End of the QUEUE, no next element");
            Item t = current.value;
            current = current.next;
            return t;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("this class does not support Iterator.remove ");
        }
    }



    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<>();

        for (int i = 0; i < 15; i++) {
            dq.addFirst(i);
        }

        dq.addLast(21);
        dq.addFirst(22);
        for (int i = 0; i < 17; i++) {
            dq.removeLast();
        }
        // dq.removeFirst();

        // System.out.print(dq.removeFirst() + " " + dq.removeLast());

    }


}

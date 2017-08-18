import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements  Iterable<Item> {

    private Item[] items;
    private int size;
    private int nextfirst;
    private int nextlast;

    public Deque() {
        size = 0;
        items = (Item[]) new Object[20];
        nextfirst = 0;
        nextlast = 1;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if(item == null) {
            throw new IllegalArgumentException("adding null item. please check");
        }
        if (size == items.length) {
            increaseSize();
        }
        items[nextfirst] = item;
        nextfirst = minusIndex(nextfirst);
        size++;

    }

    public void addLast(Item item) {
        if(item == null) {
            throw new NoSuchElementException("adding null item. please check");
        }
        if (size == items.length) {
            increaseSize();
        }
        items[nextlast] = item;
        nextlast = addIndex(nextlast);
        size++;
    }

    public Item removeFirst() {
        if(size == 0) {
            throw new NoSuchElementException(" Remove from empty queue, please check!! ");
        }


        if (items.length > 20 && size == items.length / 4) {
            decreaseSize();
        }

        nextfirst = addIndex(nextfirst);
        Item first = items[nextfirst];
        items[nextfirst] = null;
        size--;
        return  first;
    }

    public Item removeLast() {
        if(size == 0) {
            throw new UnsupportedOperationException(" Remove from empty queue, please check!! ");
        }
        if (items.length > 20 && size == items.length / 4) {
            decreaseSize();
        }
        nextlast = minusIndex(nextlast);
        Item last = items[nextlast];
        items[nextlast] = null;
        size--;
        return last;
    }

    @Override
    public Iterator<Item> iterator() {
        return new arrayIterator();
    }

    private class arrayIterator implements  Iterator<Item> {
        int current = addIndex(nextfirst);
        @Override
        public boolean hasNext() {
            return (size > 0) && (current!=minusIndex(nextlast));
        }

        @Override
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException("End of the QUEUE, no next element");
            Item t = items[current];
            current = addIndex(current);
            return t;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("this class does not support Iterator.remove ");
        }
    }

    private void increaseSize() {

        Item[] newary = (Item[]) new Object[size * 2];
        System.arraycopy(items, nextlast, newary, size + nextlast, size - nextlast);
        System.arraycopy(items, 0 , newary, 0, nextlast);
        // System.arraycopy(newary, size + nextlast, items, nextlast, size - nextlast);
         // System.arraycopy(newary, 0 , items, 0, nextlast);
        if (nextlast != 0) {
            nextfirst = nextfirst + size;
        }
        items = newary;
    }

    private void decreaseSize() {
        int length = items.length;
        Item[] newary = (Item[]) new Object[length / 2];
        if (nextlast < nextfirst) {
            System.arraycopy(items, nextfirst, newary, nextfirst - length / 2, length - nextfirst);
            System.arraycopy(items, 0, newary, 0, nextlast);

            nextfirst = nextfirst - length / 2;
        } else {
            System.arraycopy(items, nextfirst, newary, 0, size + 1);
            // System.arraycopy(newary, 0, items, nextfirst, size + 1);
            nextfirst = 0;
            nextlast = size + 1;
        }

        items =  newary;
    }

    private int minusIndex(int i) {
        return i == 0 ? items.length - 1 : i - 1;
    }

    private int addIndex(int i) {
        return i == items.length - 1 ? 0 : i + 1;
    }

    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<>();

        for(int i = 0; i < 20; i++) {
            dq.addFirst((Integer)i);
        }

        dq.addLast(21);
        dq.addFirst(22);
        for( int i = 0; i< 15; i++) {
            dq.removeLast();
        }

        System.out.print(dq.removeFirst() + " " + dq.removeLast());

    }


}

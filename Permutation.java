 import edu.princeton.cs.algs4.StdIn;


public class Permutation {

    public static void main(String[] args) {

        // System.out.print(args[0]);
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rdmQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            rdmQueue.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            System.out.println(rdmQueue.dequeue());
        }


    }
}

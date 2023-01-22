package queue;


import java.util.Arrays;
import java.util.Objects;


// Разверни если ты лучший практик

//                      §§____________§§
//                   §§§__§_§§____§§_§__§§§
//                  §__§__§§__§__§__§§__§__§
//                  §__§§§_§__§§§§__§_§§§__§
//                 §§§§_____§§§__§§§_____§§§§
//                §__§________§__§________§__§
//                §__§_________§§_________§__§
//                 §§______________________§§
//                §__§_____§§§§__§§§§_____§__§
//                §__§____§§§§§§§§§§§§____§__§
//                 §§§§____§§§§§§§§§§____§§§§
//                  §__§____§§§00§§§____§__§
//                  §__§_____§§§§§§_____§__§
//                   §§§§_____§§§§_____§§§§
//                    §__§_____§§_____§__§
//                    §__§§§________§§§__§
//                     §§§__§______§__§§§
//                       §__§§§__§§§__§
//                        §§§__§§__§§§
//                         §__§§§§__§
//                          §§____§§
//                           §____§
//                            §§§§

// Invariant:
// FIFO - first in first out
// size() >= 0, Queue.length >= 4
// head point at first to dequeue, tail to next to enqueue
public class ArrayQueueModule {
    private static Object[] Queue = new Object[4];
    private static int head = 0;
    private static int tail = 0;

    // Pre: true
    // Post: True if a[i] = null for all i : 0 <= i < Queue.length, ArrayQueue immutable
    public static boolean isEmpty() {
        return head == tail;
    }

    // Pre: true
    // Post: x : head points at x, ArrayQueue immutable
    public static Object element() {
        return Queue[head];
    }

    // Pre: true
    // Post: x, head = (head + 1) % Queue.length
    public static Object dequeue() {
        if (isEmpty()) return null;
        Object x = element();
        Queue[head] = null;
        head = (head + 1) % Queue.length;
        return x;
    }

    // Pre: true
    // Post: 0 <= size() < Queue.length, ArrayQueue immutable
    public static int size() {
        if (head > tail) return Queue.length - head + tail;

        else return tail - head;
    }

    // Pre: x != null
    // Post: size() += 1, if size() + 1 == Queue.length => Queue.length *= 2
    public static void enqueue(final Object x) {
        Objects.requireNonNull(x);
        if (Queue.length < size() + 2) Queue = ensureCapacity(size() + 2);
        Queue[tail] = x;
        tail = (tail + 1) % Queue.length;
    }

    // Pre: size() + 1 == Queue.length
    // Post: Queue.length *= 2, head = 0, tail = Queue.length, order saved
    private static Object[] ensureCapacity(int size) {
        Object[] ExtendedQueue = Arrays.copyOf(Queue, Queue.length * 2);
        int x = size() + 1;
        for (int i = 0; i < x; i++) {
            ExtendedQueue[i] = dequeue();
        }
        head = 0;
        tail = Queue.length - 1;
        return ExtendedQueue;
    }

    // Pre: true
    // Post: for all i : (0 <= i < Queue.length) Queue[i] = null, head = 0, tail = 0
    public static void clear() {
        Arrays.fill(Queue, null);
        head = 0;
        tail = 0;
    }

    public static void check() {
        System.out.println(getHead() + " " + getTail() + " " + size());
        for (Object o : Queue) {
            System.out.print(o + " ");
        }
        System.out.println();
    }

    public static int getHead() {
        return head;
    }

    public static int getTail() {
        return tail;
    }

    public static int length() {
        return Queue.length;
    }

    // Pre: x != null
    // Post: amount of x in ArrayQueue
    public static int count(Object x) {
        Objects.requireNonNull(x);
        int count = 0;
        for (Object o : Queue) {
            if (Objects.equals(o, x)) {
                count++;
            }
        }
        return count;
    }
}

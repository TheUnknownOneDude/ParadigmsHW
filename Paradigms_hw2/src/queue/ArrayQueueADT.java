package queue;


import java.util.Arrays;
import java.util.Objects;

// Invariant:
// FIFO - first in first out
// size() >= 0, Queue.length >= 4
// head point at first to dequeue, tail to next to enqueue
public class ArrayQueueADT {
    private Object[] Queue = new Object[2];
    private int head = 0;
    private int tail = 0;
    private int JumpPoint;

    // Pre: true
    // Post: True if a[i] = null for all i : 0 <= i < Queue.length
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.head == queue.tail;
    }

    // Pre: true
    // Post: x : head points at x
    public static Object element(ArrayQueueADT queue) {
        return queue.Queue[queue.head];
    }

    // Pre: true
    // Post: x, head = (head + 1) % Queue.length
    public static Object dequeue(ArrayQueueADT queue) {
        if (isEmpty(queue)) return null;
        Object x = element(queue);
        queue.Queue[queue.head] = null;
        queue.head = (queue.head + 1) % queue.Queue.length;
        return x;
    }

    // Pre: true
    // Post: 0 <= size() < Queue.length
    public static int size(ArrayQueueADT queue) {
        if (queue.head > queue.tail) return queue.Queue.length - queue.head + queue.tail;

        else return queue.tail - queue.head;
    }

    // Pre: true
    // Post: size() += 1, if size() + 1 == Queue.length => Queue.length *= 2
    public static void enqueue(ArrayQueueADT queue, final Object x) {
        Objects.requireNonNull(x);
        if (queue.Queue.length < size(queue) + 2) queue.Queue = ensureCapacity(queue, size(queue) + 2);
        queue.Queue[queue.tail] = x;
        queue.tail = (queue.tail + 1) % queue.Queue.length;
    }

    // Pre: size() + 1 == Queue.length
    // Post: Queue.length *= 2, head = 0, tail = Queue.length, order saved
    private static Object[] ensureCapacity(ArrayQueueADT queue, int size) {
        Object[] ExtendedQueue = Arrays.copyOf(queue.Queue, queue.Queue.length * 2);
        int x = size(queue) + 1;
        for (int i = 0; i < x; i++) {
            ExtendedQueue[i] = dequeue(queue);
        }
        queue.head = 0;
        queue.tail = queue.Queue.length - 1;
        return ExtendedQueue;
    }

    // Pre: true
    // Post: for all i : (0 <= i < Queue.length) Queue[i] = null, head = 0, tail = 0
    public static void clear(ArrayQueueADT queue) {
        Arrays.fill(queue.Queue, null);
        queue.head = 0;
        queue.tail = 0;
    }

    public static void check(ArrayQueueADT queue) {
        System.out.println(getHead(queue) + " " + getTail(queue));
        for (Object o : queue.Queue) {
            System.out.print(o + " ");
        }
        System.out.println();
    }

    public static int getHead(ArrayQueueADT queue) {
        return queue.head;
    }

    public static int getTail(ArrayQueueADT queue) {
        return queue.tail;
    }

    public static int length(ArrayQueueADT queue) {
        return queue.Queue.length;
    }

    // Pre: x != null
    // Post: amount of x in ArrayQueue
    public static int count(ArrayQueueADT queue, Object x) {
        Objects.requireNonNull(x);
        int count = 0;
        for (Object o : queue.Queue) {
            if (Objects.equals(o, x)) {
                count++;
            }
        }
        return count;
    }

}


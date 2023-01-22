package queue;

// Model: a[1]...a[n]

// Pre: x != null
// Post: size() += 1, if size() + 1 == Queue.length => Queue.length *= 2
// enqueue()

// Pre: true
// Post: x : head pointed at x, head = (head + 1) % Queue.length
// dequeue()

// Pre: true
// Post: True if a[i] = null for all i : 0 <= i < Queue.length, ArrayQueue immutable
// isEmpty()

// Pre: true
// Post: 0 <= size() < Queue.length, ArrayQueue immutable
// size()

// Pre: true
// Post: x : head points at x, ArrayQueue immutable
// element()

// Pre: true
// Post: for all i : (0 <= i < Queue.length) Queue[i] = null, head = 0, tail = 0
// clear()

// Pre: true
// Post: count : amount of x in queue : pred.test(x) == true
// countIf

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

// Invariant:
// FIFO - first in first out
// size() >= 0, Queue.length >= 1
// head point at first to dequeue, tail to next to enqueue
public class ArrayQueue extends AbstractQueue{
    private Object[] Queue = new Object[1];
    private int head = 0;
    private int tail = 0;


    // Pre: true
    // Post: x : head points at x, ArrayQueue immutable
    public Object element() {
        return Queue[head];
    }

    // Pre: true

    @Override
    public Object dequeueImpl() {
        Object x = element();
        Queue[head] = null;
        head = (head + 1) % Queue.length;
        return x;
    }

    public void enqueueImpl(final Object x) {
        if (Queue.length < size + 1) Queue = ensureCapacity(size + 1);
        Queue[tail] = x;
        tail = (tail + 1) % Queue.length;
    }

    // Pre: size() + 1 == Queue.length
    // Post: Queue.length *= 2, head = 0, tail = Queue.length, order saved
    private Object[] ensureCapacity(int size) {
        Object[] ExtendedQueue = Arrays.copyOf(Queue, Queue.length * 2);
        int x = this.size + 1;
        for (int i = 0; i < x; i++) {
            ExtendedQueue[i] = dequeue();
            this.size++;
        }
        head = 0;
        tail = Queue.length - 1;
        return ExtendedQueue;
    }

    // Pre: true
    // Post: for all i : (0 <= i < Queue.length) Queue[i] = null, head = 0, tail = 0
    public void clear() {
        Arrays.fill(Queue, null);
        head = 0;
        tail = 0;
        size = 0;
    }

    // Pre: x != null
    // Post: amount of x in ArrayQueue
    public int count(Object x) {
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


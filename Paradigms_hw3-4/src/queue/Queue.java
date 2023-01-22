package queue;

// Invariant:
// FIFO - first in first out
// size() >= 0
// head point at first to dequeue, tail to next to enqueue
public interface Queue {

    // Pre: true
    // Post: x : tail points at x, Queue size += 1
    public void enqueue(Object x);

    // Pre: size >= 1
    // Post: x : head pointed at x, Queue size -= 1
    public Object dequeue();

    // Pre: true
    // Post: True queue contains 0 elements, Queue immutable
    public boolean isEmpty();

    // Pre: true
    // Post: returns amount of objects in Queue, Queue is immutable
    public int size();

    // Pre: true
    // Post: makes of elements of Queue null
    public void clear();

    // Pre: true
    // Post: x : head points at x, Queue immutable
    public Object element();
}

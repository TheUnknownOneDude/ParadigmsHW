package queue;

import java.util.Objects;
import java.util.function.Predicate;

public abstract class AbstractQueue implements Queue {
    protected int size;


    // Pre: size >= 1
    // Post: x : head pointed at x, size -= 1
    public Object dequeue() {
        assert size >= 1;
        Object x = dequeueImpl();
        size--;
        return x;
    }

    // Pre: true
    // Post: x : tail points at x, size += 1
    public void enqueue(final Object x) {
        Objects.requireNonNull(x);
        size++;
        enqueueImpl(x);
    }

    // Pre: true
    // Post: count : amount of x in queue : pred.test(x) == true
    public int countIf(Predicate<Object> pred) {
        int count = 0;
        for (int i = 0; i < size; i++) {
           Object x = dequeue();
           if (pred.test(x)) count++;
           enqueue(x);
        }
        return count;
    }

    protected abstract void enqueueImpl(final Object x);

    protected abstract Object dequeueImpl();

    // Pre: true
    // Post: false if size != 0, True if size == 0
    public boolean isEmpty() {
        return size == 0;
    }

    // Pre: true
    // Post: amount of objects in queue
    public int size() {
        return size;
    }

}

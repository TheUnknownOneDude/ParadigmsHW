package queue;

//             head               next                         tail
//              ▐                  ▐                            ▐
//          ▐  Node  ▐         ▐  Node  ▐                   ▐  Node  ▐
//          ▐ -------▐         ▐ -------▐                   ▐ -------▐
//          ▐ element▐         ▐ element▐                   ▐ element▐
//          ▐  next  ▐ ------> ▐  next  ▐ ------>...------> ▐  next  ▐ ------> null
//          ▐▬▬▬▬▬▐         ▐▬▬▬▬▬▐                   ▐▬▬▬▬▬▐


// Pre: x != null
// Post: size()' = size() + 1, new Node created, linked to previous Node
// enqueue()

// Pre: size >= 1
// Post: x : head pointed at x, size -= 1
// dequeue()

// Pre: true
// Post: delete all Nodes, head = null, tail = null
// clear()

// Pre: true
// Post: size() = amount of Nodes, LinkedQueue immutable
// size()

// Pre: true
// Post: True if tail && head = null, size == 0, LinkedQueue immutable
// isEmpty()

// Pre: true
// Post: x : head points at x, LinkedQueue immutable
// element()

// Pre: true
// Post: count : amount of Nodes.x : pred.test(x) == true
// countIf

import java.util.Objects;
import java.util.function.Predicate;

// Invariant:
// FIFO - first in first out
// size() >= 0
// head point at first to dequeue, tail to next to enqueue
public class LinkedQueue extends AbstractQueue {
    private Node head = null;
    private Node tail = null;


    public void enqueueImpl(final Object x) {
        if (size != 1) {
            Node next = new Node(x, null);
            tail.next = next;
            tail = next;
        } else {
            tail = new Node(x, null);
            head = tail;
        }
    }

    // Pre: size >= 1
    // Post: x : head pointed at x, size -= 1

    @Override
    public Object dequeueImpl() {
        Object x = element();
        if (size == 0) {
            tail = null;
            head = null;
        } else {
            head = head.next;
        }
        return x;
    }

    // Pre: true
    // Post: x : head points at x, LinkedQueue immutable
    public Object element() {
        assert size >= 1;
        return head.x;
    }

    // Pre: true
    // Post: delete all Nodes, head = null, tail = null
    public void clear() {
        while (size != 0) {
            dequeue();
        }
    }


    private static class Node {
        private final Object x;
        private Node next;

        private Node(Object x, Node next) {
            this.x = x;
            this.next = next;
        }
    }
}

package queue;

import java.util.Arrays;
import java.util.Objects;

public class Hashik {
    public static void main(String[] args) {
        System.out.println("""
package queue;


import java.util.Arrays;
import java.util.Objects;


/** Model a[1]...a[n]
 *
 enqueue()



 **/

        public class ArrayQueueModule {
            private static Object[] Queue = new Object[4];
            private static int head = 0;
            private static int tail = 0;

            public static boolean isEmpty() {
                return head == tail;
            }

            public static Object element() {
                return Queue[head];
            }

            public static Object dequeue() {
                if (isEmpty()) return null;
                Object x = element();
                Queue[head] = null;
                head = (head + 1) % Queue.length;
                return x;
            }

            public static int size() {
                if (head > tail) return Queue.length - head + tail;

                else return tail - head;
            }

            public static void enqueue(final Object x) {
                Objects.requireNonNull(x);
                if (Queue.length < size() + 2) Queue = ensureCapacity(size() + 2);
                Queue[tail] = x;
                tail = (tail + 1) % Queue.length;
            }

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

            public static void clear() {
                Arrays.fill(Queue, null);
                head = 0;
                tail = 0;
            }

            public static void check() {
                //System.out.println(getHead() + " " + getTail() + " " + size());
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


            private static Object[] toArray() {
                Object[] x = new Object[size() + 1];
                if (head < tail) {
                    for (int i = head; i < tail + 1; i++) {
                        x[i - head] = Queue[i];
                    }
                } else {
                    for (int i = 0; i < size(); i++) {
                        x[i] = Queue[(head + i) % Queue.length];
                    }
                }
                return x;
            }

            public static String toStr() {
                Object[] x = toArray();
                StringBuilder sb = new StringBuilder("[");
                for (int i = 0; i < x.length - 1; i++) {
                    if (i != x.length - 2) {
                        sb.append(x[i]); sb.append(", ");
                    } else {
                        sb.append(x[i]);
                    }
                }
                sb.append("]");
                return sb.toString();
            }}

                """.hashCode());
    }
}

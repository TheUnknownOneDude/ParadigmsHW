package queue;


public class LinkedQueueTest {
    static LinkedQueue Linked = new LinkedQueue();
    static ArrayQueue Array = new ArrayQueue();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Linked.enqueue(i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.print(Linked.dequeue() + " ");
        }


        for (int i = 0; i < 10; i++) {
            Linked.enqueue(i);
        }

        Linked.clear();

        System.out.println();

        int x = Array.size;
        for (int i = 0; i < x; i++) {
            System.out.print(Array.dequeue() + " ");
        }
    }
}

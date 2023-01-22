package queue;


public class ArrayQueueModuleTest {

    public static void main(String[] args) {


        System.out.println(ArrayQueueModule.element());
        for (int i = 0; i < 5; i++) {
            ArrayQueueModule.enqueue(i);
        }

        for (int i = 0; i < 5; i++) {
            ArrayQueueModule.enqueue("0.1" + i);
        }

        for (int i = 0; i < 5; i++) {
            ArrayQueueModule.dequeue();
        }

        for (int i = 5; i < 14; i++) {
            ArrayQueueModule.enqueue(0.1 + i);
        }
        ArrayQueueModule.check();

//        for (int i = 0; i < 3; i++) {
//            ArrayQueueModule.dequeue();
//        }
//        ArrayQueueModule.check();
//        System.out.println(ArrayQueueModule.toStr());
//        for (int i = 5; i < 9; i++) {
//            ArrayQueueModule.enqueue(i);
//        }
//        System.out.println(ArrayQueueModule.toStr());
//        for (int i = 9; i < 16; i++) {
//            ArrayQueueModule.enqueue(i);
//        }
//        System.out.println(ArrayQueueModule.toStr());

//        for (int i = 0; i < 3; i++) {
//            ArrayQueueModule.dequeue();
//        }
//
//
//        for (int i = 5; i < 13; i++) {
//            ArrayQueueModule.enqueue(i);
//        }
//
//        ArrayQueueModule.enqueue(13);
//        ArrayQueueModule.check();
//
//
//        for (int i = 3; i < 14; i++) {
//            System.out.print(ArrayQueueModule.dequeue() + " ");
//        }
//        System.out.println();
//        ArrayQueueModule.check();


//        for (int i = 14; i < 33; i++) {
//            ArrayQueueModule.enqueue(i);
//        }
//
//        ArrayQueueModule.check();
//
//        for (int i = 14; i < 33; i++) {
//            System.out.println(ArrayQueueModule.dequeue());
//        }


//        for (int i = 0; i < 15; i++) {
//            System.out.println(ArrayQueueModule.dequeue());
//        }
    }
}

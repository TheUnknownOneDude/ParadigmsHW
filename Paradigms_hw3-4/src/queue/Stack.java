package queue;

import java.util.Arrays;

public class Stack {
    public Object[] steck = new Object[1];
    public int top = 0;


    public Stack(Object ... args) {
        for (Object arg : args) {
            push(arg);
        }
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public void push(Object x) {
        if (top == steck.length) steck = resizePlus(steck);

        steck[top++] = x;
    }

    private Object[] resizePlus(Object[] steck) {
        steck = Arrays.copyOf(steck, steck.length * 2);
        return steck;
    }

    private Object[] resizeMinus(Object[] steck) {
        steck = Arrays.copyOf(steck, steck.length / 2);
        return steck;
    }

    public Object pop() {
        if (isEmpty()) {
            System.out.println("stack is empty");
            return null;
        }
        if (top < steck.length / 4) steck = resizeMinus(steck);
        Object x = steck[top - 1];
        steck[top - 1] = null;
        top--;
        return x;
    }

    public void show() {
        for (int i = 0; i < top; i++) {
            System.out.print(steck[i] + " ");
        }
    }

    public void clear() {
        Arrays.fill(steck, null);
    }

    public void swap() {
        if (top < 2) return;

        Object x = steck[top - 1];
        steck[top - 1] = steck[0];
        steck[0] = x;
    }

}

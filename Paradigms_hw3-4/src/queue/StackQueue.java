package queue;

import java.util.Arrays;

public class StackQueue {
    ArrayStack left_stack = new ArrayStack();
    ArrayStack right_stack = new ArrayStack();


    public void push(Object x) {
        left_stack.push(x);
    }

    public Object pop() {
        if (!(right_stack.isEmpty())) return right_stack.pop();

        else {
            while (!(left_stack.isEmpty())) {
                right_stack.push(left_stack.pop());
            }
            return right_stack.pop();
        }
    }   

    public class ArrayStack {
        public Object[] stack = new Object[1];
        private int top = 0;

        public boolean isEmpty() {
            return top == 0;
        }

        public void push(Object x) {
            //Objects.requireNonNull(x);
            if (top + 1 == stack.length) resize();
            stack[top++] = x;
        }

        public Object pop() {
            if (isEmpty()) return null;

            else return stack[top--];
        }

        private void  resize() {
            stack = Arrays.copyOf(stack, stack.length * 2);
        }
    }

}

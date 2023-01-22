package queue;

public class StackTest {
    public static void main(String[] args) {
        Stack stack = new Stack();
        Stack stack1 = new Stack(1, 2, "3", "<", "Penis");

        System.out.println(stack.isEmpty());
        System.out.println(stack1.isEmpty());
        stack.show();
        System.out.println();
        stack1.show();
        stack1.pop();
        stack1.pop();
        stack.push("wqwqw");
        stack.push(1212332322);
        System.out.println();
        stack.show();
        stack.swap();
        System.out.println();
        stack.show();
        System.out.println();
        stack1.swap();
        System.out.println();
        stack1.show();
    }
}

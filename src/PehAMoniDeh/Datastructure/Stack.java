package PehAMoniDeh.Datastructure;

public class Stack {
    private final int SIZE = 15;
    private int[] abanieStack;
    private int top;

    public Stack(){
        abanieStack = new int[SIZE];
        top = -1;

    }

    public void push(int item) {
        abanieStack[++top] = item;
    }

    public int pop() {
        return abanieStack[top--];
    }

    public int peek() {
        return abanieStack[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

}

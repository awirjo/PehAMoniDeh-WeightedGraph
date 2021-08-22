package PehAMoniDeh.Datastructure;

import java.util.Arrays;

public class PathStack {
    private final int SIZE = 15;
    private int[] abanieStack;
    private int top;

    public PathStack(){
        abanieStack = new int[SIZE];
        top = -1;
    }

    public void push(int item) {
        abanieStack[++top] = item;
    }

    public int pop() {
        return abanieStack[top--];
    }

    public int peek() {return abanieStack[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public void showStack() {
        System.out.println(Arrays.toString(abanieStack));
    }

}

package PehAMoniDeh.Datastructure;

import PehAMoniDeh.Entity.Abanies;

import java.util.Arrays;

public class StackDisplayPath {
    private final int SIZE = 15;
    private Abanies[] abanieStack;
    private int top;


    public StackDisplayPath(){
        abanieStack = new Abanies[SIZE];
        top = -1;
    }

    public void push(Abanies abaniesStack) {
        abanieStack[++top] = abaniesStack;
    }

    public Abanies pop() {
        return abanieStack[top--];
    }

    public Abanies peek() {
        return abanieStack[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public void showStack() {
        System.out.println(Arrays.toString(abanieStack));
    }

}

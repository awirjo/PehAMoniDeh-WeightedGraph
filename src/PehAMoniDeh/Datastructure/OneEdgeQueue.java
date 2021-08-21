package PehAMoniDeh.Datastructure;

import java.util.Arrays;

public class OneEdgeQueue {

    private final int SIZE = 15;
    private int[] queArray;
    private int front;
    private int rear;

    public OneEdgeQueue() {
        queArray = new int[SIZE];
        front = 0;
        rear = -1;
    }

    public void insert(int item) {
        if(rear == SIZE-1)
            rear = -1;
        queArray[++rear] = item;
    }

    public int remove() {
        int temp = queArray[front++];
        if (front == SIZE)
            front = 0;
        return temp;
    }

    public boolean isEmpty() {
        return ((rear + 1 == front) || (front + SIZE - 1 == rear));
    }

    public void makeEmpty() {
        front = 0;
        rear = -1;
    }

    public void showQueue() {
        System.out.println(Arrays.toString(queArray));
    }

}

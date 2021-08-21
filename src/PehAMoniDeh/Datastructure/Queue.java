package PehAMoniDeh.Datastructure;

public class Queue {
    private final int ABANIES_SIZE = 30;
    private int[] abanieQueue;
    private int front;
    private int rear;

    public Queue(){
        abanieQueue = new int[ABANIES_SIZE];
        front = 0;
        rear = -1;
    }

    public void insert(int rearAbanie){ // put item at rear of queue
        if (rear == ABANIES_SIZE - 1)
            rear = -1;
            abanieQueue[++rear] = rearAbanie;
    }
    public int remove(){  // take item from front of queue
        int temp = abanieQueue[front++]; if(front == ABANIES_SIZE)
            front = 0;

        return temp;
    }
    public boolean isEmpty(){
        return ( rear+1==front || (front+ABANIES_SIZE-1==rear) );
    }


}

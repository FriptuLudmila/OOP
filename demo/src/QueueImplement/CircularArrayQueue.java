package QueueImplement;

import interfaces.Queue;
import exceptions.FullQueueException;
import exceptions.EmptyQueueException;


public class CircularArrayQueue<E> implements Queue<E> {
    private static final int CAPACITY = 5;
    final E[] data; // generic array used for storage
    private int front = 0; // index of the front element
    private int size = 0; // current number of elements



    @SuppressWarnings("unchecked")
    public CircularArrayQueue() {
        data = (E[])new Object[CAPACITY];
    }


    public void enqueue(E item) throws FullQueueException {
        if (isFull()) {
            throw new FullQueueException("Queue is full.");
        }
        int avail = (front + size) % CAPACITY;
        data[avail] = item;
        size++;
    }

    public E dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty.");
        }
        E result = data[front];
        data[front] = null; // dereference for garbage collection
        front = (front + 1) % CAPACITY;
        size--;
        return result;
    }

    public E front() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty.");
        }
        return data[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == CAPACITY;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue ").append(": [");
        for (int i = 0; i < size; i++) {
            sb.append(data[(front + i) % CAPACITY]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

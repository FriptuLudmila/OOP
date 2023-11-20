package QueueImplement;

import interfaces.Queue;
import exceptions.FullQueueException;
import exceptions.EmptyQueueException;
import java.util.Arrays;

public class DynamicQueue<E> implements Queue<E> {
    private E[] data;
    private int front = 0;
    private int size = 0;
    private int capacity;
    private static final int MAX_CAPACITY = 5;

    @SuppressWarnings("unchecked")
    public DynamicQueue() {
        capacity = 2;
        data = (E[]) new Object[capacity];
    }

    public void enqueue(E item) throws FullQueueException {
        if (size == capacity) {
            if (capacity == MAX_CAPACITY) {
                throw new FullQueueException("Queue is at max capacity.");
            }
            resize(Math.min(capacity * 2, MAX_CAPACITY));
        }
        int rear = (front + size) % capacity;
        data[rear] = item;
        size++;
    }

    public E dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty.");
        }
        E item = data[front];
        data[front] = null; // Help with garbage collection
        front = (front + 1) % capacity;
        size--;
        return item;
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
        return size == capacity && capacity == MAX_CAPACITY;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % capacity];
        }
        data = newData;
        front = 0;
        capacity = newCapacity;
    }

    @Override
    public String toString() {
        // If your queue should only show the valid elements
        Object[] validData = new Object[size];
        for (int i = 0; i < size; i++) {
            validData[i] = data[(front + i) % capacity];
        }
        return "DynamicQueue: " + Arrays.toString(validData);


    }
}




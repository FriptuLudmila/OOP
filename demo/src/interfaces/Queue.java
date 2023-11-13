package interfaces;

import exceptions.EmptyQueueException;
import exceptions.FullQueueException;

public interface Queue<E> {
    void enqueue(E item) throws FullQueueException;
    E dequeue() throws EmptyQueueException;
    E front() throws EmptyQueueException;
    boolean isEmpty();
    boolean isFull();
}


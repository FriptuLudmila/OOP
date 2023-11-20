package StackImplement;

import exceptions.FullStackException;
import exceptions.EmptyStackException;
import interfaces.Stack;

public class ResizableArrayStack<E> implements Stack<E> {
    private E[] data;
    private int top = -1;
    private int capacity;
    private static final int MAX_CAPACITY = 5;

    @SuppressWarnings("unchecked")
    public ResizableArrayStack() {
        capacity = 2; // Initial capacity
        data = (E[]) new Object[capacity];
    }

    public void push(E item) throws FullStackException {
        if (top == capacity - 1) {
            if (capacity < MAX_CAPACITY) {
                resize(Math.min(capacity * 2, MAX_CAPACITY));
            } else {
                throw new FullStackException("Stack has reached its maximum capacity.");
            }
        }
        data[++top] = item;
    }

    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty.");
        }
        E item = data[top];
        data[top] = null; // help with garbage collection
        top--;
        return item;
    }

    public E peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty.");
        }
        return data[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1 && capacity == MAX_CAPACITY;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, top + 1);
        data = newData;
        capacity = newCapacity;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" Resizable Stack ").append(": [");
        for (int i = 0; i <= top; i++) {
            sb.append(data[i]);
            if (i < top) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}

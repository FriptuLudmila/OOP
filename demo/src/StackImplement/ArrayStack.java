package StackImplement;

import exceptions.FullStackException;
import exceptions.EmptyStackException;
import interfaces.Stack;

public class ArrayStack<E> implements Stack<E> {
    public E[] data;
    public int top = -1; // index of the top element


    @SuppressWarnings("unchecked")
    public ArrayStack(int initialCapacity) {
        data = (E[]) new Object[initialCapacity];
    }


    public void push(E item) throws FullStackException {
        if (isFull()) {
            throw new FullStackException("Stack is full.");
        }
        data[++top] = item;
    }


    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty.");
        }
        E answer = data[top];
        data[top] = null; // Dereference for garbage collection
        top--;
        if (top > 0 && top == data.length / 4) {
            resize(data.length / 2);
        }
        return answer;
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
        return top == data.length - 1;
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity];
        System.arraycopy(data, 0, temp, 0, top + 1);
        data = temp;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack ").append(": [");
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

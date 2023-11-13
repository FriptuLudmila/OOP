package StackImplement;

import exceptions.FullStackException;

public class BottomStack<E> extends ArrayStack<E> {

    public BottomStack(int initialCapacity) {
        super(initialCapacity);
    }

    public void pushToBottom(E item) throws FullStackException {
        if (isFull()) {
            throw new FullStackException("Stack is full.");
        }

        // Shift all elements up by one position if the stack is not empty
        for (int i = top; i >= 0; i--) {
            data[i + 1] = data[i];
        }

        // Insert the new item at the bottom
        data[0] = item;
        top++;
    }
}


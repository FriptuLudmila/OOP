
package interfaces;

import exceptions.FullStackException;
import exceptions.EmptyStackException;
public interface Stack<E> {
    void push(E item) throws FullStackException;
    E pop() throws EmptyStackException;
    E peek() throws EmptyStackException;
    boolean isEmpty();
    boolean isFull();
}

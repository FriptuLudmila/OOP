package QueueImplement;

import exceptions.EmptyQueueException;
import interfaces.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;


public  class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private List<E> heap;

    public PriorityQueue() {
        heap = new ArrayList<>();
    }

    @Override
    public void enqueue(E item) {
        heap.add(item);
        bubbleUp(heap.size() - 1);
    }

    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            E currentItem = heap.get(index);
            E parentItem = heap.get(parentIndex);

            if (currentItem.compareTo(parentItem) >= 0) {
                break;
            }
            // Swap
            heap.set(index, parentItem);
            heap.set(parentIndex, currentItem);

            index = parentIndex;
        }
    }

    @Override
    public E dequeue() {
        // Implementation for removing the item with the highest priority
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Priority Queue is empty.");
        }

        E removedItem = heap.get(0);
        E lastItem = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastItem);
            sinkDown(0);
        }

        return removedItem;
    }

    @Override
    public E front() throws EmptyQueueException {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    private void sinkDown(int index) {
        int leftChildIndex, rightChildIndex, smallestChildIndex, heapSize = heap.size();
        E currentItem, leftChild, rightChild, smallestChild;

        while (true) {
            leftChildIndex = 2 * index + 1;
            rightChildIndex = 2 * index + 2;
            smallestChildIndex = index;

            if (leftChildIndex < heapSize) {
                currentItem = heap.get(index);
                leftChild = heap.get(leftChildIndex);

                if (leftChild.compareTo(currentItem) < 0) {
                    smallestChildIndex = leftChildIndex;
                }
            }

            if (rightChildIndex < heapSize) {
                currentItem = heap.get(smallestChildIndex);
                rightChild = heap.get(rightChildIndex);

                if (rightChild.compareTo(currentItem) < 0) {
                    smallestChildIndex = rightChildIndex;
                }
            }

            if (smallestChildIndex != index) {
                // Swap
                smallestChild = heap.get(smallestChildIndex);
                heap.set(smallestChildIndex, heap.get(index));
                heap.set(index, smallestChild);

                index = smallestChildIndex;
            } else {
                break;
            }
        }
    }
    public E peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Priority Queue is empty.");
        }
        return heap.get(0);
    }
    @Override
    public String toString() {
        return "PriorityQueue: " + heap.toString();
    }


    // Other methods defined in Queue interface
}


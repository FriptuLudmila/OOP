import java.util.Scanner;
import java.util.NoSuchElementException;


import StackImplement.BottomStack;
import StackImplement.ResizableArrayStack;
import exceptions.FullStackException;
import exceptions.EmptyStackException;
import exceptions.FullQueueException;
import exceptions.EmptyQueueException;
import QueueImplement.CircularArrayQueue;
import QueueImplement.PriorityQueue;
import QueueImplement.DynamicQueue;


public class StackQueueDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(); // Add priority queue
        BottomStack<String> stack = new BottomStack<>(5);
        CircularArrayQueue<String> queue = new CircularArrayQueue<>();
        DynamicQueue<String> dynamicQueue = new DynamicQueue<>(); // Add dynamic queue
        ResizableArrayStack<String> resizableStack = new ResizableArrayStack<>();


        String input;
        boolean quit = false;

        while (!quit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Work with Stack");
            System.out.println("2. Work with Queue");
            System.out.println("0. Quit");
            input = scanner.nextLine();

            switch (input) {
                case "1" -> stackOperations(scanner, stack, resizableStack);
                case "2" -> queueOperations(scanner, queue, priorityQueue, dynamicQueue);
                case "0" -> quit = true;
                default -> System.out.println("Invalid option! Please enter a number between 0 and 2.");
            }
        }

        scanner.close();
    }

    private static void stackOperations(Scanner scanner, BottomStack<String> stack, ResizableArrayStack<String> resizableStack) {
        // Stack operations here
        boolean back = false;
        while (!back) {
            System.out.println("\nStack Operations:");
            System.out.println("1. Add element to stack");
            System.out.println("2. Pop from stack");
            System.out.println("3. Peek at stack");
            System.out.println("4. Push to the bottom of the stack");
            System.out.println("5. View stack");
            System.out.println("6. Push to resizable stack");
            System.out.println("7. Pop from resizable stack");
            System.out.println("8. Peek at resizable stack");
            System.out.println("9. Check if resizable stack is full");
            System.out.println("10.  View resizable stack contents");

            System.out.println("0. Back to Main Menu");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.println("Enter a string to push onto the stack:");
                    String itemToPush = scanner.nextLine();
                    try {
                        stack.push(itemToPush);
                        System.out.println(itemToPush + " pushed onto the stack.");
                    } catch (FullStackException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "2" -> {
                    try {
                        System.out.println("Popped from stack: " + stack.pop());
                    } catch (EmptyStackException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "3" -> {
                    try {
                        System.out.println("Top of stack: " + stack.peek());
                    } catch (EmptyStackException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "4" -> {
                    System.out.println("Enter a string to push to the bottom of the stack:");
                    String itemToPushBottom = scanner.nextLine();
                    try {
                        stack.pushToBottom(itemToPushBottom);
                        System.out.println(itemToPushBottom + " pushed to the bottom of the stack.");
                    } catch (FullStackException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "5" -> {
                    System.out.println("Current stack contents: ");
                    System.out.println(stack);
                }
                case "6" -> {
                    System.out.println("Enter a string to push onto the resizable stack:");
                    String item = scanner.nextLine();
                    try {
                        resizableStack.push(item);
                        System.out.println(item + " pushed onto the stack.");
                    } catch (FullStackException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "7" -> {
                    try {
                        System.out.println("Popped from stack: " + resizableStack.pop());
                    } catch (EmptyStackException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "8" -> {
                    try {
                        System.out.println("Top of stack: " + resizableStack.peek());
                    } catch (EmptyStackException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "9" -> System.out.println("Resizable stack is " + (resizableStack.isFull() ? "full." : "not full."));
                case "10" -> {
                    System.out.println("Current resizable stack contents: ");
                    System.out.println(resizableStack);
                }
                case "0" -> back = true;
                // ... other cases ...
                default -> System.out.println("Invalid option! Please enter a number.");
            }
        }
    }

    private static void queueOperations(Scanner scanner, CircularArrayQueue<String> queue, PriorityQueue<String> priorityQueue, DynamicQueue<String> dynamicQueue) {
        // Queue operations here
        boolean back = false;
        while (!back) {
            System.out.println("\nQueue Operations:");
            System.out.println("1. Add element to queue");
            System.out.println("2. Dequeue from queue");
            System.out.println("3. Peek at queue");
            System.out.println("4. View queue");
            System.out.println("5. Add element to priority queue");
            System.out.println("6. Remove highest priority element from priority queue");
            System.out.println("7. Peek at priority queue");
            System.out.println("8. View priority queue elements");
            System.out.println("9. Enqueue in dynamic queue");
            System.out.println("10. Dequeue from dynamic queue");
            System.out.println("11. Peek at dynamic queue");
            System.out.println("12. Check if dynamic queue is empty");
            System.out.println("13. Check if dynamic queue is full");
            System.out.println("14. View dynamic queue elements");
            System.out.println("0. Back to Main Menu");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.println("Enter a string to enqueue into the queue:");
                    String itemToEnqueue = scanner.nextLine();
                    try {
                        queue.enqueue(itemToEnqueue);
                        System.out.println(itemToEnqueue + " enqueued into the queue.");
                    } catch (FullQueueException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "2" -> {
                    try {
                        System.out.println("Dequeued from queue: " + queue.dequeue());
                    } catch (EmptyQueueException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "3" -> {
                    try {
                        System.out.println("Front of queue: " + queue.front());
                    } catch (EmptyQueueException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "4" -> {
                    System.out.println("Current queue contents: ");
                    System.out.println(queue);
                }
                case "5" -> {
                    System.out.println("Enter a string to add to the priority queue:");
                    String item = scanner.nextLine();
                    priorityQueue.enqueue(item);
                    System.out.println(item + " added to the priority queue.");
                }
                case "6" -> {
                    try {
                        System.out.println("Removed from priority queue: " + priorityQueue.dequeue());
                    } catch (NoSuchElementException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "7" -> {
                    try {
                        System.out.println("Top of priority queue: " + priorityQueue.peek());
                    } catch (NoSuchElementException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "8" -> {
                    System.out.println("Current priority queue contents: ");
                    System.out.println(priorityQueue);
                }
                case "9" -> {
                    System.out.println("Enter a string to enqueue into the dynamic queue:");
                    String item = scanner.nextLine();
                    try {
                        dynamicQueue.enqueue(item);
                        System.out.println(item + " enqueued into the dynamic queue.");
                    } catch (FullQueueException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "10" -> {
                    try {
                        System.out.println("Dequeued from dynamic queue: " + dynamicQueue.dequeue());
                    } catch (EmptyQueueException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "11" -> {
                    try {
                        System.out.println("Front of dynamic queue: " + dynamicQueue.front());
                    } catch (EmptyQueueException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "12" -> System.out.println("Dynamic queue is " + (dynamicQueue.isEmpty() ? "empty." : "not empty."));

                case "13" -> System.out.println("Dynamic queue is " + (dynamicQueue.isFull() ? "full." : "not full."));

                case "14" -> {
                    System.out.println("Current dynamic queue contents: ");
                    System.out.println(dynamicQueue);
                }

                case "0" -> back = true;
                // ... other cases ...
                default -> System.out.println("Invalid option! Please enter a number.");
            }
        }
        scanner.close();
    }
}





























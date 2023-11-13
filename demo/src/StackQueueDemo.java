import java.util.Scanner;


import StackImplement.BottomStack;
import exceptions.FullQueueException;
import exceptions.EmptyQueueException;
import exceptions.FullStackException;
import exceptions.EmptyStackException;
import QueueImplement.CircularArrayQueue;



public class StackQueueDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BottomStack<String> stack = new BottomStack<>(5); // Use FlexibleStack
        CircularArrayQueue<String> queue = new CircularArrayQueue<>();



        String input;
        boolean quit = false;

        while (!quit) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Add element to stack");
            System.out.println("2. Pop from stack");
            System.out.println("3. Peek at stack");
            System.out.println("4. Add element to queue");
            System.out.println("5. Dequeue from queue");
            System.out.println("6. Peek at queue");
            System.out.println("7. View stack");
            System.out.println("8. View queue");
            System.out.println("9. Push to the bottom of the stack"); // New option
            System.out.println("0. Quit");

            input = scanner.nextLine();

            switch (input) {
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
                    System.out.println("Enter a string to enqueue into the queue:");
                    String itemToEnqueue = scanner.nextLine();
                    try {
                        queue.enqueue(itemToEnqueue);
                        System.out.println(itemToEnqueue + " enqueued into the queue.");
                    } catch (FullQueueException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "5" -> {
                    try {
                        System.out.println("Dequeued from queue: " + queue.dequeue());
                    } catch (EmptyQueueException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "6" -> {
                    try {
                        System.out.println("Front of queue: " + queue.front());
                    } catch (EmptyQueueException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "7" -> {
                    System.out.println("Current stack contents: ");
                    System.out.println(stack.toString());
                }
                case "8" -> {
                    System.out.println("Current queue contents: ");
                    System.out.println(queue.toString());
                }
                case "9" -> {
                    System.out.println("Enter a string to push to the bottom of the stack:");
                    String itemToPushBottom = scanner.nextLine();
                    try {
                        stack.pushToBottom(itemToPushBottom);
                        System.out.println(itemToPushBottom + " pushed to the bottom of the stack.");
                    } catch (FullStackException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "0" -> {
                    quit = true;
                    System.out.println("Exiting program.");
                }
                default -> System.out.println("Invalid option! Please enter a number between 0 and 6.");
            }
        }

        scanner.close();
    }
}

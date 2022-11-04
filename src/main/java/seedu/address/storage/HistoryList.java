package seedu.address.storage;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Manages storage of previous commands in a linked list.
 */
public class HistoryList {
    private static Queue<String> history = new LinkedList<>();

    public static Queue<String> getList() {
        return history;
    }

    public static boolean isMax() {
        return history.size() > 4;
    }

    public static boolean isEmpty() {
        return history.isEmpty();
    }

    /**
     * Adds {@code command} to the history list.
     */
    public static void addToHistory(String command) {
        if (isMax()) {
            history.remove();
        }
        history.add(command);
    }

    private static Queue<String> reverseQueue() {
        Stack<String> stack = new Stack<>();
        Queue<String> tempQ = new LinkedList<>(history);
        while (!tempQ.isEmpty()) {
            stack.add(tempQ.peek());
            tempQ.remove();
        }
        while (!stack.isEmpty()) {
            tempQ.add(stack.peek());
            stack.pop();
        }
        return tempQ;
    }

    /**
     * Prints out the contents of the list.
     */
    public static String printList() {
        int index = 1;
        String toBePrinted = "";
        Queue<String> tempHistory = reverseQueue();
        for (String s : tempHistory) {
            toBePrinted = toBePrinted + index + ": " + s.toString() + "\n";
            index += 1;
        }
        return toBePrinted;
    }
}

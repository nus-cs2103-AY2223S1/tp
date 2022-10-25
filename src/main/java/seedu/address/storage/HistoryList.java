package seedu.address.storage;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Manages storage of previous commands in a linked list.
 */
public class HistoryList {
    public static Queue<String> history = new LinkedList<>();

    public static boolean isMax() {
        return history.size() > 4;
    }

    public static boolean isEmpty() {
        return history.isEmpty();
    }

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

    public static void clearList() {
        while (!history.isEmpty()) {
            history.remove();
        }
    }

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

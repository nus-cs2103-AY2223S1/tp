package seedu.rc4hdb.ui.history;

import java.util.ArrayDeque;
import java.util.Deque;

import javafx.scene.input.KeyCode;

/**
 * The component that is responsible for logging and keeping track of user command inputs.
 * There is no limitation to the number of commands it can keep track. The stack is cleared upon
 * exiting the system.
 */
public class CommandHistory {
    private static final String EMPTY_TEXT = "";

    private Deque<String> forwardHistory = new ArrayDeque<String>();
    private Deque<String> backwardHistory = new ArrayDeque<String>();

    /**
     * Adds the most recently executed command to the forwardHistory stack.
     *
     * @param commandText both valid and invalid commands are valid texts.
     */
    public void add(String commandText) {
        assert(commandText != null);
        forwardHistory.add(commandText);
    }

    /**
     * Logic handler to parse user key press input. Input can only be DOWN or UP.
     *
     * @return String of the object that is being transferred.
     */
    public String handle(KeyCode event) {
        String commandText = EMPTY_TEXT;
        if (event == KeyCode.DOWN) {
            commandText = traverseBackward();
        }
        if (event == KeyCode.UP) {
            commandText = traverseForward();
        }
        return commandText;
    }

    /**
     * Handles the case where the user inputs a UP arrow key press.
     *
     * @return String of the object that is being transferred.
     */
    private String traverseForward() {
        if (forwardHistory.isEmpty()) {
            return EMPTY_TEXT;
        }
        if (isLastCommand(forwardHistory)) {
            return forwardHistory.getLast();
        }
        return transfer(forwardHistory, backwardHistory);
    }

    /**
     * Handles the case where the user inputs a DOWN arrow key press.
     *
     * @return String of the object that is being transferred.
     */
    private String traverseBackward() {
        if (backwardHistory.isEmpty()) {
            return EMPTY_TEXT;
        }
        return transfer(backwardHistory, forwardHistory);
    }

    /**
     * Pops the last object from the from stack, and pushes it into the to stack.
     *
     * @param from stack where the object is taken from.
     * @param to stack where the object is inserted in.
     * @return String of the object that is being transferred.
     */
    private String transfer(Deque<String> from, Deque<String> to) {
        String mostRecentCommand = from.removeLast();
        to.add(mostRecentCommand);
        return mostRecentCommand;
    }

    /**
     * Checks if there is only one String object left in stack.
     *
     * @param stack where traverse is acting on,
     * @return true if there is only 1 object left in stack.
     */
    private boolean isLastCommand(Deque<String> stack) {
        return stack.size() == 1;
    }

}

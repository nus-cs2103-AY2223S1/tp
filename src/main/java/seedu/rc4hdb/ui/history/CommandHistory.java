package seedu.rc4hdb.ui.history;

// only one commandHistory is allowed per instance of the application
// use singleton

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayDeque;
import java.util.Deque;
import static java.util.Objects.requireNonNull;

public class CommandHistory {
    private final String EMPTY_COMMAND_TEXT = "";

    private Deque<String> forwardHistory = new ArrayDeque<String>();
    private Deque<String> backwardHistory = new ArrayDeque<String>();

    public void add(String commandText) {
        forwardHistory.add(commandText);
    }

    public String handle(KeyCode event) {
        String commandText = EMPTY_COMMAND_TEXT;
        if (event == KeyCode.DOWN) {
            commandText = traverseBackward();
        }
        if (event == KeyCode.UP) {
            commandText = traverseForward();
        }
        return commandText;
    }

    private String traverseForward() {
        if (forwardHistory.isEmpty()) {
            return EMPTY_COMMAND_TEXT;
        }
        if (isLastCommand(forwardHistory)) {
            return forwardHistory.getLast();
        }
        return transfer(forwardHistory, backwardHistory);
    }

    private String traverseBackward() {
        if (backwardHistory.isEmpty()) {
            return EMPTY_COMMAND_TEXT;
        }
        return transfer(backwardHistory, forwardHistory);
    }

    private String transfer(Deque<String> from, Deque<String> to) {
        String mostRecentCommand = from.removeLast();
        to.add(mostRecentCommand);
        return mostRecentCommand;
    }

    private boolean isLastCommand(Deque<String> stack) {
        if (stack.size() == 1) {
            return true;
        }
        return false;
    }


}

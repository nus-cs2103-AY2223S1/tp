package seedu.rc4hdb.ui.history;

// only one commandHistory is allowed per instance of the application
// use singleton

import java.util.ArrayDeque;
import java.util.Deque;
import static java.util.Objects.requireNonNull;

public class CommandHistory {
    private Deque<String> forwardHistory = new ArrayDeque<String>();
    private Deque<String> backwardHistory = new ArrayDeque<String>();

    public void add(String commandText) {
        forwardHistory.add(commandText);
    }

    private String transfer(Deque<String> from, Deque<String> to) {
        String mostRecentCommand = from.removeLast();
        to.add(mostRecentCommand);
        return mostRecentCommand;
    }

    public String traverseForward() {
        if (forwardHistory.isEmpty()) {
            return "";
        }
        if (isLastCommand(forwardHistory)) {
            return forwardHistory.getLast();
        }
        return transfer(forwardHistory, backwardHistory);
    }

    public String traverseBackward() {
        if (backwardHistory.isEmpty()) {
            return "";
        }
        if (isLastCommand(backwardHistory)) {
            return backwardHistory.getLast();
        }
        return transfer(backwardHistory, forwardHistory);
    }

    public boolean isLastCommand(Deque<String> stack) {
        if (stack.size() == 1) {
            return true;
        }
        return false;
    }


}

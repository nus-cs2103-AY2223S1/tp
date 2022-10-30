package seedu.rc4hdb.ui.history;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * The component responsible for maintaining the order of the command history.
 */
public abstract class CommandHistory {
    protected static Deque<String> forwardStack = new ArrayDeque<String>();
    protected static Deque<String> backwardStack = new ArrayDeque<String>();
    protected static final String EMPTY_TEXT = "";

    /**
     * Transfer an element from the first parameter to the second.
     * @param from the stack where the element is to be popped from.
     * @param to the stack where the element is ot be pushed to.
     * @return the element that is being transferred.
     */
    protected String transfer(Deque<String> from, Deque<String> to) {
        String mostRecentCommand = from.removeLast();
        to.add(mostRecentCommand);
        return mostRecentCommand;
    }

    public abstract String execute();

}

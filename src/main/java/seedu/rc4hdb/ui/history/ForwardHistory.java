package seedu.rc4hdb.ui.history;

import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The component responsible for executing UP arrow key press in viewing recent commands.
 */
public class ForwardHistory extends CommandHistory {

    private static Logger logger = Logger.getLogger("CommandHistoryParser");

    public ForwardHistory() {
        super();
    }

    /**
     * Adds the commandText to the stack.
     * @param commandText
     */
    public void add(String commandText) {
        assert(commandText != null);
        this.forwardStack.add(commandText);
    }

    /**
     * Handles the case where the user inputs a UP arrow key press.
     *
     * @return String of the object that is being transferred.
     */
    @Override
    public String execute() {
        logger.log(Level.INFO, "Moving up the command history stack.");
        if (this.forwardStack.isEmpty()) {
            return EMPTY_TEXT;
        }
        if (isLastCommand(forwardStack)) {
            logger.log(Level.INFO, "No more previous commands in stack.");
            return forwardStack.getLast();
        }
        logger.log(Level.INFO, "Transferring from forward stack to backward stack.");
        return transfer(this.forwardStack, this.backwardStack);
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

package seedu.rc4hdb.ui.history;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The component responsible for executing DOWN arrow key press in viewing recent commands.
 */
public class BackwardHistory extends CommandHistory {

    private static Logger logger = Logger.getLogger("CommandHistoryParser");

    public BackwardHistory() {
        super();
    }

    /**
     * Handles the case where the user inputs a DOWN arrow key press.
     *
     * @return String of the object that is being transferred.
     */
    @Override
    public String execute() {
        logger.log(Level.INFO, "Moving down the command history stack.");
        if (this.backwardStack.isEmpty()) {
            logger.log(Level.INFO, "Reached the end of the stack.");
            return EMPTY_TEXT;
        }
        logger.log(Level.INFO, "Transferring from backward stack to forward stack.");
        return this.transfer(this.backwardStack, this.forwardStack);
    }


}

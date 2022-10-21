package seedu.rc4hdb.ui.history;

/**
 * The component responsible for executing DOWN arrow key press in viewing recent commands.
 */
public class BackwardHistory extends CommandHistory {

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
        if (this.backwardStack.isEmpty()) {
            return EMPTY_TEXT;
        }
        return this.transfer(this.backwardStack, this.forwardStack);
    }


}

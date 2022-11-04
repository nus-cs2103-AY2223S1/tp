package seedu.address.logic.commands.checkcommands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;


/**
 * Checks a selected Buyer/Supplier/Deliverer/Order/Pet.
 */
public abstract class CheckCommand extends Command {

    public static final String COMMAND_WORD = "check";

    public static final String MESSAGE_USAGE = "Check the buyer/supplier of a specific order/pet with.\n"
            + "Example: check order 1";

    protected final Index index;

    /**
     * Constructs a CheckCommand with Index.
     */
    public CheckCommand(Index index) {
        this.index = index;
    }

}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.BuyerBook;
import seedu.address.model.Model;
import seedu.address.model.PropertyBook;

/**
 * Clears both buyer and property list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Properties and Buyers list have been cleared";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setBuyerBook(new BuyerBook());
        model.setPropertyBook(new PropertyBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

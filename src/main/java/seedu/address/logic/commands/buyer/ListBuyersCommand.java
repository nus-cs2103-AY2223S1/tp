package seedu.address.logic.commands.buyer;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BUYERS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all buyers in the buyer book to the user.
 */
public class ListBuyersCommand extends Command {

    public static final String COMMAND_WORD = "listbuyers";

    public static final String MESSAGE_SUCCESS = "Listed all buyers";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBuyerList(PREDICATE_SHOW_ALL_BUYERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

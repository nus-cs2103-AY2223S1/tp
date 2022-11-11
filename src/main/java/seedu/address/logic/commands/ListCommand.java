package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TEAMMATES;

import seedu.address.model.Model;

/**
 * Lists all teammates in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all teammates";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTeammateList(PREDICATE_SHOW_ALL_TEAMMATES);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NOT_AT_HOMEPAGE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all existing people in Plannit to the user.
 */
public class ListPersonCommand extends Command {

    public static final String COMMAND_WORD = "list-person";

    public static final String MESSAGE_SUCCESS = "Listed all people";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        boolean isNotAtHome = !model.getHomeStatus();

        if (isNotAtHome) {
            throw new CommandException(MESSAGE_NOT_AT_HOMEPAGE);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

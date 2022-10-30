package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all existing persons in Plannit to the user.
 */
public class ListPersonCommand extends Command {

    public static final String COMMAND_WORD = "list-person";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        boolean isNotAtHome = !model.getHomeStatusAsBoolean();

        if (isNotAtHome) {
            throw new CommandException(Messages.MESSAGE_NOT_AT_HOMEPAGE);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

package seedu.codeconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.codeconnect.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.codeconnect.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListContactCommand extends Command {

    public static final String COMMAND_WORD = "listc";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

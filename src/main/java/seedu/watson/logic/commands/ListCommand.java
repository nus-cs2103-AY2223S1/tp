package seedu.watson.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.watson.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.watson.model.Model;

/**
 * Lists all persons in the watson book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

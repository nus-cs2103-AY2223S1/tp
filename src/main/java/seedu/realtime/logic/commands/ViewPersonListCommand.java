package seedu.realtime.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.realtime.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.realtime.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewPersonListCommand extends Command {

    public static final String COMMAND_WORD = "listP";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

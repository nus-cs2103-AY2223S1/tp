package swift.logic.commands;

import static java.util.Objects.requireNonNull;
import static swift.model.Model.PREDICATE_HIDE_ALL_TASKS;
import static swift.model.Model.PREDICATE_SHOW_ALL_PEOPLE;

import swift.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListContactCommand extends Command {

    public static final String COMMAND_WORD = "list_contact";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_HIDE_ALL_TASKS);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PEOPLE);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false, false);
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.address.commons.FilterInfo;
import seedu.address.model.Model;

/**
 * Lists all tasks in the address book to the user.
 */
public class ListTaskCommand extends Command {

    public static final String COMMAND_WORD = "listTasks";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS, new FilterInfo());
    }
}

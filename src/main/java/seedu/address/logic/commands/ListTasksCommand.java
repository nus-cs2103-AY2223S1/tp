package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.address.model.Model;

/**
 * Lists all tasks in the task list to the user.
 */
public class ListTasksCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";

    public static final String EMPTY_LIST = "No task in list";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        if (model.getFilteredTaskList().size() == 0) {
            return new CommandResult(EMPTY_LIST);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

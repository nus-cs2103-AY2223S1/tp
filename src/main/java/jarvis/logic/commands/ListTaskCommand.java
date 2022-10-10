package jarvis.logic.commands;

import static jarvis.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static java.util.Objects.requireNonNull;

import jarvis.model.Model;

/**
 * Lists all tasks in the task book to the user.
 */
public class ListTaskCommand extends Command {

    public static final String COMMAND_WORD = "listtask";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, true);
    }
}

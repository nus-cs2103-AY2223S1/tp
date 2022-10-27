package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.task.Task.PREDICATE_SHOW_ARCHIVED_TASKS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all archived tasks in the task list.
 */
public class ListArchivedTaskCommand extends Command {

    public static final String COMMAND_WORD = "listAT";

    public static final String MESSAGE_SUCCESS = "Listed all archived tasks \n"
            + "To view non-archived tasks, use the command: listT (mnemonic: list (T)asks)";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_ARCHIVED_TASKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

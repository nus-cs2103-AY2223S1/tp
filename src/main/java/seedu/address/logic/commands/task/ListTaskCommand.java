package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.task.Task.PREDICATE_SHOW_NON_ARCHIVED_TASKS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all (non-archived) tasks in the task list.
 */
public class ListTaskCommand extends Command {

    public static final String COMMAND_WORD = "listT";

    public static final String MESSAGE_SUCCESS = "Listed all (non-archived) tasks \n"
            + "To view archived tasks, use the command: listAT (mnemonic: list (A)rchived (T)asks)";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_NON_ARCHIVED_TASKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

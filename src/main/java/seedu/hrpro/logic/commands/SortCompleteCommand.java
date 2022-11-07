package seedu.hrpro.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.hrpro.logic.commands.exceptions.CommandException;
import seedu.hrpro.model.Model;

/**
 * Sorts the tasks in HR Pro Max++ by completion status.
 */
public class SortCompleteCommand extends Command {
    public static final String COMMAND_WORD = "sortcomplete";

    public static final String MESSAGE_FILTER_TASK_SUCCESS =
            "Task has been filtered to display uncompleted tasks at the top!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortComplete();
        return new CommandResult(MESSAGE_FILTER_TASK_SUCCESS);
    }
}

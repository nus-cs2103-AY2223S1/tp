package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Sorts the task list by deadline.
 */
public class SortByDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "sortD";

    public static final String MESSAGE_SUCCESS = "Sorted all tasks by deadline";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortByDeadline();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

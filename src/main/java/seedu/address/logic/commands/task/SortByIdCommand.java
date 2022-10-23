package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Sorts the task list by id.
 */
public class SortByIdCommand extends Command {

    public static final String COMMAND_WORD = "sortI";

    public static final String MESSAGE_SUCCESS = "Sorted all tasks by id";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortById();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

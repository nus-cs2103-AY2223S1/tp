package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Sorts the tasks by deadline.
 */
public class SortTaskCommand extends Command {
    public static final String COMMAND_WORD = "sorttask";
    public static final String MESSAGE_SUCCESS = "Tasks are now sorted!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortTasks();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

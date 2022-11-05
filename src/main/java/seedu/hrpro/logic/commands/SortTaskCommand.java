package seedu.hrpro.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.hrpro.logic.commands.exceptions.CommandException;
import seedu.hrpro.model.Model;

/**
 * Sorts the tasks in HR Pro Max++ by deadline.
 */
public class SortTaskCommand extends Command {
    public static final String COMMAND_WORD = "sorttask";
    public static final String MESSAGE_SUCCESS = "Tasks are now sorted by deadline!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortTasks();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

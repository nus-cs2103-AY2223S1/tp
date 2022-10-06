package seedu.address.logic.commands.tasks;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.tasks.TaskCategoryParser;
import seedu.address.model.Model;

/**
 * Deletes a task identified using it's displayed index from the task book.
 */
public class TaskDeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE =
            TaskCategoryParser.CATEGORY_WORD + " " + COMMAND_WORD
            + ": Deletes the task identified by the index number.\n"
            + "Parameters: " + PREFIX_INDEX + "INDEX (must be a positive integer)\n"
            + "Example: " + TaskCategoryParser.CATEGORY_WORD + " "
            + COMMAND_WORD + " " + PREFIX_INDEX + "1";

    private final Index targetIndex;

    public TaskDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException("Task delete command not implemented yet.");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((TaskDeleteCommand) other).targetIndex)); // state check
    }
}

package taskbook.logic.commands.tasks;

import taskbook.commons.core.index.Index;
import taskbook.logic.commands.Command;
import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.logic.parser.CliSyntax;
import taskbook.logic.parser.tasks.TaskCategoryParser;
import taskbook.model.Model;

/**
 * Deletes a task identified using it's displayed index from the task book.
 */
public class TaskDeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE =
            TaskCategoryParser.CATEGORY_WORD + " " + COMMAND_WORD
            + ": Deletes the task identified by the index number.\n"
            + "Parameters: " + CliSyntax.PREFIX_INDEX + "INDEX (must be a positive integer)\n"
            + "Example: " + TaskCategoryParser.CATEGORY_WORD + " "
            + COMMAND_WORD + " " + CliSyntax.PREFIX_INDEX + "1";

    private final Index index;

    public TaskDeleteCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException("Task delete command not implemented yet.");
    }
}

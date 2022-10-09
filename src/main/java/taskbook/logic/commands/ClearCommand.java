package taskbook.logic.commands;

import static java.util.Objects.requireNonNull;

import taskbook.model.TaskBook;
import taskbook.model.Model;

/**
 * Clears the task book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Task book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTaskBook(new TaskBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

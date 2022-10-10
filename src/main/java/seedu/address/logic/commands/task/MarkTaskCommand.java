package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Marks a task of a person as complete or incomplete.
 */
public class MarkTaskCommand extends TaskCommand{

    public static final String COMMAND_WORD = "mark";

    @Override
    public CommandResult execute(Model model) throws CommandException{
        return new CommandResult("Hello from remark");
    }
}
package seedu.address.logic.commands.logicalcommand;

import java.time.LocalDateTime;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Command to check whether a task is complete
 */
public class CheckTaskCompleteCommand extends Command {

    public static final String COMMAND_WORD = "isComplete";
    private static final String NO_SELECTED = "No task was selected!";
    private static final String INVALID_INPUT = "The input is not of type Task!";

    private Task item = null;

    public CheckTaskCompleteCommand() {}

    @Override
    public Command setInput(Object additionalData) throws CommandException {
        if (additionalData == null || !(additionalData instanceof Task)) {
            throw new CommandException(INVALID_INPUT);
        }
        item = (Task) additionalData;
        return this;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (item == null) {
            throw new CommandException(NO_SELECTED);
        }
        LocalDateTime dt = item.getCompletedTime();
        return new CommandResult(String.format("result is %s", dt != null), false, false, dt != null);
    }

}

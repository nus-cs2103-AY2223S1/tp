package seedu.address.logic.commands.tasks;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.task.Task;

/**
 * Commands for Tasks
 */
public abstract class TaskCommand extends Command {

    public static final String COMMAND_WORD = "task";
    public static final String INVALID_INPUT = "This command cannot take in non task element";
    protected Task task = null;

    /**
     * Returns the complete command phrase for the task command with given subCommand
     *
     * @param subcommand The subcommand to be added
     * @return The complete command phrase
     */
    public static String getFullCommand(String subcommand) {
        return "task " + subcommand;
    }

    @Override
    public Command setInput(Object additionalData) throws CommandException {
        if (additionalData == null || !(additionalData instanceof Task)) {
            task = null;
            return this;
        }

        task = (Task) additionalData;
        return this;
    }
}

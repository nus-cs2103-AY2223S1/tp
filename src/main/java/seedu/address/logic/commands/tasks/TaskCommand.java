package seedu.address.logic.commands.tasks;

import seedu.address.logic.commands.Command;

/**
 * Commands for Tasks
 */
public abstract class TaskCommand extends Command {

    public static final String COMMAND_WORD = "task";

    /**
     * Returns the complete command phrase for the task command with given subCommand
     *
     * @param subcommand The subcommand to be added
     * @return The complete command phrase
     */
    static String getFullCommand(String subcommand) {
        return "task " + subcommand;
    }
}

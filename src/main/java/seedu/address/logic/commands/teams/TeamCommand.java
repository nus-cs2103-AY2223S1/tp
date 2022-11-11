// @@author connlim
package seedu.address.logic.commands.teams;

import seedu.address.logic.commands.Command;

/**
 * Commands for Tasks
 */
public abstract class TeamCommand extends Command {

    public static final String COMMAND_WORD = "team";

    /**
     * Returns the complete command phrase for the task command with given subCommand
     *
     * @param subcommand The subcommand to be added
     * @return The complete command phrase
     */
    public static String getFullCommand(String subcommand) {
        return COMMAND_WORD + " " + subcommand;
    }
}

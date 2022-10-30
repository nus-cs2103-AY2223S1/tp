package seedu.rc4hdb.logic.commands.misccommands;

import seedu.rc4hdb.logic.commands.Command;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;

/**
 * Represents a command that does not directly interact with any components.
 */
public interface MiscCommand extends Command {

    /**
     * Executes the command and returns the result message.
     *
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    CommandResult execute() throws CommandException;

}

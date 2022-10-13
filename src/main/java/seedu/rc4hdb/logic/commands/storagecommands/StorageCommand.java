package seedu.rc4hdb.logic.commands.storagecommands;

import seedu.rc4hdb.logic.commands.Command;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.storage.Storage;

/**
 * Represents a command that interacts with a {@code Storage}.
 */
public interface StorageCommand extends Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param storage {@code Storage} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Storage storage) throws CommandException;

}

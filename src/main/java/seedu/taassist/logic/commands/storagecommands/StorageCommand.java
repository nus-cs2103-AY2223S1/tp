package seedu.taassist.logic.commands.storagecommands;

import seedu.taassist.logic.commands.CommandResult;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.storage.Storage;

/**
 * Represents a storage command with hidden internal logic and the ability to be executed.
 */
public abstract class StorageCommand {

    /**
     * Executes the storage command and returns the result message.
     *
     * @param storage {@code Storage} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Storage storage) throws CommandException;
}

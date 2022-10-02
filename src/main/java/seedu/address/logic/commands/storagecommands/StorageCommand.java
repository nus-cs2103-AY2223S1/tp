package seedu.address.logic.commands.storagecommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.storage.Storage;

/**
 * Represents a command that interacts with a {@code Storage}.
 */
public abstract class StorageCommand extends Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param storage {@code Storage} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Storage storage) throws CommandException;

}

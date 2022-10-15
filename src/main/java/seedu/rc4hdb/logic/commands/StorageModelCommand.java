package seedu.rc4hdb.logic.commands;

import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.storage.Storage;

/**
 * Represents a command that interacts with a {@code Storage} and a {@code Model}.
 */
public interface StorageModelCommand extends Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param storage {@code Storage} which the command should operate on.
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Storage storage, Model model) throws CommandException;

}

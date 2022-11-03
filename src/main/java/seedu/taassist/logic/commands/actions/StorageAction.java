package seedu.taassist.logic.commands.actions;

import seedu.taassist.logic.commands.CommandResult;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.storage.Storage;

/**
 * Represents an action with hidden internal logic and the ability to act on the storage.
 */
public interface StorageAction {

    /**
     * Performs the storage action and returns the result message.
     *
     * @param storage {@code Storage} which the action should act on.
     * @return Feedback message of the action result for display.
     * @throws CommandException If an error occurs during action execution.
     */
    CommandResult act(Storage storage) throws CommandException;
}

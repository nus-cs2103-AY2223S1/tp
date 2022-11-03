package seedu.taassist.logic.commands.actions;

import seedu.taassist.logic.commands.exceptions.StorageActionException;
import seedu.taassist.logic.commands.result.StorageActionResult;
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
     * @throws StorageActionException If an error occurs during action execution.
     */
    StorageActionResult act(Storage storage) throws StorageActionException;
}

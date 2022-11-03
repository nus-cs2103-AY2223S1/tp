package seedu.taassist.logic.commands.result;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.taassist.logic.commands.actions.StorageAction;
import seedu.taassist.logic.commands.actions.UiAction;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.logic.commands.exceptions.StorageActionException;
import seedu.taassist.storage.Storage;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    public static final UiAction NO_UI_ACTION = UiAction.UI_NO_ACTION;
    public static final StorageAction NO_STORAGE_ACTION = new NoStorageAction();
    public static final String MESSAGE_NO_STORAGE_ACTION = "There are no storage actions to perform.";

    private final String feedbackToUser;
    private final UiAction uiAction;
    private final StorageAction storageAction;

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}.
     */
    public CommandResult(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        this.feedbackToUser = feedbackToUser;
        uiAction = NO_UI_ACTION;
        storageAction = NO_STORAGE_ACTION;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code UiAction}.
     */
    public CommandResult(String feedbackToUser, UiAction uiAction) {
        requireNonNull(feedbackToUser);
        requireNonNull(uiAction);
        this.feedbackToUser = feedbackToUser;
        this.uiAction = uiAction;
        storageAction = NO_STORAGE_ACTION;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code StorageAction}.
     */
    public CommandResult(String feedbackToUser, StorageAction storageAction) {
        requireNonNull(feedbackToUser);
        requireNonNull(storageAction);
        this.feedbackToUser = feedbackToUser;
        uiAction = NO_UI_ACTION;
        this.storageAction = storageAction;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public UiAction getUiAction() {
        return uiAction;
    }

    public StorageAction getStorageAction() {
        return storageAction;
    }

    public boolean hasUiAction() {
        return !uiAction.equals(NO_UI_ACTION);
    }

    public boolean hasStorageAction() {
        return !storageAction.equals(NO_STORAGE_ACTION);
    }

    /**
     * Performs the {@code StorageAction} on the {@code storage} and returns the result of the action.
     * The {@code StorageAction} must exist.
     */
    public CommandResult performStorageAction(Storage storage) throws CommandException {
        requireNonNull(storage);
        if (!hasStorageAction()) {
            throw new CommandException(MESSAGE_NO_STORAGE_ACTION);
        }
        StorageActionResult storageActionResult = storageAction.act(storage);
        return new CommandResult(storageActionResult.combineFeedback(feedbackToUser));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && uiAction.equals(otherCommandResult.uiAction)
                && storageAction.equals(otherCommandResult.storageAction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, uiAction, storageAction);
    }

    /**
     * Represents a {@code StorageAction} that does nothing.
     */
    private static class NoStorageAction implements StorageAction {

        private static final StorageActionResult NO_STORAGE_ACTION_RESULT = new StorageActionResult("");

        @Override
        public StorageActionResult act(Storage storage) throws StorageActionException {
            return NO_STORAGE_ACTION_RESULT;
        }
    }

}

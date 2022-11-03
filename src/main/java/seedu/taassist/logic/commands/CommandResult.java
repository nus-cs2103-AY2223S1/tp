package seedu.taassist.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.taassist.logic.commands.actions.StorageAction;
import seedu.taassist.logic.commands.actions.UiAction;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.storage.Storage;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;
    private final UiAction uiAction;
    private final StorageAction storageAction;

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}.
     */
    public CommandResult(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        this.feedbackToUser = feedbackToUser;
        uiAction = null;
        storageAction = null;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code UiAction}.
     */
    public CommandResult(String feedbackToUser, UiAction uiAction) {
        requireNonNull(feedbackToUser);
        requireNonNull(uiAction);
        this.feedbackToUser = feedbackToUser;
        this.uiAction = uiAction;
        storageAction = null;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code StorageAction}.
     */
    public CommandResult(String feedbackToUser, StorageAction storageAction) {
        requireNonNull(feedbackToUser);
        requireNonNull(storageAction);
        this.feedbackToUser = feedbackToUser;
        uiAction = null;
        this.storageAction = storageAction;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public UiAction getUiAction() {
        return uiAction;
    }

    public boolean hasUiAction() {
        return !isNull(uiAction);
    }

    public boolean hasStorageAction() {
        return !isNull(storageAction);
    }

    /**
     * Performs the {@code StorageAction} on the {@code storage} and returns the result of the action.
     * The {@code StorageAction} must exist.
     */
    public CommandResult performStorageAction(Storage storage) throws CommandException {
        requireNonNull(storage);
        assert hasUiAction();

        return storageAction.act(storage);
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
        boolean isEqualFeedback = feedbackToUser.equals(otherCommandResult.feedbackToUser);
        boolean isEqualUiAction;
        if (hasUiAction()) {
            isEqualUiAction = uiAction.equals(otherCommandResult.uiAction);
        } else {
            isEqualUiAction = !otherCommandResult.hasUiAction();
        }
        boolean isEqualStorageAction;
        if (hasStorageAction()) {
            isEqualStorageAction = storageAction.equals(otherCommandResult.storageAction);
        } else {
            isEqualStorageAction = !otherCommandResult.hasStorageAction();
        }

        return isEqualFeedback && isEqualUiAction && isEqualStorageAction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, uiAction, storageAction);
    }

}

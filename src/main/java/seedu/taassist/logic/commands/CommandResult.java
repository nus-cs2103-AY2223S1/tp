package seedu.taassist.logic.commands;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.taassist.logic.commands.actions.StorageAction;
import seedu.taassist.logic.commands.actions.UiAction;

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
        return nonNull(uiAction);
    }

    public StorageAction getStorageAction() {
        return storageAction;
    }

    public boolean hasStorageAction() {
        return nonNull(storageAction);
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
                && uiAction == otherCommandResult.uiAction
                && Objects.equals(storageAction, otherCommandResult.storageAction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, uiAction, storageAction);
    }

}

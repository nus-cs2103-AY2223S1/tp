package seedu.taassist.logic.commands.result;

import java.util.Objects;

/**
 * Represents the result of a storage action.
 */
public class StorageActionResult extends CommandResult {
    /**
     * Constructs a {@code StorageActionResult} with the specified {@code feedbackToUser}.
     */
    public StorageActionResult(String feedbackToUser) {
        super(feedbackToUser);
    }

    /**
     * Combines the previous feedback with the feedback from performing the storage action.
     */
    public String combineFeedback(String previousFeedback) {
        return previousFeedback + getFeedbackToUser();
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

        StorageActionResult otherCommandResult = (StorageActionResult) other;
        return getFeedbackToUser().equals(otherCommandResult.getFeedbackToUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFeedbackToUser());
    }
}

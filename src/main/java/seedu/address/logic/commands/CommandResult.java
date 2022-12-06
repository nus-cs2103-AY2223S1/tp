package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** User Guide information should be shown to the user. */
    private final boolean showUserGuide;

    /** The application should exit. */
    private final boolean exit;

    /** Filter transactions. */
    private final boolean isFilterTransactions;

    /** Sort transactions. */
    private final boolean isSortTransactions;

    /** No change in UI. */
    private final boolean hasNoUiChange;

    /**
     * Constructs a {@code CommandResult} with all the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showUserGuide, boolean exit, boolean isFilterTransactions,
                         boolean isSortTransactions, boolean hasNoUiChange) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showUserGuide = showUserGuide;
        this.exit = exit;
        this.isFilterTransactions = isFilterTransactions;
        this.isSortTransactions = isSortTransactions;
        this.hasNoUiChange = hasNoUiChange;
    }

    /**
     * Constructs a {@code CommandResult} with the specified 4 fields.
     */
    public CommandResult(String feedbackToUser, boolean showUserGuide, boolean exit, boolean isFilterTransactions) {
        this(feedbackToUser, showUserGuide, exit, isFilterTransactions, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified 3 fields.
     */
    public CommandResult(String feedbackToUser, boolean showUserGuide, boolean exit) {
        this(feedbackToUser, showUserGuide, exit, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified 2 fields.
     */
    public CommandResult(String feedbackToUser, boolean hasNoUiChange) {
        this(feedbackToUser, false, false, false, false, hasNoUiChange);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowUserGuide() {
        return showUserGuide;
    }

    public boolean isFilteredTransactions() {
        return isFilterTransactions;
    }

    public boolean hasNoUiChange() {
        return hasNoUiChange;
    }

    public boolean isExit() {
        return exit;
    }

    @Override
    public String toString() {
        return feedbackToUser;
    }

    public boolean isSortedTransactions() {
        return isSortTransactions;
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
                && showUserGuide == otherCommandResult.showUserGuide
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showUserGuide, exit);
    }

}

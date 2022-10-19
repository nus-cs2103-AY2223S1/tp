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
    private final boolean filterTransactions;

    /**
     * Constructs a {@code CommandResult} with all the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showUserGuide, boolean exit, boolean filterTransactions) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showUserGuide = showUserGuide;
        this.exit = exit;
        this.filterTransactions = filterTransactions;
    }

    /**
     * Constructs a {@code CommandResult} with the specified 3 fields.
     */
    public CommandResult(String feedbackToUser, boolean showUserGuide, boolean exit) {
        this(feedbackToUser, showUserGuide, exit, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowUserGuide() {
        return showUserGuide;
    }

    public boolean isFilterTransactions() {
        return filterTransactions;
    }

    public boolean isExit() {
        return exit;
    }

    @Override
    public String toString() {
        return feedbackToUser;
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

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.FilterInfo;
import seedu.address.commons.SortInfo;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {
    private final String feedbackToUser;
    private final SortInfo sortInfo;
    private final FilterInfo filterInfo;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, SortInfo sortInfo, FilterInfo filterInfo, boolean showHelp,
                         boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.sortInfo = sortInfo;
        this.filterInfo = filterInfo;
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, null, null, showHelp, exit);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code sortInfo}
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, SortInfo sortInfo) {
        this(feedbackToUser, sortInfo, null, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code filterInfo}
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, FilterInfo filterInfo) {
        this(feedbackToUser, null, filterInfo, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public SortInfo getSortInfo() {
        return sortInfo;
    }

    public FilterInfo getFilterInfo() {
        return filterInfo;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
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
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}

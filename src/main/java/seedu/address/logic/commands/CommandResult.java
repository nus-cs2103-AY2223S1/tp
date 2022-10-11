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

    /** Points of contact and Transaction should be shown to the user on the right panel */
    private final boolean isView;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showUserGuide, boolean exit, boolean isView) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showUserGuide = showUserGuide;
        this.exit = exit;
        this.isView = isView;
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

    public boolean isExit() {
        return exit;
    }

    public boolean isView() {
        return isView;
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
                && exit == otherCommandResult.exit
                && isView == otherCommandResult.isView;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showUserGuide, exit, isView);
    }

}

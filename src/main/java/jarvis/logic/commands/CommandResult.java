package jarvis.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import jarvis.ui.DisplayedList;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The list being displayed in the UI */
    private final DisplayedList displayedList;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields,
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.displayedList = DisplayedList.SAME_LIST_AS_BEFORE;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields,
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, DisplayedList displayedList) {
        this.feedbackToUser = feedbackToUser;
        this.displayedList = displayedList;
        this.showHelp = false;
        this.exit = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public DisplayedList getDisplayedList() {
        return displayedList;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isList() {
        return displayedList != DisplayedList.SAME_LIST_AS_BEFORE;
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

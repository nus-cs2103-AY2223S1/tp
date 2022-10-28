package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;


/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * The application should lock.
     */
    private final boolean lock;

    /**
     * The index of client when viewing portfolio.
     */
    private final int index;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean lock, int index) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.lock = lock;
        this.index = index;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit, false, 0);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, 0);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code index},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, int index) {
        this(feedbackToUser, false, false, false, index);
    }

    public int getIndex() {
        return index;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isLock() {
        return lock;
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
            && exit == otherCommandResult.exit
            && lock == otherCommandResult.lock;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, lock);
    }

    @Override
    public String toString() {
        return feedbackToUser;
    }
}

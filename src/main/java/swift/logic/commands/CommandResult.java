package swift.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean isShowHelp;

    /** The application should exit. */
    private final boolean isExit;

    /** The application should switch to contacts tab. */
    private final boolean isContactCommand;

    /** The application should switch to tasks tab. */
    private final boolean isTaskCommand;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean isShowHelp, boolean isExit, boolean isContactCommand,
                         boolean isTaskCommand) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isShowHelp = isShowHelp;
        this.isExit = isExit;
        this.isContactCommand = isContactCommand;
        this.isTaskCommand = isTaskCommand;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.isShowHelp = false;
        this.isExit = false;
        this.isContactCommand = false;
        this.isTaskCommand = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean isContactCommand, boolean isTaskCommand) {
        this.feedbackToUser = feedbackToUser;
        this.isShowHelp = false;
        this.isExit = false;
        this.isContactCommand = isContactCommand;
        this.isTaskCommand = isTaskCommand;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return isShowHelp;
    }

    public boolean isExit() {
        return isExit;
    }

    public boolean isContactCommand() {
        return isContactCommand;
    }

    public boolean isTaskCommand() {
        return isTaskCommand;
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
                && isShowHelp == otherCommandResult.isShowHelp
                && isExit == otherCommandResult.isExit
                && isContactCommand == otherCommandResult.isContactCommand
                && isTaskCommand == otherCommandResult.isTaskCommand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isShowHelp, isExit);
    }

}

package seedu.foodrem.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {
    private final String feedbackToUser;

    private final String helpText;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    private CommandResult(String feedbackToUser, String helpText, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.helpText = requireNonNull(helpText);
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, "", showHelp, exit);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * a {@code helpText} to display to user and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, String helpText) {
        this(feedbackToUser, helpText, true, false);
    }

    private CommandResult(String feedbackToUser) {
        this(feedbackToUser, "", false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified message to display.
     * @param feedbackToUser the message to display to the user.
     * @return a {@code CommandResult} with the supplied string as a message.
     */
    public static CommandResult fromString(String feedbackToUser) {
        return new CommandResult(feedbackToUser);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public String getHelpText() {
        return helpText;
    }

    public boolean shouldShowHelp() {
        return showHelp;
    }

    public boolean shouldExit() {
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

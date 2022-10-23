package seedu.foodrem.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public abstract class CommandResult {
    /**
     * Constructs a {@code CommandResult} with the specified message to display.
     * @param feedbackToUser the message to display to the user.
     * @return a {@code CommandResult} with the supplied string as a message.
     */
    public static CommandResult fromString(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        return new CommandResult() {
            @Override
            public String getFeedbackToUser() {
                return feedbackToUser;
            }
        };
    }

    public String getFeedbackToUser() {
        throw new UnsupportedOperationException();
    }
    public String getHelpText() {
        throw new UnsupportedOperationException();
    }
    public boolean shouldShowHelp() {
        return false;
    }
    public boolean shouldExit() {
        return false;
    }
}

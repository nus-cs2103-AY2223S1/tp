package seedu.foodrem.logic.commands;

import static java.util.Objects.requireNonNull;

/**
 * Represents the result of a command execution.
 */
public abstract class CommandResult<T> {
    /**
     * Constructs a {@code CommandResult} with the specified message to display.
     * @param feedbackToUser the message to display to the user.
     * @return a {@code CommandResult} with the supplied string as a message.
     */
    public static CommandResult<String> fromString(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        return new CommandResult<>() {
            @Override
            public String getOutput() {
                return feedbackToUser;
            }
        };
    }

    public T getOutput() {
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

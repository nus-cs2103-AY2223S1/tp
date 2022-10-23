package seedu.foodrem.logic.commands;

import static java.util.Objects.requireNonNull;

/**
 * Represents the result of a command execution.
 */
public abstract class CommandResult<T> {
    /**
     * Constructs a {@code CommandResult} with the specified output.
     * @param output the output to eventually display to the user.
     * @return a {@code CommandResult} that holds the output.
     */
    public static <T> CommandResult<T> from(T output) {
        requireNonNull(output);
        return new CommandResult<>() {
            @Override
            public T getOutput() {
                return output;
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

package swift.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Type of the command. */
    private final CommandType commandType;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, CommandType commandType) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.commandType = commandType;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return commandType == CommandType.HELP;
    }

    public boolean isExit() {
        return commandType == CommandType.EXIT;
    }

    public boolean isContactCommand() {
        return commandType == CommandType.CONTACTS;
    }

    public boolean isTaskCommand() {
        return commandType == CommandType.TASKS;
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
                && commandType == otherCommandResult.commandType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, commandType);
    }

}

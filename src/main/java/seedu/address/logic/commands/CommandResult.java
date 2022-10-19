package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;


    /** the index of the entity being shown if the command is show **/
    private int indexOfShownEntity;

    private final CommandType type;

    public enum CommandType { LIST, SHOW, HELP, EXIT, OTHER }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * This should not be called by ShowCommand as it does not pass
     * the index of the entity shwon to the Ui.
     */
    public CommandResult(String feedbackToUser, CommandType type) {
        assert(type != CommandType.SHOW);
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.type = type;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * This should only be called by ShowCommand to pass the index
     * of the entity shown to the Ui.
     */
    public CommandResult(String feedbackToUser, int index) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.type = CommandType.SHOW;
        this.indexOfShownEntity = index;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.type = CommandType.OTHER;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return this.type == CommandType.HELP;
    }

    public boolean isExit() {
        return this.type == CommandType.EXIT;
    }

    public boolean isList() {
        return this.type == CommandType.LIST;
    }

    public boolean isShow() {
        return this.type == CommandType.SHOW;
    }

    public int getIndex() {
        assert(this.type == CommandType.SHOW);

        return indexOfShownEntity;
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
                && type == otherCommandResult.type
                && indexOfShownEntity == otherCommandResult.indexOfShownEntity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, type);
    }

}

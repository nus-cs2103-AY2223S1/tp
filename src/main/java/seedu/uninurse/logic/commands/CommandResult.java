package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;


    ///** Help information should be shown to the user. */
    //private final boolean showHelp;

    ///** The application should exit. */
    //private final boolean exit;

    private final CommandType commandType;


    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, CommandType commandType) {
        requireNonNull(feedbackToUser);
        requireNonNull(commandType);
        this.feedbackToUser = feedbackToUser;
        this.commandType = commandType;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return this.commandType == CommandType.HELP;
    }

    public boolean isExit() {
        return this.commandType == CommandType.EXIT;
    }

    public boolean isTaskRelated() {
        return this.commandType == CommandType.TASK;
    }

    public boolean isAddPatient() {
        return this.commandType == CommandType.ADD_PATIENT;
    }

    public boolean isEditPatient() {
        return this.commandType == CommandType.EDIT_PATIENT;
    }

    public boolean isDeletePatient() {
        return this.commandType == CommandType.DELETE_PATIENT;
    }

    public boolean isSchedule() {
        return this.commandType == CommandType.SCHEDULE;
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
                && this.commandType == otherCommandResult.commandType;

    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, commandType);
    }

}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;


    /** Help information should be shown to the user. */
    //private final boolean showHelp;

    /** The application should exit. */
    //private final boolean exit;


        // TODO: Intend to use SCHEDULE for ListTask
    static enum COMMANDTYPE {
        HELP, EXIT, TASK, PATIENT, CLEAR, LIST, SCHEDULE;
    }

    private final COMMANDTYPE commandType;


    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, COMMANDTYPE commandType) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.commandType = commandType;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    /*public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

     */

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return this.commandType == COMMANDTYPE.HELP;
    }

    public boolean isExit() {
        return this.commandType == COMMANDTYPE.EXIT;
    }

    public boolean isTaskRelated() {
        return this.commandType == COMMANDTYPE.TASK;
    }

    public boolean isPatientRelated() {
        return this.commandType == COMMANDTYPE.PATIENT;
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

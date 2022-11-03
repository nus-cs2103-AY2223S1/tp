package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.uninurse.model.PatientListTracker;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {
    private final String feedbackToUser;

    private final CommandType commandType;
    private final Optional<PatientListTracker> patientListTracker;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, CommandType commandType) {
        requireNonNull(feedbackToUser);
        requireNonNull(commandType);
        this.feedbackToUser = feedbackToUser;
        this.commandType = commandType;
        this.patientListTracker = Optional.empty();
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, CommandType commandType,
                         PatientListTracker patientListTracker) {
        requireNonNull(feedbackToUser);
        requireNonNull(commandType);
        this.feedbackToUser = feedbackToUser;
        this.commandType = commandType;
        this.patientListTracker = Optional.of(patientListTracker);
    }

    public Optional<PatientListTracker> getPatientListTracker() {
        return patientListTracker;
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

    public boolean isViewPatient() {
        return this.commandType == CommandType.VIEW_PATIENT;
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

    public boolean isListTask() {
        return this.commandType == CommandType.LIST_TASK;
    }

    public boolean isTaskRelated() {
        return this.commandType == CommandType.TASK;
    }

    public boolean isSchedule() {
        return this.commandType == CommandType.SCHEDULE;
    }

    public boolean isUndo() {
        return this.commandType == CommandType.UNDO;
    }

    public boolean isRedo() {
        return this.commandType == CommandType.REDO;
    }

    public boolean isFind() {
        return this.commandType == CommandType.FIND;
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

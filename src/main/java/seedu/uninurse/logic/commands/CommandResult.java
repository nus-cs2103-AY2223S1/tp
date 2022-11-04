package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

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
     * Constructs a CommandResult with the specified fields.
     */
    public CommandResult(String feedbackToUser, CommandType commandType) {
        requireAllNonNull(feedbackToUser, commandType);
        this.feedbackToUser = feedbackToUser;
        this.commandType = commandType;
        this.patientListTracker = Optional.empty();
    }

    /**
     * Constructs a CommandResult with the specified fields with a provided patientListTracker.
     */
    public CommandResult(String feedbackToUser, CommandType commandType, PatientListTracker patientListTracker) {
        requireAllNonNull(feedbackToUser, commandType, patientListTracker);
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
        return commandType == CommandType.HELP;
    }

    public boolean isExit() {
        return commandType == CommandType.EXIT;
    }

    public boolean isViewPatient() {
        return commandType == CommandType.VIEW_PATIENT;
    }

    public boolean isAddPatient() {
        return commandType == CommandType.ADD_PATIENT;
    }

    public boolean isEditPatient() {
        return commandType == CommandType.EDIT_PATIENT;
    }

    public boolean isDeletePatient() {
        return commandType == CommandType.DELETE_PATIENT;
    }

    public boolean isListTask() {
        return commandType == CommandType.LIST_TASK;
    }

    public boolean isTaskRelated() {
        return commandType == CommandType.TASK;
    }

    public boolean isSchedule() {
        return commandType == CommandType.SCHEDULE;
    }

    public boolean isUndo() {
        return commandType == CommandType.UNDO;
    }

    public boolean isRedo() {
        return commandType == CommandType.REDO;
    }

    public boolean isFind() {
        return commandType == CommandType.FIND;
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

        CommandResult o = (CommandResult) other;
        return feedbackToUser.equals(o.feedbackToUser)
                && commandType.equals(o.commandType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, commandType);
    }
}

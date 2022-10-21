package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Represents the result of a command execution.
 * CommandType is passed to the Ui to update Ui parts accordingly.
 * ListView is updated when AssignCommand and UnassignCommand is called.
 * Description is updated when AddCommand, ClearCommand, DeleteCommand,
 * EditCommand, AssignCommand and UnassignCommand is called.
 *
 */
public class CommandResult {

    private final String feedbackToUser;

    private final CommandType type;

    /** the index of the entity being shown if the command is show **/
    private int indexOfShownEntity;

    private Student deletedStudent;

    private Tutor deletedTutor;

    private TuitionClass deletedClass;

    /** Types of command which are passed to the Ui to determine actions to take for each type **/
    public enum CommandType { ADD, ASSIGN, CLEAR, DELETE, EDIT, EXIT, LIST, SHOW, HELP, OTHER }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * This should only be called by ListCommand, HelpCommand and
     * ExitCommand.
     */
    public CommandResult(String feedbackToUser, CommandType type) {
        assert(type == CommandType.ADD
                || type == CommandType.LIST
                || type == CommandType.EXIT
                || type == CommandType.HELP
                || type == CommandType.CLEAR);
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.type = type;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * This should only be called by ShowCommand, EditCommand,
     * AssignCommand and UnassignCommand.
     */
    public CommandResult(String feedbackToUser, CommandType type, int index) {
        assert (type == CommandType.SHOW || type == CommandType.ASSIGN || type == CommandType.EDIT);
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.indexOfShownEntity = index;
        this.type = type;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * This should only be called by DeleteCommand.
     */
    public CommandResult(String feedbackToUser, Student deletedStudent) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.deletedStudent = deletedStudent;
        this.type = CommandType.DELETE;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * This should only be called by DeleteCommand.
     */
    public CommandResult(String feedbackToUser, Tutor deletedTutor) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.deletedTutor = deletedTutor;
        this.type = CommandType.DELETE;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * This should only be called by DeleteCommand.
     */
    public CommandResult(String feedbackToUser, TuitionClass deletedClass) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.deletedClass = deletedClass;
        this.type = CommandType.DELETE;
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

    public int getIndex() {
        assert(type == CommandType.SHOW
        || type == CommandType.ASSIGN
        || type == CommandType.EDIT);

        return indexOfShownEntity;
    }

    public Student getDeletedStudent() {
        assert(deletedStudent != null);
        return deletedStudent;
    }

    public Tutor getDeletedTutor() {
        assert(deletedTutor != null);
        return deletedTutor;
    }

    public TuitionClass getDeletedClass() {
        assert(deletedClass != null);
        return deletedClass;
    }

    public boolean isClear() {
        return this.type == CommandType.CLEAR;
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

    public boolean isUpdateListView() {
        return this.type == CommandType.ASSIGN;
    }

    public boolean isUpdateDescription() {
        return this.type == CommandType.ASSIGN
                || this.type == CommandType.EDIT;
    }

    public boolean isDelete() {
        return this.type == CommandType.DELETE;
    }

    public boolean isAdd() {
        return this.type == CommandType.ADD;
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

        if (this.deletedStudent != null) {
            if (otherCommandResult.deletedStudent != null) {
                return this.deletedStudent == otherCommandResult.deletedStudent
                        && feedbackToUser.equals(otherCommandResult.feedbackToUser);
            }
        } else if (this.deletedTutor != null) {
            if (otherCommandResult.deletedTutor != null) {
                return this.deletedTutor == otherCommandResult.deletedTutor
                        && feedbackToUser.equals(otherCommandResult.feedbackToUser);
            }
        } else if (this.deletedClass != null) {
            if (otherCommandResult.deletedClass != null) {
                return this.deletedClass == otherCommandResult.deletedClass
                        && feedbackToUser.equals(otherCommandResult.feedbackToUser);
            }
        } else {
            return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                    && type == otherCommandResult.type
                    && indexOfShownEntity == otherCommandResult.indexOfShownEntity;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, indexOfShownEntity, type, deletedStudent, deletedTutor, deletedClass);
    }

}

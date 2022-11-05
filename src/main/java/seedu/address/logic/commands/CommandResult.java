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

    private final CommandType commandType;

    private CommandType queryType;

    /** the index of the entity being shown if the command is show **/
    private int indexOfShownEntity;

    private Student deletedStudent;

    private Tutor deletedTutor;

    private TuitionClass deletedClass;

    /** Types of command which are passed to the Ui to determine actions to take for each type **/
    public enum CommandType {
        ADD, EDIT, DELETE, SORT, CLEAR, FIND, LIST, EXIT, HELP, SHOW, ASSIGN, UNASSIGN, NOK, OTHER
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * This should only be called by ListCommand, HelpCommand and
     * ExitCommand.
     */
    public CommandResult(String feedbackToUser, CommandType type) {
        assert(type == CommandType.ADD
                || type == CommandType.LIST
                || type == CommandType.HELP
                || type == CommandType.EXIT
                || type == CommandType.CLEAR);
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.commandType = type;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * This should only be called by ShowCommand, EditCommand,
     * NextOfKinCommand, AssignCommand and UnassignCommand.
     */
    public CommandResult(String feedbackToUser, CommandType type, int index) {
        assert (type == CommandType.SHOW
                || type == CommandType.ASSIGN
                || type == CommandType.EDIT
                || type == CommandType.NOK);
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.indexOfShownEntity = index;
        this.commandType = type;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * This should only be called by DeleteCommand.
     */
    public CommandResult(String feedbackToUser, Student deletedStudent) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.deletedStudent = deletedStudent;
        this.commandType = CommandType.DELETE;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * This should only be called by DeleteCommand.
     */
    public CommandResult(String feedbackToUser, Tutor deletedTutor) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.deletedTutor = deletedTutor;
        this.commandType = CommandType.DELETE;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * This should only be called by DeleteCommand.
     */
    public CommandResult(String feedbackToUser, TuitionClass deletedClass) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.deletedClass = deletedClass;
        this.commandType = CommandType.DELETE;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.commandType = CommandType.OTHER;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, CommandType actualType, CommandType queryType) {
        assert (actualType == CommandType.HELP);
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.commandType = actualType;
        this.queryType = queryType;
    }

    public CommandType getQueryType() {
        return queryType;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public int getIndex() {
        assert(commandType == CommandType.SHOW
        || commandType == CommandType.ASSIGN
        || commandType == CommandType.EDIT
        || commandType == CommandType.NOK);

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
        return this.commandType == CommandType.CLEAR;
    }

    public boolean isShowHelp() {
        return this.commandType == CommandType.HELP;
    }

    public boolean isExit() {
        return this.commandType == CommandType.EXIT;
    }

    public boolean isList() {
        return this.commandType == CommandType.LIST;
    }

    public boolean isUpdateListView() {
        return this.commandType == CommandType.ASSIGN;
    }

    public boolean isUpdateDescription() {
        return this.commandType == CommandType.ASSIGN
                || this.commandType == CommandType.SHOW
                || this.commandType == CommandType.NOK;
    }

    public boolean isDelete() {
        return this.commandType == CommandType.DELETE;
    }

    public boolean isAdd() {
        return this.commandType == CommandType.ADD;
    }

    public boolean isEdit() {
        return this.commandType == CommandType.EDIT;
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
                    && commandType == otherCommandResult.commandType
                    && indexOfShownEntity == otherCommandResult.indexOfShownEntity;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, indexOfShownEntity, commandType,
                deletedStudent, deletedTutor, deletedClass);
    }

}

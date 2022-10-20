package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    private final CommandType type;

    /** the index of the entity being shown if the command is show **/
    private int indexOfShownEntity;

    private Student studentToModify;

    private Student modifiedStudent;

    private Tutor tutorToModify;

    private Tutor modifiedTutor;

    private TuitionClass classToModify;

    private TuitionClass modifiedClass;

    /** Types of command which are passed to the Ui to determine actions to take for each type **/
    public enum CommandType { LIST, SHOW, HELP, EXIT, ASSIGN, EDIT, OTHER }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * This should only be called by ListCommand, HelpCommand and
     * ExitCommand.
     */
    public CommandResult(String feedbackToUser, CommandType type) {
        assert(type == CommandType.LIST || type == CommandType.EXIT || type == CommandType.HELP);
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.type = type;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * This should only be called by EditCommand.
     */
    public CommandResult(String feedbackToUser, Student studentToModify, Student modifiedStudent) {
        this.studentToModify = studentToModify;
        this.modifiedStudent = modifiedStudent;
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.type = CommandType.EDIT;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * This should only be called by EditCommand.
     */
    public CommandResult(String feedbackToUser, Tutor tutorToModify, Tutor modifiedTutor) {
        this.tutorToModify = tutorToModify;
        this.modifiedTutor = modifiedTutor;
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.type = CommandType.EDIT;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * This should only be called by EditCommand.
     */
    public CommandResult(String feedbackToUser, TuitionClass classToModify, TuitionClass modifiedClass) {
        this.classToModify = classToModify;
        this.modifiedClass = modifiedClass;
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.type = CommandType.EDIT;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * This should only be called by AssignCommand and UnassignCommand.
     */
    public CommandResult(String feedbackToUser, Student modifiedStudent) {
        this.modifiedStudent = modifiedStudent;
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.type = CommandType.ASSIGN;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * This should only be called by AssignCommand and UnassignCommand.
     */
    public CommandResult(String feedbackToUser, Tutor modifiedTutor) {
        this.modifiedTutor = modifiedTutor;
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.type = CommandType.ASSIGN;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * This should only be called by AssignCommand and UnassignCommand.
     */
    public CommandResult(String feedbackToUser, TuitionClass modifiedClass) {
        this.modifiedClass = modifiedClass;
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.type = CommandType.ASSIGN;
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

    public boolean isUpdateListView() {
        return this.type == CommandType.ASSIGN;
    }

    public boolean isUpdateDescription() {
        return this.type == CommandType.ASSIGN || this.type == CommandType.EDIT;
    }

    public int getIndex() {
        assert(this.type == CommandType.SHOW);

        return indexOfShownEntity;
    }

    public Student getStudentToModify() {
        return studentToModify;
    }

    public Student getModifiedStudent() {
        return modifiedStudent;
    }

    public Tutor getTutorToModify() {
        return tutorToModify;
    }

    public Tutor getModifiedTutor() {
        return modifiedTutor;
    }

    public TuitionClass getClassToModify() {
        return classToModify;
    }

    public TuitionClass getModifiedClass() {
        return modifiedClass;
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

        if (this.modifiedStudent != null) {
            if (otherCommandResult.modifiedStudent != null) {
                return this.modifiedStudent == otherCommandResult.modifiedStudent;
            }
        }
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && type == otherCommandResult.type
                && indexOfShownEntity == otherCommandResult.indexOfShownEntity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, indexOfShownEntity, type);
    }

}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.CommandResult.CommandType.ASSIGN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.Name;
import seedu.address.model.tuitionclass.TuitionClass;
import seedu.address.model.tuitionclass.exceptions.TuitionClassNotAssignedException;
import seedu.address.model.tuitionclass.exceptions.TuitionClassNotFoundException;

/**
 * Unassigned a person identified using it's displayed index from the address book from a specified tuition class.
 */
public class UnassignCommand extends Command {

    public static final String COMMAND_WORD = "unassign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unassigned the person identified by the index "
            + "number used in the displayed person list from the specified class if it exists.\n"
            + "Parameters: unassign"
            + "INDEX (must be a positive integer)"
            + "[" + PREFIX_NAME + " CLASS NAME] \n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_NAME + "P2MATHS";

    public static final String MESSAGE_UNASSIGNED_STUDENT_SUCCESS = "Unassigned Student: %1$s";
    public static final String MESSAGE_UNASSIGNED_TUTOR_SUCCESS = "Unassigned Tutor: %1$s";
    public static final String MESSAGE_INVALID_CURRENT_LIST = "The current list type is invalid for assign command \n"
            + "Valid list type are tutor and student list";

    public static final String MESSAGE_INVALID_UNASSIGNED_STUDENT = "This student was not been assigned to this class";
    public static final String MESSAGE_INVALID_UNASSIGNED_TUTOR = "This tutor was not been assigned to this class";
    public static final String MESSAGE_INVALID_TUITION_CLASS = "Tuition class does not exist";

    private final Index index;
    private final Name className;

    /**
     * @param index of the person in the filtered person list to unassign.
     * @param className name of the class to unassigned the person from.
     */
    public UnassignCommand(Index index, Name className) {
        requireAllNonNull(index, className);
        this.index = index;
        this.className = className;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Model.ListType type = model.getCurrentListType();

        switch(type) {
        case STUDENT_LIST:
            List<Student> lastShownStudentList = model.getFilteredStudentList();
            if (index.getZeroBased() >= lastShownStudentList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Student studentToUnassign = lastShownStudentList.get(index.getZeroBased());
            try {
                TuitionClass tuitionClassToBeUnassigned = model.getTuitionClass(className);
                studentToUnassign.unassignClassFromStudent(tuitionClassToBeUnassigned);
                return new CommandResult(String.format(MESSAGE_UNASSIGNED_STUDENT_SUCCESS, studentToUnassign),
                        ASSIGN, index.getZeroBased());
            } catch (TuitionClassNotFoundException e) {
                throw new CommandException(MESSAGE_INVALID_TUITION_CLASS);
            } catch (TuitionClassNotAssignedException e) {
                throw new CommandException(MESSAGE_INVALID_UNASSIGNED_STUDENT);
            }
        case TUTOR_LIST:
            List<Tutor> lastShownTutorList = model.getFilteredTutorList();
            if (index.getZeroBased() >= lastShownTutorList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Tutor tutorToUnassign = lastShownTutorList.get(index.getZeroBased());
            try {
                TuitionClass tuitionClassToBeAssignedFrom = model.getTuitionClass(className);
                tutorToUnassign.unassignClassFromTutor(tuitionClassToBeAssignedFrom);
                return new CommandResult(
                        String.format(MESSAGE_UNASSIGNED_TUTOR_SUCCESS, tutorToUnassign),
                        ASSIGN, index.getZeroBased());
            } catch (TuitionClassNotFoundException e) {
                throw new CommandException(MESSAGE_INVALID_TUITION_CLASS);
            } catch (TuitionClassNotAssignedException e) {
                throw new CommandException(MESSAGE_INVALID_UNASSIGNED_TUTOR);
            }
        default:
            throw new CommandException(MESSAGE_INVALID_CURRENT_LIST);
        }

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnassignCommand)) {
            return false;
        }

        // state check
        UnassignCommand otherUnassignCommand = (UnassignCommand) other;
        return index.equals(otherUnassignCommand.index)
                && className.equals(otherUnassignCommand.className);
    }


}

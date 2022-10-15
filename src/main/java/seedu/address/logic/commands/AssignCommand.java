package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Model.ListType;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.Name;
import seedu.address.model.tuitionclass.TuitionClass;
import seedu.address.model.tuitionclass.exceptions.TuitionClassNotFoundException;

/**
 * Assign a person identified using it's displayed index from the address book to a specified class.
 */
public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assign the person identified by the index "
            + "number used in the displayed person list to the specified class if it exists.\n"
            + "Parameters: assign"
            + "INDEX (must be a positive integer)"
            + "[" + PREFIX_NAME + " CLASS NAME] \n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_NAME + "P2MATHS";

    public static final String MESSAGE_ASSIGN_STUDENT_SUCCESS = "Assigned Student: %1$s";
    public static final String MESSAGE_ASSIGN_TUTOR_SUCCESS = "Assigned Tutor: %1$s";
    public static final String MESSAGE_INVALID_CURRENT_LIST = "The current list type is invalid for assign command \n"
            + "Valid list type are tutor and student list";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student has already been assigned to this class";
    public static final String MESSAGE_DUPLICATE_TUTOR = "This tutor has already been assigned to this class";
    public static final String MESSAGE_INVALID_TUITION_CLASS = "Tuition class does not exist";


    private final Index index;
    private final Name className;

    /**
     * @param index of the person in the filtered person list to assign.
     * @param className name of the class to assigned the person to.
     */
    public AssignCommand(Index index, Name className) {
        requireAllNonNull(index, className);
        this.index = index;
        this.className = className;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ListType type = model.getCurrentListType();

        switch(type) {
        case STUDENT_LIST:
            List<Student> lastShownStudentList = model.getFilteredStudentList();
            if (index.getZeroBased() >= lastShownStudentList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Student studentToAssign = lastShownStudentList.get(index.getZeroBased());
            try {
                TuitionClass tuitionClassToBeAssigned = model.getTuitionClass(className);
                if (tuitionClassToBeAssigned.containsStudentInClass(studentToAssign)) {
                    throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
                }
                tuitionClassToBeAssigned.addStudentToClass(studentToAssign);
                return new CommandResult(String.format(MESSAGE_ASSIGN_STUDENT_SUCCESS, studentToAssign));
            } catch (TuitionClassNotFoundException e) {
                throw new CommandException(MESSAGE_INVALID_TUITION_CLASS);
            }
        case TUTOR_LIST:
            List<Tutor> lastShownTutorList = model.getFilteredTutorList();
            if (index.getZeroBased() >= lastShownTutorList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Tutor tutorToAssign = lastShownTutorList.get(index.getZeroBased());
            try {
                TuitionClass tuitionClassToBeAssignedTo = model.getTuitionClass(className);
                if (tuitionClassToBeAssignedTo.containsTutorInClass(tutorToAssign)) {
                    throw new CommandException(MESSAGE_DUPLICATE_TUTOR);
                }
                tuitionClassToBeAssignedTo.addTutorToClass(tutorToAssign);
                return new CommandResult(String.format(MESSAGE_ASSIGN_TUTOR_SUCCESS, tutorToAssign));
            } catch (TuitionClassNotFoundException e) {
                throw new CommandException(MESSAGE_INVALID_TUITION_CLASS);
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
        if (!(other instanceof AssignCommand)) {
            return false;
        }

        // state check
        AssignCommand otherAssignCommand = (AssignCommand) other;
        return index.equals(otherAssignCommand.index)
                && className.equals(otherAssignCommand.className);
    }
}

package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.position.Student;

/**
 * Changes the grade of an existing student in the address book.
 */
public class GradeCommand extends Command {
    public static final String COMMAND_WORD = "grade";

    public static final String EMPTY_FIELD = "Please provide an assignment field.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the grade of the Assignment of a student"
            + " identified by the index number used in the last person listing.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_ASSIGNMENT + "INDEX (must be a positive integer) "
            + PREFIX_GRADE + "GRADE ([integer (0-100)]/[integer (0-100)])\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 " + PREFIX_ASSIGNMENT + "1 "
            + PREFIX_GRADE + "18/20.";
    public static final String MESSAGE_PERSON_NOT_STUDENT = "The person to edit is not a student, there is no "
            + "grade to be edited.";
    public static final String MESSAGE_EDIT_GRADE_SUCCESS = "Edited grade to student: %1$s";

    private final Index indexOfStudent;
    private final Index indexOfAssignment;
    private final String grade;

    /**
     * @param indexOfStudent Index of the student in the filtered person list to edit the grade
     * @param indexOfAssignment Index of the assignment in the student's assignment list
     * @param grade of the student to be updated to
     */
    public GradeCommand(Index indexOfStudent, Index indexOfAssignment, String grade) {
        requireAllNonNull(indexOfStudent, indexOfAssignment, grade);

        this.indexOfStudent = indexOfStudent;
        this.indexOfAssignment = indexOfAssignment;
        this.grade = grade;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (indexOfStudent.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(indexOfStudent.getZeroBased());
        if (!(personToEdit.getPosition() instanceof Student)) {
            throw new CommandException(MESSAGE_PERSON_NOT_STUDENT);
        }
        Student currPosition = (Student) personToEdit.getPosition();
        Student editedPosition = new Student(currPosition.getAttendance(),
                currPosition.updateOverallGrade(indexOfAssignment, grade),
                currPosition.getAssignmentsList(), currPosition.getFilePath());
        Person editedPerson = new Person(personToEdit.getName(),
                personToEdit.getPhone(),
                personToEdit.getEmail(),
                editedPosition,
                personToEdit.getAddress(),
                personToEdit.getRemark(),
                personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether the availability is edited for
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_EDIT_GRADE_SUCCESS, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AvailabilityCommand)) {
            return false;
        }

        // state check
        GradeCommand e = (GradeCommand) other;
        return indexOfStudent.equals(e.indexOfStudent)
                && grade.equals(e.grade);
    }

}

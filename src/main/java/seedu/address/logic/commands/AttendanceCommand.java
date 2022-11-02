package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.position.Student;

/**
 * Changes the attendance of an existing student in the address book.
 */
public class AttendanceCommand extends Command {
    public static final String COMMAND_WORD = "attendance";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the attendance of the Student identified "
            + "by the index number used in the last person listing.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_ATTENDANCE
            + "ATTENDANCE ([integer (0-100)]/[integer (0-100)])\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ATTENDANCE + "1/2.";
    public static final String MESSAGE_PERSON_NOT_STUDENT = "The person to edit is not a student, there is no "
            + "attendance to be edited.";
    public static final String MESSAGE_EDIT_ATTENDANCE_SUCCESS = "Edited attendance to student: %1$s";

    private final Index index;
    private final String attendance;

    /**
     * @param index of the student in the filtered person list to edit the attendance
     * @param attendance of the student to be updated to
     */
    public AttendanceCommand(Index index, String attendance) {
        requireAllNonNull(index, attendance);

        this.index = index;
        this.attendance = attendance;
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

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        if (!(personToEdit.getPosition() instanceof Student)) {
            throw new CommandException(MESSAGE_PERSON_NOT_STUDENT);
        }
        Student currPosition = (Student) personToEdit.getPosition();
        Student editedPosition = new Student(attendance,
                currPosition.getOverallGrade(),
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
        return String.format(MESSAGE_EDIT_ATTENDANCE_SUCCESS, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AttendanceCommand)) {
            return false;
        }

        // state check
        AttendanceCommand e = (AttendanceCommand) other;
        return index.equals(e.index)
                && attendance.equals(e.attendance);
    }

}

package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.student.Student;

/**
 * Edits attendance in the address book.
 */
public class AttendanceCommand extends Command {

    public static final String COMMAND_WORD = "attendance";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";

    public static final String MESSAGE_ADD_ATTENDANCE_SUCCESS = "Added attendance to Student: %1$s";
    public static final String MESSAGE_DELETE_ATTENDANCE_SUCCESS = "Removed attendance from Student: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the attendance of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_ATTENDANCE + "[ATTENDANCE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ATTENDANCE + "Likes to swim.";

    private final Index index;
    private final Attendance attendance;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param attendance of the person to be updated to
     */
    public AttendanceCommand(Index index, Attendance attendance) {
        requireAllNonNull(index, attendance);
        this.index = index;
        this.attendance = attendance;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = new Student(
                studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
                studentToEdit.getClassGroup(), studentToEdit.getStudentId(), studentToEdit.getTags(),
                attendance, studentToEdit.getPicture());

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(generateSuccessMessage(editedStudent));
    }

    /**
     * Generates a command execution success message based on whether
     * the attendance is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Student studentToEdit) {
        String message = attendance.value.equals("1")
                ? MESSAGE_ADD_ATTENDANCE_SUCCESS
                : MESSAGE_DELETE_ATTENDANCE_SUCCESS;
        return String.format(message, studentToEdit);
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

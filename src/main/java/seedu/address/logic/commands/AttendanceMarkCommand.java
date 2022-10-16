package seedu.address.logic.commands;

import static seedu.address.logic.parser.AttendanceCommandParser.ATTENDANCE_COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MARK;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.attendance.AttendanceList;
import seedu.address.model.student.Student;

/**
 * Marks attendance of student in address book.
 */
public class AttendanceMarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_ADD_ATTENDANCE_SUCCESS = "Marked attendance of Student: %1$s";
    public static final String MESSAGE_DELETE_ATTENDANCE_SUCCESS = "Unmarked attendance of Student: %1$s";
    public static final String MESSAGE_USAGE = ATTENDANCE_COMMAND_WORD + " "
            + COMMAND_WORD
            + ": marks the lesson index of the student identified "
            + "by the index number used in the last student listing.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_LESSON + "[LESSON_NUMBER] "
            + PREFIX_MARK + "[ATTENDANCE]\n"
            + "Example: " + ATTENDANCE_COMMAND_WORD
            + " " + COMMAND_WORD + " 1 "
            + PREFIX_LESSON + "3 " + PREFIX_MARK + "1";
    private final Index studentIndex;
    private final String lessonIndex;
    private final Attendance attendance;

    /**
     * Creates an AttendanceMarkCommand to mark attendance of the specified {@code Student}
     * @param studentIndex
     * @param lessonIndex
     * @param attendance
     */
    public AttendanceMarkCommand(Index studentIndex, String lessonIndex, Attendance attendance) {
        this.studentIndex = studentIndex;
        this.lessonIndex = lessonIndex;
        this.attendance = attendance;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();
        if (studentIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }
        Student studentToEdit = lastShownList.get(studentIndex.getZeroBased());
        if (studentToEdit.getAttendanceList().isEmpty()
                || studentToEdit.getAttendanceList().getSize() < Integer.parseInt(lessonIndex)) {
            throw new CommandException(Messages.MESSAGE_INVALID_ATTENDANCE_LIST_INDEX);
        }
        if (Integer.parseInt(lessonIndex) < 1) {
            throw new CommandException(Messages.MESSAGE_INVALID_ATTENDANCE_LIST_INDEX);
        }
        AttendanceList attendanceList = studentToEdit.getAttendanceList();
        attendanceList.mark(lessonIndex, attendance);
        Student editedStudent = new Student(
                studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
                studentToEdit.getClassGroup(), studentToEdit.getStudentId(), studentToEdit.getTags(),
                attendanceList);
        model.setStudent(studentToEdit, editedStudent);
        return new CommandResult(generateSuccessMessage(editedStudent));
    }
    /**
     * Generates a command execution success message based on whether
     * the attendance is added to or removed from
     * {@code studentToEdit}.
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
        if (!(other instanceof AttendanceMarkCommand)) {
            return false;
        }

        // state check
        AttendanceMarkCommand e = (AttendanceMarkCommand) other;
        return studentIndex.equals(e.studentIndex)
                && attendance.equals(e.attendance)
                && lessonIndex.equals(lessonIndex);
    }

}

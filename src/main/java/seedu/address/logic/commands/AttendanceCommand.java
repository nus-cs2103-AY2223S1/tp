package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.HelpTag;
import seedu.address.model.student.Response;
import seedu.address.model.student.StuEmail;
import seedu.address.model.student.StuName;
import seedu.address.model.student.Student;
import seedu.address.model.student.Telegram;

/**
 * Adds attendance of an existing student in SETA.
 */
public class AttendanceCommand extends Command {

    public static final String COMMAND_WORD = "attendance";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Increment attendance by 1 for the student identified "
            + "by the index number used in the displayed student list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_ATTENDANCE_SUCCESS = "%1$s's attendance is marked!";

    private final Index index;

    /**
     * @param index of the student in the filtered student list
     */
    public AttendanceCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToMarkAttendance = lastShownList.get(index.getZeroBased());
        Student attendanceMarkedStudent = createAttendanceMarkedStudent(studentToMarkAttendance);

        model.setStudent(studentToMarkAttendance, attendanceMarkedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_ATTENDANCE_SUCCESS, attendanceMarkedStudent));
    }

    private Student createAttendanceMarkedStudent(Student studentToMarkAttendance) {
        assert studentToMarkAttendance != null;

        Attendance attendance = studentToMarkAttendance.getAttendance();
        StuName name = studentToMarkAttendance.getName();
        Telegram telegram = studentToMarkAttendance.getTelegram();
        StuEmail email = studentToMarkAttendance.getEmail();
        Response response = studentToMarkAttendance.getResponse();
        HelpTag helpTag = studentToMarkAttendance.getHelpTag();

        String updatedAttendanceString = String.valueOf(Integer.parseInt(attendance.toString()) + 1);
        Attendance updatedAttendance = new Attendance(updatedAttendanceString);

        return new Student(name, telegram, email, response, updatedAttendance, helpTag);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttendanceCommand // instanceof handles nulls
                && index.equals(((AttendanceCommand) other).index)); // state check
    }
}

package seedu.studmap.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.studmap.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.studmap.commons.core.Messages;
import seedu.studmap.commons.core.index.Index;
import seedu.studmap.logic.commands.exceptions.CommandException;
import seedu.studmap.model.Model;
import seedu.studmap.model.student.Attendance;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.StudentData;

/**
 * Marks a student identified using its displayed index from the student map as having attended a class or tutorial.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the attendance for student identified by the index number used in the displayed"
            + " student list.\n Marks attendance for the class or tutorial specified in the parameter.\n"
            + "Parameters: INDEX (must be positive integer) OPTION (must be absent/present) "
            + PREFIX_CLASS + " [CLASS]\n"
            + "Example: " + COMMAND_WORD + " 1 present " + PREFIX_CLASS + " T01";

    public static final String MESSAGE_MARK_SUCCESS = "Marked Student as %1$s: %2$s";

    private final Index index;
    private final Attendance attendance;

    /**
     * @param index Index of the student in the filtered student list to edit the attendance
     * @param attendance Attendance of the student to be added
     */
    public MarkCommand(Index index, Attendance attendance) {
        this.index = index;
        this.attendance = attendance;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        StudentData studentData = studentToEdit.getStudentData();

        Set<Attendance> newAttendance = new HashSet<>(studentToEdit.getAttendances());
        newAttendance.add(attendance);
        studentData.setAttendances(newAttendance);
        Student editedStudent = new Student(studentData);

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(
                String.format(MESSAGE_MARK_SUCCESS, attendance.getAttendance(), editedStudent));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        // state check
        MarkCommand e = (MarkCommand) other;
        return index.equals(e.index)
                && attendance.equals(e.attendance);
    }
}

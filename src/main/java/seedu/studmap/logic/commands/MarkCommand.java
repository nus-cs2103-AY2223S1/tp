package seedu.studmap.logic.commands;

import static seedu.studmap.logic.parser.CliSyntax.PREFIX_CLASS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.logic.commands.commons.StudentEditor;
import seedu.studmap.model.student.Attendance;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.StudentData;

/**
 * Marks a student identified using its displayed index from the student map as having attended a class or tutorial.
 */
public class MarkCommand extends EditStudentCommand<MarkCommand.MarkCommandStudentEditor> {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the attendance for a student identified by the index number used in the displayed"
            + " student list.\n"
            + "Marks attendance for the class or tutorial specified in the parameter.\n"
            + "Parameters: INDEX (must be positive integer or use \"all\" to mark everyone currently displayed)"
            + " STATUS (must be absent/present) " + PREFIX_CLASS + "CLASS\n"
            + "Example: " + COMMAND_WORD + " 1 present " + PREFIX_CLASS + "T01\n"
            + "Example: " + COMMAND_WORD + " all present " + PREFIX_CLASS + "T07";

    public static final String MESSAGE_MARK_SINGLE_SUCCESS_ATTENDANCE = "Marked Student as %1$s";
    public static final String MESSAGE_MARK_MULTI_SUCCESS_ATTENDANCE = "Marked %1$s students as %2$s";

    public static final String MESSAGE_MARK_SINGLE_UNEDITED_ATTENDANCE = "Student already marked as %1$s";
    public static final String MESSAGE_MARK_MULTI_UNEDITED_ATTENDANCE = "%1$s students are already marked as %2$s";

    public static final String MESSAGE_NO_EDIT = "Attendance must be provided.";

    public MarkCommand(IndexListGenerator indexListGenerator, MarkCommandStudentEditor studentEditor) {
        super(indexListGenerator, studentEditor);
    }

    @Override
    public String getSingleEditSuccessMessage(Student editedStudent) {
        return String.format(MESSAGE_MARK_SINGLE_SUCCESS_ATTENDANCE,
                studentEditor.getAttendance().getString());
    }

    @Override
    public String getMultiEditSuccessMessage(List<Student> editedStudents) {
        return String.format(MESSAGE_MARK_MULTI_SUCCESS_ATTENDANCE,
                editedStudents.size(), studentEditor.getAttendance().getString());
    }

    @Override
    public String getSingleUneditedMessage(Student uneditedStudent) {
        return String.format(MESSAGE_MARK_SINGLE_UNEDITED_ATTENDANCE,
                studentEditor.getAttendance().getString());
    }

    @Override
    public String getMultiUneditedMessage(List<Student> uneditedStudents) {
        return String.format(MESSAGE_MARK_MULTI_UNEDITED_ATTENDANCE,
                uneditedStudents.size(), studentEditor.getAttendance().getString());
    }

    @Override
    public String getNoEditMessage() {
        return MESSAGE_NO_EDIT;
    }

    /**
     * A static StudentEditor that adjusts Attendance for a given Student.
     */
    public static class MarkCommandStudentEditor implements StudentEditor {

        private final Attendance attendance;

        /**
         * Constructor using Attendance.
         *
         * @param attendance Attendance to edit the student with.
         */
        public MarkCommandStudentEditor(Attendance attendance) {
            this.attendance = attendance;
        }

        public Attendance getAttendance() {
            return attendance;
        }

        @Override
        public EditResult editStudent(Student studentToEdit) {
            StudentData studentData = studentToEdit.getStudentData();
            Set<Attendance> newAttendances = new HashSet<>(studentToEdit.getAttendances());

            if (newAttendances.stream().anyMatch(x -> x.strongEquals(attendance))) {
                return new EditResult(studentToEdit, false);
            }

            newAttendances.remove(attendance);
            newAttendances.add(attendance);
            studentData.setAttendances(newAttendances);
            return new EditResult(new Student(studentData), true);
        }

        @Override
        public boolean hasEdits() {
            return attendance != null;
        }

        @Override
        public boolean equals(Object other) {

            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof MarkCommand.MarkCommandStudentEditor)) {
                return false;
            }

            // state check
            MarkCommand.MarkCommandStudentEditor e = (MarkCommand.MarkCommandStudentEditor) other;

            if (getAttendance() == null && e.getAttendance() != null) {
                return false;
            } else if (getAttendance() == null && e.getAttendance() == null) {
                return true;
            }

            return getAttendance().equals(e.getAttendance());
        }
    }
}

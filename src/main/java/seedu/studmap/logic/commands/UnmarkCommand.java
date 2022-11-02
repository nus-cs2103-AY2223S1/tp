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
 * Unmarks the specified attendance record from the student identified using its displayed index.
 */
public class UnmarkCommand extends EditStudentCommand<UnmarkCommand.UnmarkCommandStudentEditor> {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmarks the attendance for student identified by the index number used in the displayed student list."
            + "\nRemoves attendance record for the class or tutorial specified in the parameter.\n"
            + "Parameters: INDEX (must be positive integer) "
            + PREFIX_CLASS + "CLASS\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_CLASS + " T01\n"
            + "Example: " + COMMAND_WORD + " all " + PREFIX_CLASS + " T02";

    public static final String MESSAGE_UNMARK_SINGLE_ATTENDANCE_SUCCESS = "Removed Class %1$s from Student: %2$s";

    public static final String MESSAGE_UNMARK_MULTI_ATTENDANCE_SUCCESS = "Removed Class %1$s from %2$s students";

    public static final String MESSAGE_UNMARK_ATTENDANCE_NOTFOUND = "Class %1$s not found in Student: %2$s";

    public static final String MESSAGE_NO_EDIT = "Attendance must be provided.";

    public UnmarkCommand(IndexListGenerator indexListGenerator, UnmarkCommandStudentEditor studentEditor) {
        super(indexListGenerator, studentEditor);
    }

    @Override
    public String getSingleEditSuccessMessage(Student editedStudent) {
        return String.format(MESSAGE_UNMARK_SINGLE_ATTENDANCE_SUCCESS,
                studentEditor.getAttendance().className,
                editedStudent);
    }

    @Override
    public String getMultiEditSuccessMessage(List<Student> editedStudents) {
        return String.format(MESSAGE_UNMARK_MULTI_ATTENDANCE_SUCCESS,
                studentEditor.getAttendance().className,
                editedStudents.size());
    }

    @Override
    public String getNoEditMessage() {
        return MESSAGE_NO_EDIT;
    }

    /**
     * A static StudentEditor that adjusts Attendance for a given Student.
     */
    public static class UnmarkCommandStudentEditor implements StudentEditor {

        private final Attendance attendance;

        /**
         * Constructor using Attendance.
         *
         * @param attendance Attendance to edit the student with.
         */
        public UnmarkCommandStudentEditor(Attendance attendance) {
            this.attendance = attendance;
        }

        public Attendance getAttendance() {
            return attendance;
        }

        @Override
        public Student editStudent(Student studentToEdit) {
            StudentData studentData = studentToEdit.getStudentData();

            Set<Attendance> newAttendance = new HashSet<>(studentToEdit.getAttendances());
            newAttendance.remove(attendance);
            studentData.setAttendances(newAttendance);

            return new Student(studentData);
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
            if (!(other instanceof UnmarkCommand.UnmarkCommandStudentEditor)) {
                return false;
            }

            // state check
            UnmarkCommand.UnmarkCommandStudentEditor e = (UnmarkCommand.UnmarkCommandStudentEditor) other;

            if (getAttendance() == null && e.getAttendance() != null) {
                return false;
            } else if (getAttendance() == null && e.getAttendance() == null) {
                return true;
            }

            return getAttendance().equals(e.getAttendance());
        }
    }
}

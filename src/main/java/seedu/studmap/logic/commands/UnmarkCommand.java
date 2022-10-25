package seedu.studmap.logic.commands;

import static seedu.studmap.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_CLASS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.logic.commands.commons.StudentEditor;
import seedu.studmap.model.student.Assignment;
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
            + "\nSupport both attendance and assignment."
            + "\n<Attendance>"
            + "\n Removes attendance record for the class or tutorial specified in the parameter.\n"
            + "Parameters: INDEX (must be positive integer) "
            + PREFIX_CLASS + " [CLASS]\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_CLASS + " T01"
            + "\n<Assignment>"
            + "\n Removes the specified assignment.\n"
            + "Parameters: INDEX (must be positive integer) "
            + PREFIX_ASSIGNMENT + " [CLASS]"
            + "\n Example: " + COMMAND_WORD + " 1 " + PREFIX_ASSIGNMENT + " A01";

    public static final String MESSAGE_UNMARK_SINGLE_ATTENDANCE_SUCCESS = "Removed Class %1$s from Student: %2$s";
    public static final String MESSAGE_UNMARK_SINGLE_ASSIGNMENT_SUCCESS = "Removed Assignment %1$s from Student: %2$s";

    public static final String MESSAGE_UNMARK_MULTI_ATTENDANCE_SUCCESS = "Removed Class %1$s from %2$s students";
    public static final String MESSAGE_UNMARK_MULTI_ASSIGNMENT_SUCCESS = "Removed Assignment %1$s from %2$s Students";

    public static final String MESSAGE_UNMARK_ATTENDANCE_NOTFOUND = "Class %1$s not found in Student: %2$s";
    public static final String MESSAGE_UNMARK_ASSIGNMENT_NOTFOUND = "Assignment %1$s not found in Student: %2$s";

    public static final String MESSAGE_NO_EDIT = "Attendance or Assignment must be provided.";

    public UnmarkCommand(IndexListGenerator indexListGenerator, UnmarkCommandStudentEditor studentEditor) {
        super(indexListGenerator, studentEditor);
    }

    @Override
    public String getSingleEditSuccessMessage(Student editedStudent) {
        if (studentEditor.getAttendance() != null) {
            assert studentEditor.getAssignment() == null : "Assignment should not be marked if attendance is marked";
            return String.format(MESSAGE_UNMARK_SINGLE_ATTENDANCE_SUCCESS,
                    studentEditor.getAttendance().className,
                    editedStudent);
        } else {
            assert studentEditor.getAttendance() == null : "Attendance should not be marked if assignment is marked";
            Assignment assignment = studentEditor.getAssignment();
            return String.format(MESSAGE_UNMARK_SINGLE_ASSIGNMENT_SUCCESS,
                    assignment.getAssignmentName(),
                    editedStudent);
        }
    }

    @Override
    public String getMultiEditSuccessMessage(List<Student> editedStudents) {
        if (studentEditor.getAttendance() != null) {
            assert studentEditor.getAssignment() == null : "Assignment should not be marked if attendance is marked";
            return String.format(MESSAGE_UNMARK_MULTI_ATTENDANCE_SUCCESS,
                    studentEditor.getAttendance().className,
                    editedStudents.size());
        } else {
            assert studentEditor.getAssignment() == null : "Attendance should not be marked if assignment is marked";
            Assignment assignment = studentEditor.getAssignment();
            return String.format(MESSAGE_UNMARK_MULTI_ASSIGNMENT_SUCCESS,
                    assignment.getAssignmentName(),
                    editedStudents.size());
        }
    }

    @Override
    public String getNoEditMessage() {
        return MESSAGE_NO_EDIT;
    }

    /**
     * A static StudentEditor that adjusts Attendance or Assignment for a given Student.
     */
    public static class UnmarkCommandStudentEditor implements StudentEditor {

        private final Attendance attendance;
        private final Assignment assignment;

        /**
         * Constructor using Attendance.
         *
         * @param attendance Attendance to edit the student with.
         */
        public UnmarkCommandStudentEditor(Attendance attendance) {
            this.assignment = null;
            this.attendance = attendance;
        }

        /**
         * Constructor using Assignment.
         *
         * @param assignment Assignment to edit the student with.
         */
        public UnmarkCommandStudentEditor(Assignment assignment) {
            this.assignment = assignment;
            this.attendance = null;
        }

        public Attendance getAttendance() {
            return attendance;
        }

        public Assignment getAssignment() {
            return assignment;
        }


        @Override
        public Student editStudent(Student studentToEdit) {
            StudentData studentData = studentToEdit.getStudentData();

            if (attendance != null) {
                Set<Attendance> newAttendance = new HashSet<>(studentToEdit.getAttendances());
                newAttendance.remove(attendance);
                studentData.setAttendances(newAttendance);
            } else if (assignment != null) {
                Set<Assignment> newAssignments = new HashSet<>(studentToEdit.getAssignments());
                newAssignments.remove(assignment);
                studentData.setAssignments(newAssignments);
            }

            return new Student(studentData);
        }

        @Override
        public boolean hasEdits() {
            return attendance != null || assignment != null;
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

            if (getAssignment() == null && e.getAssignment() != null) {
                return false;
            } else if (getAssignment() == null && e.getAssignment() == null) {
                return true;
            }

            return getAttendance().equals(e.getAttendance()) && getAssignment().equals(e.getAssignment());
        }
    }
}

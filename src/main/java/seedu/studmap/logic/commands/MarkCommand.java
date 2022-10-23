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
 * Marks a student identified using its displayed index from the student map as having attended a class or tutorial.
 */
public class MarkCommand extends EditStudentCommand<MarkCommand.MarkCommandStudentEditor> {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the status for a student identified by the index number used in the displayed"
            + " student list.\nThis command support both attendance and assignment marking."
            + "\n<Attendance>"
            + "\n  Marks attendance for the class or tutorial specified in the parameter."
            + "\n  Parameters: INDEX (must be positive integer or use \"all\" to mark everyone currently displayed)"
            + " OPTION (must be absent/present) "
            + PREFIX_CLASS + " [CLASS]"
            + "\n Example: " + COMMAND_WORD + " 1 present " + PREFIX_CLASS + "T01"
            + "\n Example: " + COMMAND_WORD + " all present " + PREFIX_CLASS + "T07"
            + "\n\n<Assignment>"
            + "\n Marks the status for the assignment specified in the parameter.\n"
            + "Parameters: INDEX (must be positive integer or use \"all\" to mark everyone currently displayed)"
            + " OPTION (must be marked/received/new where 'new' also represents an unmarked assignment) "
            + PREFIX_ASSIGNMENT + " [CLASS]\n"
            + "Example: " + COMMAND_WORD + " 1 new " + PREFIX_ASSIGNMENT + "A01\n"
            + "Example: " + COMMAND_WORD + " all marked " + PREFIX_ASSIGNMENT + "A07";;

    public static final String MESSAGE_MARK_SINGLE_SUCCESS_ATTENDACE = "Marked Student as %1$s: %2$s";
    public static final String MESSAGE_MARK_MULTI_SUCCESS_ATTENDACE = "Marked %1$s students as %2$s";
    public static final String MESSAGE_MARK_SINGLE_SUCCESS_ASSIGNMENT =
            "Marked assignment %1$s for student %2$s as %3$s:" + " \n%4$s";
    public static final String MESSAGE_MARK_MULTI_SUCCESS_ASSIGNMENT =
            "Marked assignment %1$s as %2$s for %3$s students: ";

    public static final String MESSAGE_NO_EDIT = "Attendance or Assignment must be provided.";

    public MarkCommand(IndexListGenerator indexListGenerator, MarkCommandStudentEditor studentEditor) {
        super(indexListGenerator, studentEditor);
    }

    @Override
    public String getSingleEditSuccessMessage(Student editedStudent) {
        if (studentEditor.getAttendance() != null) {
            assert studentEditor.getAssignment() == null : "Assignment should not be marked if attendance is marked";
            return String.format(MESSAGE_MARK_SINGLE_SUCCESS_ATTENDACE,
                    studentEditor.getAttendance().getAttendanceString(),
                    editedStudent);
        } else {
            assert studentEditor.getAssignment() == null : "Attendance should not be marked if assignment is marked";
            Assignment assignment = studentEditor.getAssignment();
            return String.format(MESSAGE_MARK_SINGLE_SUCCESS_ASSIGNMENT,
                    assignment.getAssignmentName(),
                    editedStudent.getName(),
                    assignment.getAssignmentString(),
                    editedStudent);
        }
    }

    @Override
    public String getMultiEditSuccessMessage(List<Student> editedStudents) {
        if (studentEditor.getAttendance() != null) {
            assert studentEditor.getAssignment() == null : "Assignment should not be marked if attendance is marked";
            return String.format(MESSAGE_MARK_MULTI_SUCCESS_ATTENDACE,
                    editedStudents.size(), studentEditor.getAttendance());
        } else {
            assert studentEditor.getAssignment() == null : "Attendance should not be marked if assignment is marked";
            Assignment assignment = studentEditor.getAssignment();
            return String.format(MESSAGE_MARK_MULTI_SUCCESS_ASSIGNMENT,
                    assignment.getAssignmentName(),
                    assignment.getAssignmentString(),
                    editedStudents.size());
        }
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
        private final Assignment assignment;

        /**
         * Constructor using Attendance.
         *
         * @param attendance Attendance to edit the student with.
         */
        public MarkCommandStudentEditor(Attendance attendance) {
            this.assignment = null;
            this.attendance = attendance;
        }

        /**
         * Constructor using Assignment.
         *
         * @param assignment Assignment to edit the student with.
         */
        public MarkCommandStudentEditor(Assignment assignment) {
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
                newAttendance.add(attendance);
                studentData.setAttendances(newAttendance);
            } else if (assignment != null) {
                Set<Assignment> newAssignments = new HashSet<>(studentToEdit.getAssignments());
                newAssignments.remove(assignment);
                newAssignments.add(assignment);
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

            if (getAssignment() == null && e.getAssignment() != null) {
                return false;
            } else if (getAssignment() == null && e.getAssignment() == null) {
                return true;
            }

            return getAttendance().equals(e.getAttendance()) && getAssignment().equals(e.getAssignment());
        }
    }
}

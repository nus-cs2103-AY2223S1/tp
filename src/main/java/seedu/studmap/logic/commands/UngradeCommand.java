package seedu.studmap.logic.commands;

import static seedu.studmap.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.logic.commands.commons.StudentEditor;
import seedu.studmap.model.student.Assignment;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.StudentData;

/**
 * Remove the specified assignment from the student identified using its displayed index.
 */
public class UngradeCommand extends EditStudentCommand<UngradeCommand.UngradeCommandStudentEditor> {

    public static final String COMMAND_WORD = "ungrade";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Remove the assignment for student identified by the index number used in the displayed student list."
            + "\nRemoves the specified assignment.\n"
            + "Parameters: INDEX (must be positive integer) "
            + PREFIX_ASSIGNMENT + "ASSIGNMENT"
            + "\nExample: " + COMMAND_WORD + " 1 " + PREFIX_ASSIGNMENT + " A01"
            + "\nExample: " + COMMAND_WORD + " all " + PREFIX_ASSIGNMENT + " A02";

    public static final String MESSAGE_UNGRADE_SINGLE_ASSIGNMENT_SUCCESS = "Removed Assignment %1$s from Student: %2$s";

    public static final String MESSAGE_UNGRADE_MULTI_ASSIGNMENT_SUCCESS = "Removed Assignment %1$s from %2$s Students";

    public static final String MESSAGE_UNGRADE_ASSIGNMENT_NOTFOUND = "Assignment %1$s not found in Student: %2$s";

    public static final String MESSAGE_NO_EDIT = "Assignment must be provided.";

    public UngradeCommand(IndexListGenerator indexListGenerator, UngradeCommandStudentEditor studentEditor) {
        super(indexListGenerator, studentEditor);
    }

    @Override
    public String getSingleEditSuccessMessage(Student editedStudent) {
        Assignment assignment = studentEditor.getAssignment();
        return String.format(MESSAGE_UNGRADE_SINGLE_ASSIGNMENT_SUCCESS,
                assignment.getAssignmentName(),
                editedStudent);
    }

    @Override
    public String getMultiEditSuccessMessage(List<Student> editedStudents) {
        Assignment assignment = studentEditor.getAssignment();
        return String.format(MESSAGE_UNGRADE_MULTI_ASSIGNMENT_SUCCESS,
                assignment.getAssignmentName(),
                editedStudents.size());
    }

    @Override
    public String getNoEditMessage() {
        return MESSAGE_NO_EDIT;
    }

    /**
     * A static StudentEditor that adjusts Assignment for a given Student.
     */
    public static class UngradeCommandStudentEditor implements StudentEditor {

        private final Assignment assignment;

        /**
         * Constructor using Assignment.
         *
         * @param assignment Assignment to edit the student with.
         */
        public UngradeCommandStudentEditor(Assignment assignment) {
            this.assignment = assignment;
        }

        public Assignment getAssignment() {
            return assignment;
        }


        @Override
        public Student editStudent(Student studentToEdit) {
            StudentData studentData = studentToEdit.getStudentData();

            Set<Assignment> newAssignments = new HashSet<>(studentToEdit.getAssignments());
            newAssignments.remove(assignment);
            studentData.setAssignments(newAssignments);

            return new Student(studentData);
        }

        @Override
        public boolean hasEdits() {
            return assignment != null;
        }

        @Override
        public boolean equals(Object other) {

            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UngradeCommand.UngradeCommandStudentEditor)) {
                return false;
            }

            // state check
            UngradeCommand.UngradeCommandStudentEditor e = (UngradeCommand.UngradeCommandStudentEditor) other;

            if (getAssignment() == null && e.getAssignment() != null) {
                return false;
            } else if (getAssignment() == null && e.getAssignment() == null) {
                return true;
            }

            return getAssignment().equals(e.getAssignment());
        }
    }
}

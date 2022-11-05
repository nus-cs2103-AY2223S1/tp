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
 * Specifies the state of assignment for a student.
 */
public class GradeCommand extends EditStudentCommand<GradeCommand.GradeCommandStudentEditor> {

    public static final String COMMAND_WORD = "grade";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Specifies the assignment status for a student identified by the index number used in the displayed"
            + " student list.\nThis commands creates a new assignment for the student if it does not exist.\n"
            + "Grades the status for the assignment specified in the parameter.\n"
            + "Parameters: INDEX (must be positive integer or use \"all\" to mark everyone currently displayed)"
            + " OPTION (must be marked/received/new where 'new'"
            + " also represents a new assignment that is not yet received) "
            + PREFIX_ASSIGNMENT + "ASSIGNMENT\n"
            + "Example: " + COMMAND_WORD + " 1 new " + PREFIX_ASSIGNMENT + "A01\n"
            + "Example: " + COMMAND_WORD + " all marked " + PREFIX_ASSIGNMENT + "A07";

    public static final String MESSAGE_GRADE_SINGLE_SUCCESS_ASSIGNMENT =
            "Set assignment %1$s for student %2$s as %3$s:" + " \n%4$s";
    public static final String MESSAGE_GRADE_MULTI_SUCCESS_ASSIGNMENT =
            "Set assignment %1$s as %2$s for %3$s students: ";

    public static final String MESSAGE_NO_EDIT = "Assignment must be provided.";

    public GradeCommand(IndexListGenerator indexListGenerator, GradeCommandStudentEditor studentEditor) {
        super(indexListGenerator, studentEditor);
    }

    @Override
    public String getSingleEditSuccessMessage(Student editedStudent) {
        Assignment assignment = studentEditor.getAssignment();
        return String.format(MESSAGE_GRADE_SINGLE_SUCCESS_ASSIGNMENT,
                assignment.getAssignmentName(),
                editedStudent.getName(),
                assignment.getAssignmentString(),
                editedStudent);
    }

    @Override
    public String getMultiEditSuccessMessage(List<Student> editedStudents) {
        Assignment assignment = studentEditor.getAssignment();
        return String.format(MESSAGE_GRADE_MULTI_SUCCESS_ASSIGNMENT,
                assignment.getAssignmentName(),
                assignment.getAssignmentString(),
                editedStudents.size());
    }

    @Override
    public String getNoEditMessage() {
        return MESSAGE_NO_EDIT;
    }

    /**
     * A static StudentEditor that adjusts Assignment for a given Student.
     */
    public static class GradeCommandStudentEditor implements StudentEditor {

        private final Assignment assignment;

        /**
         * Constructor using Assignment.
         *
         * @param assignment Assignment to edit the student with.
         */
        public GradeCommandStudentEditor(Assignment assignment) {
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
            newAssignments.add(assignment);
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
            if (!(other instanceof GradeCommand.GradeCommandStudentEditor)) {
                return false;
            }

            // state check
            GradeCommand.GradeCommandStudentEditor e = (GradeCommand.GradeCommandStudentEditor) other;

            if (getAssignment() == null && e.getAssignment() != null) {
                return false;
            } else if (getAssignment() == null && e.getAssignment() == null) {
                return true;
            }

            return getAssignment().equals(e.getAssignment());
        }
    }
}

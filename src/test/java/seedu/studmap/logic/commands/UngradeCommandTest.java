package seedu.studmap.logic.commands;

import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.studmap.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;
import static seedu.studmap.testutil.TypicalStudents.getTypicalStudMap;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.studmap.commons.core.Messages;
import seedu.studmap.commons.core.index.Index;
import seedu.studmap.commons.core.index.SingleIndexGenerator;
import seedu.studmap.model.Model;
import seedu.studmap.model.ModelManager;
import seedu.studmap.model.UserPrefs;
import seedu.studmap.model.student.Assignment;
import seedu.studmap.model.student.Student;
import seedu.studmap.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UngradeCommand}.
 */
class UngradeCommandTest {

    private Model model = new ModelManager(getTypicalStudMap(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredListAssignment_success() {
        Student studentToUnmark = model.getFilteredStudentList().get(INDEX_THIRD_STUDENT.getZeroBased());
        Assignment assignment = new Assignment("A01", Assignment.Status.NEW);
        UngradeCommand ungradeCommand = new UngradeCommand(new SingleIndexGenerator(INDEX_THIRD_STUDENT),
                new UngradeCommand.UngradeCommandStudentEditor(assignment));

        Set<Assignment> assignmentSet = new HashSet<>(studentToUnmark.getAssignments());
        assignmentSet.remove(assignment);
        Student unmarkedStudent = new StudentBuilder(studentToUnmark).setAssigned(assignmentSet).build();

        String expectedMessage = String.format(UngradeCommand.MESSAGE_UNGRADE_SINGLE_ASSIGNMENT_SUCCESS,
                assignment.getAssignmentName(), unmarkedStudent);

        ModelManager expectedModel = new ModelManager(model.getStudMap(), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList()
                .get(INDEX_THIRD_STUDENT.getZeroBased()), unmarkedStudent);
        assertCommandSuccess(ungradeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_THIRD_STUDENT);

        Student studentInFilteredList = model.getFilteredStudentList().get(0);
        Assignment assignment = new Assignment("A01", Assignment.Status.NEW);
        UngradeCommand ungradeCommand = new UngradeCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT),
                new UngradeCommand.UngradeCommandStudentEditor(assignment));

        Set<Assignment> assignmentSet = new HashSet<>(studentInFilteredList.getAssignments());
        assignmentSet.remove(assignment);
        Student unmarkedStudent = new StudentBuilder(studentInFilteredList).setAssigned(assignmentSet).build();

        String expectedMessage = String.format(UngradeCommand.MESSAGE_UNGRADE_SINGLE_ASSIGNMENT_SUCCESS,
                assignment.assignmentName, unmarkedStudent);

        ModelManager expectedModel = new ModelManager(model.getStudMap(), new UserPrefs());
        showStudentAtIndex(expectedModel, INDEX_THIRD_STUDENT);
        expectedModel.setStudent(model.getFilteredStudentList().get(0), unmarkedStudent);
        assertCommandSuccess(ungradeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidstudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        Assignment assignment = new Assignment("A04", Assignment.Status.NEW);
        UngradeCommand ungradeCommand = new UngradeCommand(new SingleIndexGenerator(outOfBoundIndex),
                new UngradeCommand.UngradeCommandStudentEditor(assignment));

        assertCommandFailure(ungradeCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }
}

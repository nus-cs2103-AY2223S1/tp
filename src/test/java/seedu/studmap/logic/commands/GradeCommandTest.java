package seedu.studmap.logic.commands;

import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.studmap.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.studmap.testutil.TypicalStudents.getTypicalStudMap;

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
 * {@code GradeCommand}.
 */
class GradeCommandTest {

    private Model model = new ModelManager(getTypicalStudMap(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToMark = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Assignment assignment = new Assignment("A123", Assignment.Status.MARKED);
        GradeCommand gradeCommand = new GradeCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT),
                new GradeCommand.GradeCommandStudentEditor(assignment));

        Student markedStudent = new StudentBuilder(studentToMark)
                .addAssignedMarked("A123").build();

        String expectedMessage = String.format(GradeCommand.MESSAGE_GRADE_SINGLE_SUCCESS_ASSIGNMENT,
                assignment.getAttributeName(),
                markedStudent.getName(),
                assignment.state,
                markedStudent);

        ModelManager expectedModel = new ModelManager(model.getStudMap(), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), markedStudent);
        assertCommandSuccess(gradeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Assignment assignment = new Assignment("A123", Assignment.Status.MARKED);
        GradeCommand gradeCommand = new GradeCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT),
                new GradeCommand.GradeCommandStudentEditor(assignment));

        Student markedStudent = new StudentBuilder(studentInFilteredList)
                .addAssignedMarked("A123").build();

        String expectedMessage = String.format(GradeCommand.MESSAGE_GRADE_SINGLE_SUCCESS_ASSIGNMENT,
                assignment.getAttributeName(),
                markedStudent.getName(),
                assignment.state,
                markedStudent);

        ModelManager expectedModel = new ModelManager(model.getStudMap(), new UserPrefs());
        showStudentAtIndex(expectedModel, INDEX_FIRST_STUDENT);
        expectedModel.setStudent(model.getFilteredStudentList().get(0), markedStudent);
        assertCommandSuccess(gradeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidstudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        Assignment assignment = new Assignment("A123", Assignment.Status.MARKED);
        GradeCommand gradeCommand = new GradeCommand(new SingleIndexGenerator(outOfBoundIndex),
                new GradeCommand.GradeCommandStudentEditor(assignment));

        assertCommandFailure(gradeCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_markedNoEdit_success() {

        // student not edited if already marked

        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Assignment assignment = new Assignment("A123", Assignment.Status.MARKED);
        Student studentToMark = new StudentBuilder(studentInFilteredList).addAssignments(assignment).build();
        model.setStudent(studentInFilteredList, studentToMark);

        GradeCommand gradeCommand = new GradeCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT),
                new GradeCommand.GradeCommandStudentEditor(assignment));
        String expectedMessage = String.format(GradeCommand.MESSAGE_GRADE_SINGLE_UNEDITED_ASSIGNMENT,
                assignment.getAttributeName(),
                studentToMark.getName(),
                assignment.state,
                studentToMark);

        ModelManager expectedModel = new ModelManager(model.getStudMap(), new UserPrefs());
        showStudentAtIndex(expectedModel, INDEX_FIRST_STUDENT);
        expectedModel.setStudent(model.getFilteredStudentList().get(0), studentToMark);
        assertCommandSuccess(gradeCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_newNoEdit_success() {

        // student not edited if already marked

        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Assignment assignment = new Assignment("A123", Assignment.Status.NEW);
        Student studentToMark = new StudentBuilder(studentInFilteredList).addAssignments(assignment).build();
        model.setStudent(studentInFilteredList, studentToMark);

        GradeCommand gradeCommand = new GradeCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT),
                new GradeCommand.GradeCommandStudentEditor(assignment));
        String expectedMessage = String.format(GradeCommand.MESSAGE_GRADE_SINGLE_UNEDITED_ASSIGNMENT,
                assignment.getAttributeName(),
                studentToMark.getName(),
                assignment.state,
                studentToMark);

        ModelManager expectedModel = new ModelManager(model.getStudMap(), new UserPrefs());
        showStudentAtIndex(expectedModel, INDEX_FIRST_STUDENT);
        expectedModel.setStudent(model.getFilteredStudentList().get(0), studentToMark);
        assertCommandSuccess(gradeCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_receivedNoEdit_success() {

        // student not edited if already marked

        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Assignment assignment = new Assignment("A123", Assignment.Status.RECEIVED);
        Student studentToMark = new StudentBuilder(studentInFilteredList).addAssignments(assignment).build();
        model.setStudent(studentInFilteredList, studentToMark);

        GradeCommand gradeCommand = new GradeCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT),
                new GradeCommand.GradeCommandStudentEditor(assignment));
        String expectedMessage = String.format(GradeCommand.MESSAGE_GRADE_SINGLE_UNEDITED_ASSIGNMENT,
                assignment.getAttributeName(),
                studentToMark.getName(),
                assignment.state,
                studentToMark);

        ModelManager expectedModel = new ModelManager(model.getStudMap(), new UserPrefs());
        showStudentAtIndex(expectedModel, INDEX_FIRST_STUDENT);
        expectedModel.setStudent(model.getFilteredStudentList().get(0), studentToMark);
        assertCommandSuccess(gradeCommand, model, expectedMessage, expectedModel);

    }
}

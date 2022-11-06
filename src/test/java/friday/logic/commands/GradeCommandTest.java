package friday.logic.commands;

import static friday.logic.commands.CommandTestUtil.assertCommandFailure;
import static friday.logic.commands.CommandTestUtil.assertCommandSuccess;
import static friday.logic.commands.CommandTestUtil.showStudentAtIndex;
import static friday.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static friday.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static friday.testutil.TypicalStudents.getTypicalFriday;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import friday.commons.core.Messages;
import friday.commons.core.index.Index;
import friday.logic.commands.GradeCommand.EditGradeDescriptor;
import friday.model.Model;
import friday.model.ModelManager;
import friday.model.UserPrefs;
import friday.model.student.Student;
import friday.testutil.EditGradeDescriptorBuilder;
import friday.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for GradeCommand.
 */
public class GradeCommandTest {

    private static final String GRADE_STUB = "99.99";

    private Model model = new ModelManager(getTypicalFriday(), new UserPrefs());

    @Test
    public void execute_allGradesSpecifiedUnfilteredList_success() {
        Student editedStudent = model.getStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        EditGradeDescriptor descriptor = new EditGradeDescriptorBuilder(editedStudent.getGradesList()).build();
        GradeCommand gradeCommand = new GradeCommand(INDEX_FIRST_STUDENT, descriptor);

        String expectedMessage = String.format(GradeCommand.MESSAGE_EDIT_GRADE_SUCCESS, editedStudent);

        ModelManager expectedModel = new ModelManager(model.getFriday(), new UserPrefs());
        expectedModel.setStudent(model.getStudentList().get(0), editedStudent);

        assertCommandSuccess(gradeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someGradesSpecifiedUnfilteredList_success() {
        Student firstStudent = model.getStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(firstStudent).withGradesList("Midterm", GRADE_STUB)
                .build();
        EditGradeDescriptor descriptor = new EditGradeDescriptorBuilder()
                .withGrade("Midterm", GRADE_STUB).build();
        GradeCommand gradeCommand = new GradeCommand(INDEX_FIRST_STUDENT, descriptor);

        String expectedMessage = String.format(GradeCommand.MESSAGE_EDIT_GRADE_SUCCESS, editedStudent);

        ModelManager expectedModel = new ModelManager(model.getFriday(), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(gradeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getStudentList().size() + 1);
        EditGradeDescriptor descriptor = new EditGradeDescriptorBuilder().build();
        GradeCommand gradeCommand = new GradeCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(gradeCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of friday list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFriday().getStudentList().size());

        EditGradeDescriptor descriptor = new EditGradeDescriptorBuilder().build();
        GradeCommand gradeCommand = new GradeCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(gradeCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        EditGradeDescriptor emptyDescriptor = new EditGradeDescriptor();
        EditGradeDescriptor descriptor = new EditGradeDescriptorBuilder(emptyDescriptor)
                .withGrade("RA1", GRADE_STUB).build();
        final GradeCommand standardCommand = new GradeCommand(INDEX_FIRST_STUDENT, descriptor);

        // same values -> returns true
        GradeCommand commandWithSameValues = new GradeCommand(INDEX_FIRST_STUDENT, descriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new GradeCommand(INDEX_SECOND_STUDENT, descriptor)));

        // different remark -> returns false
        EditGradeDescriptor otherDescriptor = new EditGradeDescriptorBuilder().withGrade("RA2", GRADE_STUB)
                .build();
        assertFalse(standardCommand.equals(new GradeCommand(INDEX_FIRST_STUDENT, otherDescriptor)));
    }
}

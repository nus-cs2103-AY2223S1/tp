package friday.logic.commands;

import static friday.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static friday.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
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
import friday.model.Friday;
import friday.model.Model;
import friday.model.ModelManager;
import friday.model.UserPrefs;
import friday.model.student.Remark;
import friday.model.student.Student;
import friday.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class RemarkCommandTest {

    private static final String REMARK_STUB = "Some remark";

    private Model model = new ModelManager(getTypicalFriday(), new UserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Student firstStudent = model.getStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(firstStudent).withRemark(REMARK_STUB).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_STUDENT,
                new Remark(editedStudent.getRemark().value));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new Friday(model.getFriday()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteRemarkUnfilteredList_success() {
        Student firstStudent = model.getStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(firstStudent).withRemark("").build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_STUDENT,
                new Remark(editedStudent.getRemark().toString()));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_DELETE_REMARK_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new Friday(model.getFriday()), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student firstStudent = model.getStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(model.getStudentList()
                .get(INDEX_FIRST_STUDENT.getZeroBased()))
                .withRemark(REMARK_STUB).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_STUDENT,
                new Remark(editedStudent.getRemark().value));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedStudent);

        assertCommandSuccess(remarkCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getStudentList().size() + 1);
        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, new Remark(VALID_REMARK_BOB));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFriday().getStudentList().size());

        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, new Remark(VALID_REMARK_BOB));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final RemarkCommand standardCommand = new RemarkCommand(INDEX_FIRST_STUDENT,
                new Remark(VALID_REMARK_AMY));

        // same values -> returns true
        RemarkCommand commandWithSameValues = new RemarkCommand(INDEX_FIRST_STUDENT,
                new Remark(VALID_REMARK_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(INDEX_SECOND_STUDENT,
                new Remark(VALID_REMARK_AMY))));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(INDEX_FIRST_STUDENT,
                new Remark(VALID_REMARK_BOB))));
    }
}

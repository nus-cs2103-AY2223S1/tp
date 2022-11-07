package seedu.classify.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.classify.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.classify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.classify.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.classify.testutil.TypicalStudents.getTypicalStudentRecord;

import org.junit.jupiter.api.Test;

import seedu.classify.commons.core.Messages;
import seedu.classify.model.Model;
import seedu.classify.model.ModelManager;
import seedu.classify.model.UserPrefs;
import seedu.classify.model.student.Id;
import seedu.classify.model.student.IdPredicate;
import seedu.classify.model.student.Name;
import seedu.classify.model.student.NamePredicate;
import seedu.classify.model.student.Student;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalStudentRecord(), new UserPrefs());

    @Test
    public void execute_validNameUnfilteredList_success() {
        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Name studentNameToDelete = studentToDelete.getStudentName();
        DeleteCommand deleteCommand = new DeleteCommand(studentNameToDelete, new NamePredicate(studentNameToDelete));

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, studentToDelete);

        ModelManager expectedModel = new ModelManager(model.getStudentRecord(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIdUnfilteredList_success() {
        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Id studentIdToDelete = studentToDelete.getId();
        DeleteCommand deleteCommand = new DeleteCommand(studentIdToDelete, new IdPredicate(studentIdToDelete));

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, studentToDelete);

        ModelManager expectedModel = new ModelManager(model.getStudentRecord(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameUnfilteredList_throwsCommandException() {
        Name invalidName = new Name("Harry Styles");
        DeleteCommand deleteCommand = new DeleteCommand(invalidName, new NamePredicate(invalidName));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_STUDENT_NAME);
    }

    @Test
    public void execute_invalidIdUnfilteredList_throwsCommandException() {
        Id invalidId = new Id("999Z");
        DeleteCommand deleteCommand = new DeleteCommand(invalidId, new IdPredicate(invalidId));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_STUDENT_ID);
    }

    @Test
    public void equals() {
        Student firstStudentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Name studentNameToDelete = firstStudentToDelete.getStudentName();
        DeleteCommand deleteFirstCommand = new DeleteCommand(studentNameToDelete,
                new NamePredicate(studentNameToDelete));

        Student secondStudentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Id studentIdToDelete = secondStudentToDelete.getId();
        DeleteCommand deleteSecondCommand = new DeleteCommand(studentIdToDelete, new IdPredicate(studentIdToDelete));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(studentNameToDelete,
                new NamePredicate(studentNameToDelete));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredStudentList(p -> false);

        assertTrue(model.getFilteredStudentList().isEmpty());
    }
}

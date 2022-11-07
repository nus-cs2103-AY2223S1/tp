package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Response;
import seedu.address.model.student.Student;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddResponseCommand}.
 */
public class AddResponseCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Response dummyResponse = new Response("7");

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToAddResponse = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        AddResponseCommand addResponseCommand = new AddResponseCommand(INDEX_FIRST_STUDENT, dummyResponse);
        Student editedStudent = new Student(studentToAddResponse.getName(),
                studentToAddResponse.getTelegram(), studentToAddResponse.getEmail(),
                dummyResponse, studentToAddResponse.getAttendance(), studentToAddResponse.getHelpTag());

        String expectedMessage = String.format(addResponseCommand.MESSAGE_ADDRESPONSE_SUCCESS
                + studentToAddResponse.getName().toString());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setStudent(studentToAddResponse, editedStudent);

        assertCommandSuccess(addResponseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        AddResponseCommand addResponseCommand = new AddResponseCommand(outOfBoundIndex, dummyResponse);

        assertCommandFailure(addResponseCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AddResponseCommand addResponseFirstCommand = new AddResponseCommand(INDEX_FIRST_STUDENT, dummyResponse);
        AddResponseCommand addResponseSecondCommand = new AddResponseCommand(INDEX_SECOND_STUDENT, dummyResponse);

        // same object -> returns true
        assertTrue(addResponseFirstCommand.equals(addResponseFirstCommand));

        // same values -> returns true
        AddResponseCommand addResponseFirstCommandCopy = new AddResponseCommand(INDEX_FIRST_STUDENT, dummyResponse);
        assertTrue(addResponseFirstCommand.equals(addResponseFirstCommandCopy));

        // different types -> returns false
        assertFalse(addResponseFirstCommand.equals(1));

        // null -> returns false
        assertFalse(addResponseFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(addResponseFirstCommand.equals(addResponseSecondCommand));
    }


}

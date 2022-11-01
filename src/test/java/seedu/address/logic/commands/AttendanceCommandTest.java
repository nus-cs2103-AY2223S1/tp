package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class AttendanceCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void execute_invalidIndexPerson_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AttendanceCommand attendanceCommand = new AttendanceCommand(outOfBoundIndex, "0/0");

        assertCommandFailure(attendanceCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_notStudent_throwsCommandException() {
        Index nonStudentTIndex = Index.fromOneBased(1);
        AttendanceCommand attendanceCommand = new AttendanceCommand(nonStudentTIndex, "0/0");

        assertCommandFailure(attendanceCommand, model, AttendanceCommand.MESSAGE_PERSON_NOT_STUDENT);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AttendanceCommand attendanceCommand = new AttendanceCommand(outOfBoundIndex, "0/0");

        assertCommandFailure(attendanceCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AttendanceCommand attendanceFirstCommand = new AttendanceCommand(INDEX_FIRST_PERSON, "0/0");
        AttendanceCommand attendanceSecondCommand = new AttendanceCommand(INDEX_SECOND_PERSON, "0/0");

        // same object -> returns true
        assertTrue(attendanceFirstCommand.equals(attendanceFirstCommand));

        // same values -> returns true
        AttendanceCommand attendanceFirstCommandCopy = new AttendanceCommand(INDEX_FIRST_PERSON, "0/0");
        assertTrue(attendanceFirstCommand.equals(attendanceFirstCommandCopy));

        // different types -> returns false
        assertFalse(attendanceFirstCommand.equals(1));

        // null -> returns false
        assertFalse(attendanceFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(attendanceFirstCommand.equals(attendanceSecondCommand));
    }

}

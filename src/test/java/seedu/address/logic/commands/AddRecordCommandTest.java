package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AddRecordCommand.MESSAGE_DUPLICATE_RECORD;
import static seedu.address.logic.commands.AddRecordCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRecords.RECORD1;
import static seedu.address.testutil.TypicalRecords.RECORD2;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code AddRecordCommand}.
 */
public class AddRecordCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        AddRecordCommand addFirstCommand = new AddRecordCommand(RECORD1);
        AddRecordCommand addSecondCommand = new AddRecordCommand(RECORD2);

        // same object -> returns true
        assertTrue(addFirstCommand.equals(addFirstCommand));

        // same values -> returns true
        AddRecordCommand addFirstCommandCopy = new AddRecordCommand(RECORD1);
        assertTrue(addFirstCommand.equals(addFirstCommandCopy));

        // different types -> returns false
        assertFalse(addFirstCommand.equals(1));

        // null -> returns false
        assertFalse(addFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(addFirstCommand.equals(addSecondCommand));
    }

    @Test
    public void execute_duplicateRecord_exceptionThrown() {
        model.setFilteredRecordList(GEORGE); // Typical Person with existing RECORD1 in record list
        model.setRecordListDisplayed(true);

        AddRecordCommand addRecordCommand = new AddRecordCommand(RECORD1);

        assertCommandFailure(addRecordCommand, model, MESSAGE_DUPLICATE_RECORD);
    }

    @Test
    public void execute_addRecord_success() {
        model.setFilteredRecordList(GEORGE); // Typical Person with existing RECORD1 in record list
        model.setRecordListDisplayed(true);

        expectedModel.setFilteredRecordList(GEORGE); // Typical Person with existing RECORD1 in record list
        expectedModel.setRecordListDisplayed(true);

        AddRecordCommand addRecordCommand = new AddRecordCommand(RECORD2);

        assertCommandSuccess(addRecordCommand, model, String.format(MESSAGE_SUCCESS, RECORD2), expectedModel);
    }
}

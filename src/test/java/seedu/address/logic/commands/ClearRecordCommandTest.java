package seedu.address.logic.commands;

import static seedu.address.logic.commands.Command.MESSAGE_RECORD_COMMAND_PREREQUISITE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class ClearRecordCommandTest {

    @Test
    public void execute_clearEmptyRecordsWithoutListing_failure() {
        Model model = new ModelManager();

        assertCommandFailure(new ClearRecordCommand(), model, MESSAGE_RECORD_COMMAND_PREREQUISITE);
    }

    @Test
    public void execute_clearRecordsWithoutListing_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandFailure(new ClearRecordCommand(), model, MESSAGE_RECORD_COMMAND_PREREQUISITE);
    }

    @Test
    public void execute_clearRecords_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        model.setFilteredRecordList(BENSON);
        model.setRecordListDisplayed(true);

        expectedModel.setFilteredRecordList(BENSON);
        expectedModel.setRecordListDisplayed(true);
        expectedModel.clearRecords();

        assertCommandSuccess(new ClearRecordCommand(), model, ClearRecordCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_clearRecordsEmptyRecords_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        model.setFilteredRecordList(ALICE);
        model.setRecordListDisplayed(true);

        expectedModel.setFilteredRecordList(ALICE);
        expectedModel.setRecordListDisplayed(true);
        expectedModel.clearRecords();

        assertCommandSuccess(new ClearRecordCommand(), model, ClearRecordCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
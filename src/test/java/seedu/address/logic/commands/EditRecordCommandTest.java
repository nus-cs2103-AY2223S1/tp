package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_RECORD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_DATA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_DATE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showRecordAtIndex;
import static seedu.address.testutil.TestUtil.prepareModel;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.address.testutil.TypicalIndexes.SECOND_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditRecordCommand.EditRecordDescriptor;
import seedu.address.model.Model;
import seedu.address.model.record.Record;
import seedu.address.testutil.EditRecordDescriptorBuilder;
import seedu.address.testutil.RecordBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditRecordCommand.
 */
public class EditRecordCommandTest {

    private Model model = prepareModel();
    private Model expectedModel = prepareModel();

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastRecord = Index.fromOneBased(model.getFilteredRecordList().size());
        Record lastRecord = model.getFilteredRecordList().get(indexLastRecord.getZeroBased());

        RecordBuilder recordInList = new RecordBuilder(lastRecord);
        Record editedRecord = recordInList.withRecordDate(VALID_RECORD_DATE).withRecordData(VALID_RECORD_DATA).build();

        EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder()
                .withDate(VALID_RECORD_DATE).withData(VALID_RECORD_DATA).build();
        EditRecordCommand editRecordCommand = new EditRecordCommand(indexLastRecord, descriptor);

        String expectedMessage = String.format(EditRecordCommand.MESSAGE_EDIT_RECORD_SUCCESS, editedRecord);

        expectedModel.setRecord(model.getFilteredRecordList().get(indexLastRecord.getZeroBased()), editedRecord);
        assertCommandSuccess(editRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditRecordCommand editRecordCommand = new EditRecordCommand(FIRST_INDEX, new EditRecordDescriptor());
        Record editedRecord = model.getFilteredRecordList().get(FIRST_INDEX.getZeroBased());

        String expectedMessage = String.format(EditRecordCommand.MESSAGE_EDIT_RECORD_SUCCESS, editedRecord);

        assertCommandSuccess(editRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showRecordAtIndex(model, FIRST_INDEX);

        Record recordInFilteredList = model.getFilteredRecordList().get(FIRST_INDEX.getZeroBased());
        Record editedRecord = new RecordBuilder(recordInFilteredList).withRecordData(VALID_RECORD_DATA).build();
        EditRecordCommand editRecordCommand = new EditRecordCommand(FIRST_INDEX,
                new EditRecordDescriptorBuilder().withData(VALID_RECORD_DATA).build());

        String expectedMessage = String.format(EditRecordCommand.MESSAGE_EDIT_RECORD_SUCCESS, editedRecord);

        expectedModel.setRecord(model.getFilteredRecordList().get(0), editedRecord);

        assertCommandSuccess(editRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateRecordUnfilteredList_failure() {
        Record firstRecord = model.getFilteredRecordList().get(FIRST_INDEX.getZeroBased());
        EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder(firstRecord).build();
        EditRecordCommand editRecordCommand = new EditRecordCommand(SECOND_INDEX, descriptor);

        assertCommandFailure(editRecordCommand, model, EditRecordCommand.MESSAGE_DUPLICATE_RECORD);
    }

    @Test
    public void equals() {
        final EditRecordCommand standardCommand = new EditRecordCommand(FIRST_INDEX, DESC_RECORD);

        // same values -> returns true
        EditRecordDescriptor copyDescriptor = new EditRecordDescriptor(DESC_RECORD);
        EditRecordCommand commandWithSameValues = new EditRecordCommand(FIRST_INDEX, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditRecordCommand(SECOND_INDEX, DESC_RECORD)));
    }
}

package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DINNER;
import static seedu.address.logic.commands.CommandTestUtil.LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMT_TUITION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TUITION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_TUITION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_PERSONAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_EXPENDITURE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showExpenditureAtIndex;
import static seedu.address.testutil.TypicalEntry.getTypicalPennyWise;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditEntryDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PennyWise;
import seedu.address.model.UserPrefs;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.EntryType;
import seedu.address.testutil.EditEntryDescriptorBuilder;
import seedu.address.testutil.ExpenditureBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model;

    @BeforeEach
    public void setModel() {
        model = new ModelManager(getTypicalPennyWise(), new UserPrefs());
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Index indexLastEntry = Index.fromOneBased(model.getFilteredExpenditureList().size());
        Entry lastEntry = model.getFilteredExpenditureList().get(indexLastEntry.getZeroBased());

        ExpenditureBuilder expenditureInList = new ExpenditureBuilder(lastEntry);

        Entry editedEntry = expenditureInList
                .withDescription(VALID_DESC_TUITION)
                .withAmount(VALID_AMT_TUITION)
                .withTag(VALID_TAG_PERSONAL)
                .withDate(VALID_DATE_TUITION)
                .build();
        // Specify all fields to be edited
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder()
                .withType(VALID_TYPE_EXPENDITURE)
                .withDescription(VALID_DESC_TUITION)
                .withAmount(VALID_AMT_TUITION)
                .withTag(VALID_TAG_PERSONAL)
                .withDate(VALID_DATE_TUITION)
                .build();
        EditCommand editCommand = new EditCommand(indexLastEntry, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry);

        Model expectedModel = new ModelManager(new PennyWise(model.getPennyWise()), new UserPrefs());
        expectedModel.setExpenditure(lastEntry, editedEntry);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastEntry = Index.fromOneBased(model.getFilteredExpenditureList().size());
        Entry lastEntry = model.getFilteredExpenditureList().get(indexLastEntry.getZeroBased());

        ExpenditureBuilder expenditureInList = new ExpenditureBuilder(lastEntry);

        Entry editedEntry = expenditureInList
                .withDescription(VALID_DESC_TUITION)
                .withAmount(VALID_AMT_TUITION)
                .build();

        // Specify only the description and amount field to be updated
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder()
                .withType(VALID_TYPE_EXPENDITURE)
                .withDescription(VALID_DESC_TUITION)
                .withAmount(VALID_AMT_TUITION)
                .build();

        EditCommand editCommand = new EditCommand(indexLastEntry, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry);

        Model expectedModel = new ModelManager(new PennyWise(model.getPennyWise()), new UserPrefs());
        expectedModel.setExpenditure(lastEntry, editedEntry);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder()
                .withType(EntryType.ENTRY_TYPE_EXPENDITURE)
                .build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENTRY, descriptor);
        Entry editedEntry = model.getFilteredExpenditureList().get(INDEX_FIRST_ENTRY.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry);

        Model expectedModel = new ModelManager(new PennyWise(model.getPennyWise()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showExpenditureAtIndex(model, INDEX_FIRST_ENTRY);

        Entry entryInFilteredList = model.getFilteredExpenditureList().get(INDEX_FIRST_ENTRY.getZeroBased());
        Entry editedEntry = new ExpenditureBuilder(entryInFilteredList)
                .withDescription(VALID_DESC_TUITION)
                .withTag(VALID_TAG_PERSONAL)
                .build();

        EditCommand editCommand = new EditCommand(
                INDEX_FIRST_ENTRY,
                new EditEntryDescriptorBuilder()
                        .withType(VALID_TYPE_EXPENDITURE)
                        .withDescription(VALID_DESC_TUITION)
                        .withTag(VALID_TAG_PERSONAL)
                        .build()
        );

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry);

        Model expectedModel = new ModelManager(new PennyWise(model.getPennyWise()), new UserPrefs());
        expectedModel.setExpenditure(
                model.getFilteredExpenditureList().get(INDEX_FIRST_ENTRY.getZeroBased()),
                editedEntry
        );

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateEntryUnfilteredList_failure() {
        Entry firstEntry = model.getFilteredExpenditureList().get(INDEX_FIRST_ENTRY.getZeroBased());
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder(
                firstEntry, new EntryType(EntryType.ENTRY_TYPE_EXPENDITURE)
        ).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_ENTRY, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ENTRY);
    }

    @Test
    public void execute_duplicateEntryFilteredList_failure() {
        showExpenditureAtIndex(model, INDEX_FIRST_ENTRY);

        // Edit entry in filtered list (i.e. the 1st entry in the entire list) into a duplicate
        // (i.e. the 2nd entry in the entire list) in PennyWise.
        Entry entryInList = model.getPennyWise().getExpenditureList().get(INDEX_SECOND_ENTRY.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENTRY,
                new EditEntryDescriptorBuilder(entryInList, new EntryType(EntryType.ENTRY_TYPE_EXPENDITURE)).build()
        );

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ENTRY);
    }

    @Test
    public void execute_invalidEntryIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExpenditureList().size() + 1);
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder()
                .withType(VALID_TYPE_EXPENDITURE)
                .withDescription(VALID_DESC_TUITION)
                .build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list, but smaller than size of PennyWise.
     */
    @Test
    public void execute_invalidEntryIndexFilteredList_failure() {
        showExpenditureAtIndex(model, INDEX_FIRST_ENTRY);
        Index outOfBoundIndex = INDEX_SECOND_ENTRY;
        // ensures that outOfBoundIndex is still in bounds of PennyWise list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPennyWise().getExpenditureList().size());

        EditCommand editCommand = new EditCommand(
                outOfBoundIndex,
                new EditEntryDescriptorBuilder()
                        .withType(VALID_TYPE_EXPENDITURE)
                        .withDescription(VALID_DESC_TUITION)
                        .build()
        );

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_ENTRY, LUNCH);

        // same values -> returns true
        EditEntryDescriptor copyDescriptor = new EditEntryDescriptor(LUNCH);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_ENTRY, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditCommand(INDEX_SECOND_ENTRY, LUNCH));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditCommand(INDEX_FIRST_ENTRY, DINNER));
    }

}

package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DINNER;
import static seedu.address.logic.commands.CommandTestUtil.LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMT_TUITION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_TUITION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_PERSONAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_EXPENDITURE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showExpenditureAtIndex;
import static seedu.address.testutil.TypicalEntry.getTypicalPennyWise;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

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

    private Model model = new ModelManager(getTypicalPennyWise(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Entry editedEntry = new ExpenditureBuilder().build();
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder(editedEntry, new EntryType("e")).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry);

        Model expectedModel = new ModelManager(new PennyWise(model.getPennyWise()), new UserPrefs());
        expectedModel.setExpenditure(model.getFilteredExpenditureList().get(0), editedEntry);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastEntry = Index.fromOneBased(model.getFilteredExpenditureList().size());
        Entry lastEntry = model.getFilteredExpenditureList().get(indexLastEntry.getZeroBased());

        ExpenditureBuilder expenditureInList = new ExpenditureBuilder(lastEntry);
        Entry editedEntry = expenditureInList.withDescription(VALID_DESC_TUITION)
                .withAmount(VALID_AMT_TUITION).withTags(VALID_TAG_PERSONAL).build();

        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withType(VALID_TYPE_EXPENDITURE)
                .withDescription(VALID_DESC_TUITION).withAmount(VALID_AMT_TUITION).withTags(VALID_TAG_PERSONAL).build();
        EditCommand editCommand = new EditCommand(indexLastEntry, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry);

        Model expectedModel = new ModelManager(new PennyWise(model.getPennyWise()), new UserPrefs());
        expectedModel.setExpenditure(lastEntry, editedEntry);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditEntryDescriptor descriptor = new EditEntryDescriptor();
        descriptor.setTags(null);
        descriptor.setType(new EntryType("e"));
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
        Entry editedEntry = model.getFilteredExpenditureList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry);

        Model expectedModel = new ModelManager(new PennyWise(model.getPennyWise()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showExpenditureAtIndex(model, INDEX_FIRST_PERSON);

        Entry entryInFilteredList = model.getFilteredExpenditureList().get(INDEX_FIRST_PERSON.getZeroBased());
        Entry editedEntry = new ExpenditureBuilder(entryInFilteredList)
                .withDescription(VALID_DESC_TUITION).withTags().build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditEntryDescriptorBuilder().withType(VALID_TYPE_EXPENDITURE)
                        .withDescription(VALID_DESC_TUITION).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry);

        Model expectedModel = new ModelManager(new PennyWise(model.getPennyWise()), new UserPrefs());
        expectedModel.setExpenditure(model.getFilteredExpenditureList().get(0), editedEntry);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Entry firstEntry = model.getFilteredExpenditureList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder(firstEntry, new EntryType("e")).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ENTRY);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showExpenditureAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Entry entryInList = model.getPennyWise().getExpenditureList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditEntryDescriptorBuilder(entryInList, new EntryType("e")).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ENTRY);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExpenditureList().size() + 1);
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withType(VALID_TYPE_EXPENDITURE)
                .withDescription(VALID_DESC_TUITION).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showExpenditureAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPennyWise().getExpenditureList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditEntryDescriptorBuilder().withType(VALID_TYPE_EXPENDITURE)
                        .withDescription(VALID_DESC_TUITION).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, LUNCH);

        // same values -> returns true
        EditEntryDescriptor copyDescriptor = new EditEntryDescriptor(LUNCH);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, LUNCH)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DINNER)));
    }

}

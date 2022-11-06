package seedu.pennywise.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pennywise.logic.commands.CommandTestUtil.DINNER;
import static seedu.pennywise.logic.commands.CommandTestUtil.LUNCH;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_AMT_INVESTMENT;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_AMT_TUITION;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_DATE_INVESTMENT;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_DATE_TUITION;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_DESC_INVESTMENT;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_DESC_TUITION;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_TAG_INVESTMENT;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_TAG_PERSONAL;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_TYPE_EXPENDITURE;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_TYPE_INCOME;
import static seedu.pennywise.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.pennywise.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.pennywise.logic.commands.CommandTestUtil.showExpenditureAtIndex;
import static seedu.pennywise.logic.commands.CommandTestUtil.showIncomeAtIndex;
import static seedu.pennywise.testutil.TypicalEntry.getTypicalPennyWise;
import static seedu.pennywise.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static seedu.pennywise.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.pennywise.commons.core.Messages;
import seedu.pennywise.commons.core.index.Index;
import seedu.pennywise.logic.commands.EditCommand.EditEntryDescriptor;
import seedu.pennywise.model.Model;
import seedu.pennywise.model.ModelManager;
import seedu.pennywise.model.PennyWise;
import seedu.pennywise.model.UserPrefs;
import seedu.pennywise.model.entry.Entry;
import seedu.pennywise.model.entry.EntryType;
import seedu.pennywise.testutil.EditEntryDescriptorBuilder;
import seedu.pennywise.testutil.ExpenditureBuilder;
import seedu.pennywise.testutil.IncomeBuilder;

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
    public void execute_allFieldsSpecifiedUnfilteredExpenditureList_success() {
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
    public void execute_allFieldsSpecifiedUnfilteredIncomeList_success() {
        Index indexLastEntry = Index.fromOneBased(model.getFilteredIncomeList().size());
        Entry lastEntry = model.getFilteredIncomeList().get(indexLastEntry.getZeroBased());

        IncomeBuilder incomeInList = new IncomeBuilder(lastEntry);

        Entry editedEntry = incomeInList
                .withDescription(VALID_DESC_INVESTMENT)
                .withAmount(VALID_AMT_INVESTMENT)
                .withTag(VALID_TAG_INVESTMENT)
                .withDate(VALID_DATE_INVESTMENT)
                .build();
        // Specify all fields to be edited
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder()
                .withType(VALID_TYPE_INCOME)
                .withDescription(VALID_DESC_INVESTMENT)
                .withAmount(VALID_AMT_INVESTMENT)
                .withTag(VALID_TAG_INVESTMENT)
                .withDate(VALID_DATE_INVESTMENT)
                .build();
        EditCommand editCommand = new EditCommand(indexLastEntry, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry);

        Model expectedModel = new ModelManager(new PennyWise(model.getPennyWise()), new UserPrefs());
        expectedModel.setIncome(lastEntry, editedEntry);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredExpenditureList_success() {
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
    public void execute_someFieldsSpecifiedUnfilteredIncomeList_success() {
        Index indexLastEntry = Index.fromOneBased(model.getFilteredIncomeList().size());
        Entry lastEntry = model.getFilteredIncomeList().get(indexLastEntry.getZeroBased());

        IncomeBuilder incomeInList = new IncomeBuilder(lastEntry);

        Entry editedEntry = incomeInList
                .withDescription(VALID_DESC_INVESTMENT)
                .withAmount(VALID_AMT_INVESTMENT)
                .build();

        // Specify only the description and amount field to be updated
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder()
                .withType(VALID_TYPE_INCOME)
                .withDescription(VALID_DESC_INVESTMENT)
                .withAmount(VALID_AMT_INVESTMENT)
                .build();

        EditCommand editCommand = new EditCommand(indexLastEntry, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry);

        Model expectedModel = new ModelManager(new PennyWise(model.getPennyWise()), new UserPrefs());
        expectedModel.setIncome(lastEntry, editedEntry);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredExpenditureList_success() {
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
    public void execute_noFieldSpecifiedUnfilteredIncomeList_success() {
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder()
                .withType(EntryType.ENTRY_TYPE_INCOME)
                .build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENTRY, descriptor);
        Entry editedEntry = model.getFilteredIncomeList().get(INDEX_FIRST_ENTRY.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry);

        Model expectedModel = new ModelManager(new PennyWise(model.getPennyWise()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredExpenditureList_success() {
        showExpenditureAtIndex(model, INDEX_FIRST_ENTRY);

        Entry entryInFilteredList = model.getFilteredExpenditureList().get(INDEX_FIRST_ENTRY.getZeroBased());
        Entry editedEntry = new ExpenditureBuilder(entryInFilteredList)
                .withDescription(VALID_DESC_TUITION).withTag(VALID_TAG_PERSONAL).build();


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
    public void execute_filteredIncomeList_success() {
        showIncomeAtIndex(model, INDEX_FIRST_ENTRY);

        Entry entryInFilteredList = model.getFilteredIncomeList().get(INDEX_FIRST_ENTRY.getZeroBased());
        Entry editedEntry = new IncomeBuilder(entryInFilteredList)
                .withDescription(VALID_DESC_INVESTMENT).withTag(VALID_TAG_INVESTMENT).build();


        EditCommand editCommand = new EditCommand(
                INDEX_FIRST_ENTRY,
                new EditEntryDescriptorBuilder()
                        .withType(VALID_TYPE_INCOME)
                        .withDescription(VALID_DESC_INVESTMENT)
                        .withTag(VALID_TAG_INVESTMENT)
                        .build()
        );

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry);

        Model expectedModel = new ModelManager(new PennyWise(model.getPennyWise()), new UserPrefs());
        expectedModel.setIncome(
                model.getFilteredIncomeList().get(INDEX_FIRST_ENTRY.getZeroBased()),
                editedEntry
        );

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateExpenditureUnfilteredList_failure() {
        Entry firstEntry = model.getFilteredExpenditureList().get(INDEX_FIRST_ENTRY.getZeroBased());
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder(
                firstEntry, new EntryType(EntryType.ENTRY_TYPE_EXPENDITURE)
        ).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_ENTRY, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ENTRY);
    }

    @Test
    public void execute_duplicateIncomeUnfilteredList_failure() {
        Entry firstEntry = model.getFilteredIncomeList().get(INDEX_FIRST_ENTRY.getZeroBased());
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder(
                firstEntry, new EntryType(EntryType.ENTRY_TYPE_INCOME)
        ).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_ENTRY, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ENTRY);
    }

    @Test
    public void execute_duplicateExpenditureFilteredList_failure() {
        showExpenditureAtIndex(model, INDEX_FIRST_ENTRY);

        // Edit expenditure in filtered income list (i.e. the 1st expenditure entry in the entire list) into a duplicate
        // (i.e. the 2nd expenditure entry in the entire list) in PennyWise.
        Entry entryInList = model.getPennyWise().getExpenditureList().get(INDEX_SECOND_ENTRY.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENTRY,
                new EditEntryDescriptorBuilder(entryInList, new EntryType(EntryType.ENTRY_TYPE_EXPENDITURE)).build()
        );

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ENTRY);
    }

    @Test
    public void execute_duplicateIncomeFilteredList_failure() {
        showIncomeAtIndex(model, INDEX_FIRST_ENTRY);

        // Edit income in filtered income list (i.e. the 1st income entry in the entire list) into a duplicate
        // (i.e. the 2nd income entry in the entire list) in PennyWise.
        Entry entryInList = model.getPennyWise().getIncomeList().get(INDEX_SECOND_ENTRY.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENTRY,
                new EditEntryDescriptorBuilder(entryInList, new EntryType(EntryType.ENTRY_TYPE_INCOME)).build()
        );

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ENTRY);
    }

    @Test
    public void execute_invalidExpenditureIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExpenditureList().size() + 1);
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder()
                .withType(VALID_TYPE_EXPENDITURE)
                .withDescription(VALID_DESC_TUITION)
                .build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIncomeIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredIncomeList().size() + 1);
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder()
                .withType(VALID_TYPE_INCOME)
                .withDescription(VALID_DESC_INVESTMENT)
                .build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered expenditure list where index is larger than size of filtered list,
     * but smaller than size of PennyWise.
     */
    @Test
    public void execute_invalidExpenditureIndexFilteredList_failure() {
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

    /**
     * Edit filtered income list where index is larger than size of filtered list,
     * but smaller than size of PennyWise.
     */
    @Test
    public void execute_invalidIncomeIndexFilteredList_failure() {
        showIncomeAtIndex(model, INDEX_FIRST_ENTRY);
        Index outOfBoundIndex = INDEX_SECOND_ENTRY;
        // ensures that outOfBoundIndex is still in bounds of PennyWise list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPennyWise().getIncomeList().size());

        EditCommand editCommand = new EditCommand(
                outOfBoundIndex,
                new EditEntryDescriptorBuilder()
                        .withType(VALID_TYPE_INCOME)
                        .withDescription(VALID_DESC_INVESTMENT)
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

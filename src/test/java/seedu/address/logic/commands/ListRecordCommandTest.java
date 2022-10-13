package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.ListRecordCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.address.testutil.TypicalIndexes.SECOND_INDEX;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class ListRecordCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredPersonList_success() {
        Person personToListRecords = model.getFilteredPersonList().get(FIRST_INDEX.getZeroBased());
        ListRecordCommand listRecordCommand = new ListRecordCommand(FIRST_INDEX);


        String expectedMessage = MESSAGE_SUCCESS + personToListRecords.getName() + "\n"
                + String.format(Messages.MESSAGE_RECORDS_LISTED_OVERVIEW,
                model.getFilteredRecordList().size());

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(listRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredPersonList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ListRecordCommand listRecordCommand = new ListRecordCommand(outOfBoundIndex);

        assertCommandFailure(listRecordCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredPersonList_success() {
        showPersonAtIndex(model, FIRST_INDEX);

        Person personToListRecords = model.getFilteredPersonList().get(FIRST_INDEX.getZeroBased());
        ListRecordCommand listRecordCommand = new ListRecordCommand(FIRST_INDEX);

        String expectedMessage = MESSAGE_SUCCESS + personToListRecords.getName() + "\n"
                + String.format(Messages.MESSAGE_RECORDS_LISTED_OVERVIEW,
                model.getFilteredRecordList().size());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showPersonAtIndex(expectedModel, FIRST_INDEX);

        assertCommandSuccess(listRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredPersonList_throwsCommandException() {
        showPersonAtIndex(model, FIRST_INDEX);

        Index outOfBoundIndex = SECOND_INDEX;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        ListRecordCommand listRecordCommand = new ListRecordCommand(outOfBoundIndex);

        assertCommandFailure(listRecordCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ListRecordCommand listRecordFirstCommand = new ListRecordCommand(FIRST_INDEX);
        ListRecordCommand listRecordSecondCommand = new ListRecordCommand(SECOND_INDEX);

        // same object -> returns true
        assertTrue(listRecordFirstCommand.equals(listRecordFirstCommand));

        // same values -> returns true
        ListRecordCommand listRecordFirstCommandCopy = new ListRecordCommand(FIRST_INDEX);
        assertTrue(listRecordFirstCommand.equals(listRecordFirstCommandCopy));

        // different types -> returns false
        assertFalse(listRecordFirstCommand.equals(1));

        // null -> returns false
        assertFalse(listRecordFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(listRecordFirstCommand.equals(listRecordSecondCommand));
    }
}


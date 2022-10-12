package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.ListRecordCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.*;
import static seedu.address.testutil.TypicalPersons.RECORD3;

public class ListRecordCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToListRecords = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ListRecordCommand listRecordCommand = new ListRecordCommand(INDEX_FIRST_PERSON);


        String expectedMessage = MESSAGE_SUCCESS + personToListRecords.getName() + "\n"
                + String.format(Messages.MESSAGE_RECORDS_LISTED_OVERVIEW,
                model.getFilteredRecordList().size());

        assertCommandSuccess(listRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ListRecordCommand listRecordCommand = new ListRecordCommand(outOfBoundIndex);

        assertCommandFailure(listRecordCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToListRecords = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ListRecordCommand listRecordCommand = new ListRecordCommand(INDEX_FIRST_PERSON);

        String expectedMessage = MESSAGE_SUCCESS + personToListRecords.getName() + "\n"
                + String.format(Messages.MESSAGE_RECORDS_LISTED_OVERVIEW,
                model.getFilteredRecordList().size());

        assertCommandSuccess(listRecordCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        ListRecordCommand listRecordCommand = new ListRecordCommand(outOfBoundIndex);

        assertCommandFailure(listRecordCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ListRecordCommand listRecordFirstCommand = new ListRecordCommand(INDEX_FIRST_PERSON);
        ListRecordCommand listRecordSecondCommand = new ListRecordCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(listRecordFirstCommand.equals(listRecordFirstCommand));

        // same values -> returns true
        ListRecordCommand listRecordFirstCommandCopy = new ListRecordCommand(INDEX_FIRST_PERSON);
        assertTrue(listRecordFirstCommand.equals(listRecordFirstCommandCopy));

        // different types -> returns false
        assertFalse(listRecordFirstCommand.equals(1));

        // null -> returns false
        assertFalse(listRecordFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(listRecordFirstCommand.equals(listRecordSecondCommand));
    }
}

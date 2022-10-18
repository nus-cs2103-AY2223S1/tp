package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

class OpenPersonFileCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        OpenPersonFileCommand openPersonFileCommand = new OpenPersonFileCommand(outOfBoundIndex);

        assertCommandFailure(openPersonFileCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        OpenPersonFileCommand openPersonFileCommand = new OpenPersonFileCommand(outOfBoundIndex);

        assertCommandFailure(openPersonFileCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        OpenPersonFileCommand openPersonFileFirstCommand = new OpenPersonFileCommand(INDEX_FIRST_PERSON);
        OpenPersonFileCommand openPersonFileSecondCommand = new OpenPersonFileCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertEquals(openPersonFileFirstCommand, openPersonFileFirstCommand);

        // same values -> returns true
        OpenPersonFileCommand openPersonFileFirstCommandCopy = new OpenPersonFileCommand(INDEX_FIRST_PERSON);
        assertEquals(openPersonFileFirstCommand, openPersonFileFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, openPersonFileFirstCommand);

        // null -> returns false
        assertNotEquals(null, openPersonFileFirstCommand);

        // different person -> returns false
        assertNotEquals(openPersonFileFirstCommand, openPersonFileSecondCommand);
    }
}

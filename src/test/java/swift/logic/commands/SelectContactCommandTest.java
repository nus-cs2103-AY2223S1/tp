package swift.logic.commands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swift.logic.commands.CommandTestUtil.assertCommandFailure;
import static swift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static swift.logic.commands.CommandTestUtil.showPersonAtIndex;
import static swift.testutil.TypicalIntegratedAddressBook.PERSON1_UUID;
import static swift.testutil.TypicalIntegratedAddressBook.PERSON2_UUID;
import static swift.testutil.TypicalIntegratedAddressBook.TASK3_UUID;
import static swift.testutil.TypicalIntegratedAddressBook.getTypicalAddressBook;
import static swift.testutil.TypicalPersonIndexes.INDEX_FIRST_PERSON;
import static swift.testutil.TypicalPersonIndexes.INDEX_SECOND_PERSON;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import swift.commons.core.Messages;
import swift.commons.core.index.Index;
import swift.model.Model;
import swift.model.ModelManager;
import swift.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code SelectContactCommand}.
 */
public class SelectContactCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        SelectContactCommand selectContactCommand = new SelectContactCommand(INDEX_SECOND_PERSON);
        String expectedMessage = SelectContactCommand.MESSAGE_SELECT_CONTACT_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList((p) -> p.getId().equals(UUID.fromString(PERSON2_UUID)));
        expectedModel.updateFilteredTaskList((t) -> t.getId().equals(UUID.fromString(TASK3_UUID)));
        expectedModel.updateFilteredBridgeList((b) -> b.getPersonId().equals(UUID.fromString(PERSON2_UUID)));

        assertCommandSuccess(selectContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        SelectContactCommand selectContactCommand = new SelectContactCommand(outOfBoundIndex);

        assertCommandFailure(selectContactCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        SelectContactCommand selectContactCommand = new SelectContactCommand(INDEX_FIRST_PERSON);
        String expectedMessage = SelectContactCommand.MESSAGE_SELECT_CONTACT_SUCCESS;

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList((p) -> p.getId().equals(UUID.fromString(PERSON1_UUID)));
        expectedModel.updateFilteredTaskList((t) -> !t.getId().equals(UUID.fromString(TASK3_UUID)));
        expectedModel.updateFilteredBridgeList((b) -> b.getPersonId().equals(UUID.fromString(PERSON1_UUID)));

        assertCommandSuccess(selectContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        SelectContactCommand selectContactCommand = new SelectContactCommand(outOfBoundIndex);

        assertCommandFailure(selectContactCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        SelectContactCommand selectFirstContactCommand = new SelectContactCommand(INDEX_FIRST_PERSON);
        SelectContactCommand selectSecondContactCommand = new SelectContactCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(selectFirstContactCommand.equals(selectFirstContactCommand));

        // same values -> returns true
        SelectContactCommand selectFirstContactCommandCopy = new SelectContactCommand(INDEX_FIRST_PERSON);
        assertTrue(selectFirstContactCommand.equals(selectFirstContactCommandCopy));

        // different types -> returns false
        assertFalse(selectFirstContactCommand.equals(1));

        // null -> returns false
        assertFalse(selectFirstContactCommand.equals(null));

        // different task -> returns false
        assertFalse(selectFirstContactCommand.equals(selectSecondContactCommand));
    }
}

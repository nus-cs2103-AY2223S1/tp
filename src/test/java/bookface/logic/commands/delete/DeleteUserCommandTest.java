package bookface.logic.commands.delete;

import static bookface.testutil.TypicalPersons.getTypicalBookFaceData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bookface.commons.core.Messages;
import bookface.commons.core.index.Index;
import bookface.logic.commands.CommandTestUtil;
import bookface.model.Model;
import bookface.model.ModelManager;
import bookface.model.UserPrefs;
import bookface.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteUserCommandTest {

    private final Model model = new ModelManager(getTypicalBookFaceData(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteUserCommand deleteUserCommand = new DeleteUserCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteUserCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getBookFace().getPersonList().size());

        DeleteUserCommand deleteUserCommand = new DeleteUserCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteUserCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteUserCommand deleteFirstCommand = new DeleteUserCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        DeleteUserCommand deleteSecondCommand = new DeleteUserCommand(TypicalIndexes.INDEX_SECOND_PERSON);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteUserCommand deleteFirstCommandCopy = new DeleteUserCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstCommand);

        // different person -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }
}

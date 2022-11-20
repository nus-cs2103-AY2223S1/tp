package bookface.logic.commands;

import static bookface.logic.commands.CommandTestUtil.assertCommandFailure;
import static bookface.testutil.TypicalPersons.getAllTypicalBookFaceData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bookface.commons.core.Messages;
import bookface.commons.core.index.Index;
import bookface.model.Model;
import bookface.model.ModelManager;
import bookface.model.UserPrefs;
import bookface.testutil.TypicalIndexes;

public class ReturnCommandTest {
    private final Model model = new ModelManager(getAllTypicalBookFaceData(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookList().size() + 1);
        ReturnCommand returnCommand = new ReturnCommand(outOfBoundIndex);

        assertCommandFailure(returnCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        CommandTestUtil.showBookAtIndex(model, TypicalIndexes.INDEX_FIRST_BOOK);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_BOOK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getBookFace().getBookList().size());

        ReturnCommand returnCommand = new ReturnCommand(outOfBoundIndex);

        assertCommandFailure(returnCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ReturnCommand returnCommand = new ReturnCommand(TypicalIndexes.INDEX_FIRST_BOOK);
        ReturnCommand returnSecondCommand = new ReturnCommand(TypicalIndexes.INDEX_SECOND_BOOK);

        // same object -> returns true
        assertEquals(returnCommand, returnCommand);

        // same values -> returns true
        ReturnCommand returnFirstCommandCopy = new ReturnCommand(TypicalIndexes.INDEX_FIRST_BOOK);
        assertEquals(returnCommand, returnFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, returnCommand);

        // null -> returns false
        assertNotEquals(null, returnCommand);

        // different person -> returns false
        assertNotEquals(returnCommand, returnSecondCommand);
    }
}

package bookface.logic.commands.delete;

import static bookface.testutil.TypicalBooks.getTypicalBookFaceData;
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

public class DeleteBookCommandTest {
    private final Model model = new ModelManager(getTypicalBookFaceData(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookList().size() + 1);
        DeleteBookCommand deleteBookCommand = new DeleteBookCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteBookCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        CommandTestUtil.showBookAtIndex(model, TypicalIndexes.INDEX_FIRST_BOOK);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_BOOK;
        assertTrue(outOfBoundIndex.getZeroBased() < model.getBookFace().getBookList().size());

        DeleteBookCommand deleteBookCommand = new DeleteBookCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteBookCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteBookCommand deleteFirstCommand = new DeleteBookCommand(TypicalIndexes.INDEX_FIRST_BOOK);
        DeleteBookCommand deleteSecondCommand = new DeleteBookCommand(TypicalIndexes.INDEX_SECOND_BOOK);

        // Same Object -> Returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // Same values -> Returns true
        DeleteBookCommand deleteFirstCommandCopy = new DeleteBookCommand(TypicalIndexes.INDEX_FIRST_BOOK);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // Different types -> Returns false
        assertNotEquals(0, deleteFirstCommand);

        // NULL -> Returns false
        assertNotEquals(null, deleteFirstCommand);

        // Different Book -> Returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }
}

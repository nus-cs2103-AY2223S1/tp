package bookface.logic.commands.edit;

import static bookface.logic.commands.CommandTestUtil.assertCommandSuccess;
import static bookface.testutil.TypicalBooks.getTypicalBookFaceData;

import org.junit.jupiter.api.Test;

import bookface.commons.core.index.Index;
import bookface.logic.commands.CommandTestUtil;
import bookface.logic.commands.edit.EditBookCommand.EditBookDescriptor;
import bookface.model.BookFace;
import bookface.model.Model;
import bookface.model.ModelManager;
import bookface.model.UserPrefs;
import bookface.model.book.Book;
import bookface.testutil.BookBuilder;
import bookface.testutil.EditBookDescriptorBuilder;

public class EditBookCommandTest {
    private final Model model = new ModelManager(getTypicalBookFaceData(), new UserPrefs());

    /**
     * Test will involve editing {@code Title} only
     */
    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastBook = Index.fromOneBased(model.getFilteredBookList().size());
        Book lastBook = model.getFilteredBookList().get(indexLastBook.getZeroBased());

        BookBuilder bookInList = new BookBuilder(lastBook);
        Book editedBook = bookInList.withTitle(CommandTestUtil.VALID_TITLE_GETMOTIVATED).build();

        EditBookDescriptor descriptor = new EditBookDescriptorBuilder()
                .withTitle(CommandTestUtil.VALID_TITLE_GETMOTIVATED).build();
        EditBookCommand editBookCommand = new EditBookCommand(indexLastBook, descriptor);

        String expectedMessage = String.format(EditBookCommand.MESSAGE_EDIT_BOOK_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new BookFace(model.getBookFace()), new UserPrefs());
        expectedModel.setBook(lastBook, editedBook);

        assertCommandSuccess(editBookCommand, model, expectedMessage, expectedModel);
    }
}

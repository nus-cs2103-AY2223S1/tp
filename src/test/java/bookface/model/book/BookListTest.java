package bookface.model.book;

import bookface.model.book.exceptions.BookNotFoundException;
import bookface.model.book.exceptions.DuplicateBookException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static bookface.testutil.Assert.assertThrows;
import static bookface.testutil.TypicalBooks.GET_MOTIVATED;
import static bookface.testutil.TypicalBooks.HOW_TO_SPELL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookListTest {

    private final BookList bookList = new BookList();

    @Test
    public void contains_duplicateBook_throwsDuplicateBookException() {
        bookList.add(GET_MOTIVATED);
        assertThrows(DuplicateBookException.class, () -> bookList.add(GET_MOTIVATED));
    }

    @Test
    public void setBook_nullTargetBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bookList.setBook(null, GET_MOTIVATED));
    }

    @Test
    public void setBook_nullEditedBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bookList.setBook(GET_MOTIVATED, null));
    }

    @Test
    public void setBook_targetBookNotInList_throwsBookNotFoundException() {
        assertThrows(BookNotFoundException.class, () -> bookList.setBook(GET_MOTIVATED, GET_MOTIVATED));
    }

    @Test
    public void setBook_editedBookExistsInList_throwsDuplicateBookException() {
        bookList.add(GET_MOTIVATED);
        bookList.add(HOW_TO_SPELL);
        assertThrows(DuplicateBookException.class, () -> bookList.setBook(GET_MOTIVATED, HOW_TO_SPELL));
    }

    @Test
    public void delete_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bookList.delete(null));
    }

    @Test
    public void delete_bookDoesNotExist_throwsBookNotFoundException() {
        assertThrows(BookNotFoundException.class, () -> bookList.delete(GET_MOTIVATED));
    }

    @Test
    public void delete_existingBook_deletesBook() {
        bookList.add(GET_MOTIVATED);
        bookList.delete(GET_MOTIVATED);
        BookList expectedBookList = new BookList();
        assertEquals(expectedBookList, bookList);
    }

    @Test
    public void refreshBookListAfterDeletingPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bookList.refreshBookListAfterDeletingPerson(null));
    }

    @Test
    public void setBooks_nullReplacement_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bookList.setBooks((BookList) null));
    }

    @Test
    public void setBooks_listWithDuplicateBooks_throwsDuplicateBookException() {
        List<Book> listWithDuplicateBooks = Arrays.asList(GET_MOTIVATED, GET_MOTIVATED);
        assertThrows(DuplicateBookException.class, () -> bookList.setBooks(listWithDuplicateBooks));
    }

    @Test
    public void returnLoanedBook_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bookList.returnLoanedBook(null));
    }
}

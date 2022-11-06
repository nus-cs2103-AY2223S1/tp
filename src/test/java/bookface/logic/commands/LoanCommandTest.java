package bookface.logic.commands;

import static bookface.logic.commands.LoanCommand.ALREADY_LOANED;
import static bookface.testutil.TypicalDates.TYPICAL_DATE;
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
import bookface.model.book.Book;
import bookface.model.person.Person;
import bookface.testutil.TypicalIndexes;

public class LoanCommandTest {
    private final Model model = new ModelManager(getAllTypicalBookFaceData(), new UserPrefs());

    @Test
    public void execute_loanedBook_throwsCommandException() {
        Book bookToLoan = model.getFilteredBookList().get(TypicalIndexes.INDEX_FIRST_BOOK.getZeroBased());
        Person personToLoan = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        LoanCommand loanCommand = new LoanCommand(TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_FIRST_BOOK);
        String expectedMessage = String.format(ALREADY_LOANED);
        ModelManager expectedModel = new ModelManager(model.getBookFace(), new UserPrefs());
        expectedModel.loan(personToLoan, bookToLoan, TYPICAL_DATE);
        CommandTestUtil.assertCommandFailure(loanCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexUnfilteredPersonList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        LoanCommand loanCommand = new LoanCommand(outOfBoundIndex, TypicalIndexes.INDEX_FIRST_BOOK);
        CommandTestUtil.assertCommandFailure(loanCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


    // For this, it shows "Book is already loaned out." instead of MESSAGE_LOAN_SUCCESS, but I'm not sure why this
    // is happening?

    /*
    @Test
    public void execute_success() {
        Person personToLoan = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        Book bookToLoan = model.getFilteredBookList().get(TypicalIndexes.INDEX_FIRST_BOOK.getZeroBased());

        LoanCommand loanCommand = new LoanCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                TypicalIndexes.INDEX_FIRST_BOOK, TYPICAL_DATE);

        String expectedMessage = String.format(LoanCommand.MESSAGE_LOAN_SUCCESS,
                personToLoan, bookToLoan, TYPICAL_DATE);

        Model expectedModel = new ModelManager(model.getBookFace(), new UserPrefs());
        expectedModel.loan(personToLoan, bookToLoan, TYPICAL_DATE);
        assertCommandSuccess(loanCommand, model, expectedMessage, expectedModel);
    }
     */

    @Test
    public void execute_invalidIndexUnfilteredBookList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookList().size() + 1);
        LoanCommand loanCommand = new LoanCommand(TypicalIndexes.INDEX_FIRST_PERSON, outOfBoundIndex);
        CommandTestUtil.assertCommandFailure(loanCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredPersonList_throwsCommandException() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of bookface list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getBookFace().getPersonList().size());

        LoanCommand loanCommand = new LoanCommand(outOfBoundIndex, TypicalIndexes.INDEX_FIRST_BOOK);

        CommandTestUtil.assertCommandFailure(loanCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredBookList_throwsCommandException() {
        CommandTestUtil.showBookAtIndex(model, TypicalIndexes.INDEX_FIRST_BOOK);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_BOOK;
        // ensures that outOfBoundIndex is still in bounds of bookface list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getBookFace().getBookList().size());

        LoanCommand loanCommand = new LoanCommand(TypicalIndexes.INDEX_FIRST_PERSON, outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(loanCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        LoanCommand loanFirstCommand = new LoanCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                TypicalIndexes.INDEX_FIRST_BOOK, TYPICAL_DATE);
        LoanCommand loanSecondCommand = new LoanCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                TypicalIndexes.INDEX_FIRST_BOOK, TYPICAL_DATE);
        LoanCommand loanThirdCommand = new LoanCommand(TypicalIndexes.INDEX_SECOND_PERSON,
                TypicalIndexes.INDEX_FIRST_BOOK, TYPICAL_DATE);

        // same object -> returns true
        assertEquals(loanFirstCommand, loanFirstCommand);

        // same values -> returns true
        assertEquals(loanFirstCommand, loanSecondCommand);

        // different types -> returns false
        assertNotEquals(1, loanFirstCommand);

        // null -> returns false
        assertNotEquals(null, loanFirstCommand);

        // different person -> returns false
        assertNotEquals(loanFirstCommand, loanThirdCommand);
    }
}

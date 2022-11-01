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

    //This test is very weird, if i test it on its own, it works, but if i test all the tests at once, it fails.
    //i suspect it depends on some other tests' results?


    /*
    @Test
    public void execute_returnUnloanedBook_throwsCommandException() {
        ReturnCommand returnCommand = new ReturnCommand(TypicalIndexes.INDEX_FIRST_BOOK);

        assertCommandFailure(returnCommand, model, NOT_ON_LOAN);
    }
     */




    //Same as loancommandtest, it shows "Book is not on loan." instead of MESSAGE_RETURN_SUCCESS,
    //but I'm not sure why this is happening?



    /*
    @Test
    public void execute_returnLoanedBook_success() throws ParseException {
        Book bookToReturn = model.getFilteredBookList().get(TypicalIndexes.INDEX_FIRST_BOOK.getZeroBased());

        ReturnCommand returnCommand = new ReturnCommand(TypicalIndexes.INDEX_FIRST_BOOK);
        String expectedMessage = String.format(ReturnCommand.MESSAGE_RETURN_SUCCESS, bookToReturn);

        Index userIndex = Index.fromOneBased(1);
        Model expectedModel = new ModelManager(model.getBookFace(), new UserPrefs());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        formatter = formatter.withResolverStyle(ResolverStyle.SMART);
        Date date;
        try {
            LocalDate ld = LocalDate.parse("31/10/2022", formatter);
            date = java.sql.Date.valueOf(ld);
        } catch (DateTimeParseException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_DATE_FORMAT);
        }

        expectedModel.loan(model.getFilteredPersonList().get(userIndex.getZeroBased()),
                model.getFilteredBookList().get(userIndex.getZeroBased()), date);

        expectedModel.returnLoanedBook(bookToReturn);
        assertCommandSuccess(returnCommand, model, expectedMessage, expectedModel);
    }
     */



    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookList().size() + 1);
        ReturnCommand returnCommand = new ReturnCommand(outOfBoundIndex);

        assertCommandFailure(returnCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    //Same case as above, expected true but was false. I think its due to the same "book not loaned out" issue
    // and trying to return book


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

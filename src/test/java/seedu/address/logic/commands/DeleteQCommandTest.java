package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showQuestionAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_QUESTION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_QUESTION;
import static seedu.address.testutil.TypicalQuestions.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.question.Question;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteQCommand}.
 */
public class DeleteQCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Question questionToDeleteQ = model.getFilteredQuestionList().get(INDEX_FIRST_QUESTION.getZeroBased());
        DeleteQCommand deleteQCommand = new DeleteQCommand(INDEX_FIRST_QUESTION);

        String expectedMessage = String.format(DeleteQCommand.MESSAGE_DELETE_QUESTION_SUCCESS, questionToDeleteQ);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteQuestion(questionToDeleteQ);

        assertCommandSuccess(deleteQCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredQuestionList().size() + 1);
        DeleteQCommand deleteQCommand = new DeleteQCommand(outOfBoundIndex);

        assertCommandFailure(deleteQCommand, model, Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showQuestionAtIndex(model, INDEX_FIRST_QUESTION);

        Question questionToDeleteQ = model.getFilteredQuestionList().get(INDEX_FIRST_QUESTION.getZeroBased());
        DeleteQCommand deleteQCommand = new DeleteQCommand(INDEX_FIRST_QUESTION);

        String expectedMessage = String.format(DeleteQCommand.MESSAGE_DELETE_QUESTION_SUCCESS, questionToDeleteQ);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteQuestion(questionToDeleteQ);
        showNoQuestion(expectedModel);

        assertCommandSuccess(deleteQCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showQuestionAtIndex(model, INDEX_FIRST_QUESTION);

        Index outOfBoundIndex = INDEX_SECOND_QUESTION;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getQuestionList().size());

        DeleteQCommand deleteQCommand = new DeleteQCommand(outOfBoundIndex);

        assertCommandFailure(deleteQCommand, model, Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteQCommand deleteQFirstCommand = new DeleteQCommand(INDEX_FIRST_QUESTION);
        DeleteQCommand deleteQSecondCommand = new DeleteQCommand(INDEX_SECOND_QUESTION);

        // same object -> returns true
        assertTrue(deleteQFirstCommand.equals(deleteQFirstCommand));

        // same values -> returns true
        DeleteQCommand deleteQFirstCommandCopy = new DeleteQCommand(INDEX_FIRST_QUESTION);
        assertTrue(deleteQFirstCommand.equals(deleteQFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteQFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteQFirstCommand.equals(null));

        // different question -> returns false
        assertFalse(deleteQFirstCommand.equals(deleteQSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoQuestion(Model model) {
        model.updateFilteredQuestionList(p -> false);

        assertTrue(model.getFilteredQuestionList().isEmpty());
    }
}

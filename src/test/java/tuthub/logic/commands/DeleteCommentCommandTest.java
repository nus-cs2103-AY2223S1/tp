package tuthub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.logic.commands.CommandTestUtil.assertCommandFailure;
import static tuthub.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tuthub.testutil.TypicalIndexes.INDEX_FIRST_TUTOR;
import static tuthub.testutil.TypicalIndexes.INDEX_SECOND_TUTOR;
import static tuthub.testutil.TypicalTutors.getTypicalTuthub;

import org.junit.jupiter.api.Test;

import tuthub.commons.core.Messages;
import tuthub.commons.core.index.Index;
import tuthub.model.Model;
import tuthub.model.ModelManager;
import tuthub.model.UserPrefs;
import tuthub.model.tutor.Tutor;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommentCommand}.
 */
public class DeleteCommentCommandTest {

    private Model model = new ModelManager(getTypicalTuthub(), new UserPrefs());

    @Test
    public void execute_invalidCommentIndex_throwsCommandException() {
        DeleteCommentCommand deleteCommentCommand = new DeleteCommentCommand(INDEX_FIRST_TUTOR,
                Index.fromZeroBased(10));

        String expectedMessage = DeleteCommentCommand.MESSAGE_INVALID_COMMENT_INDEX;

        assertCommandFailure(deleteCommentCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndex_success() {
        // Add a comment to the first tutor
        Tutor tutorToDelete = model.getFilteredTutorList().get(INDEX_FIRST_TUTOR.getZeroBased());

        int lastCommentPos = model.getFilteredTutorList().get(0).getComments().size() - 1;
        // Comment is at index 0
        DeleteCommentCommand deleteCommentCommand = new DeleteCommentCommand(INDEX_FIRST_TUTOR,
                Index.fromZeroBased(lastCommentPos));

        String expectedMessage = String.format(DeleteCommentCommand.MESSAGE_DELETE_COMMENT_SUCCESS,
                tutorToDelete.getName(), tutorToDelete.getComments().getList().get(lastCommentPos));

        Model expectedModel = new ModelManager(model.getTuthub(), new UserPrefs());

        assertCommandSuccess(deleteCommentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_tutorNoComments_throwsCommandException() {
        Tutor tutorToDelete = model.getFilteredTutorList().get(INDEX_SECOND_TUTOR.getZeroBased());
        DeleteCommentCommand deleteCommentCommand = new DeleteCommentCommand(INDEX_SECOND_TUTOR,
                Index.fromZeroBased(0));

        String expectedMessage = String.format(DeleteCommentCommand.MESSAGE_INVALID_TUTOR_NO_COMMENTS,
                tutorToDelete.getName());

        assertCommandFailure(deleteCommentCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidTutorIndex_throwsCommandException() {
        DeleteCommentCommand deleteCommentCommand = new DeleteCommentCommand(Index.fromZeroBased(200),
                Index.fromZeroBased(0));

        assertCommandFailure(deleteCommentCommand, model, Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommentCommand deleteCommentFirstCommand = new DeleteCommentCommand(INDEX_FIRST_TUTOR,
                Index.fromZeroBased(0));
        DeleteCommentCommand deleteCommentSecondCommand = new DeleteCommentCommand(INDEX_SECOND_TUTOR,
                Index.fromZeroBased(1));
        DeleteCommentCommand deleteCommentThirdCommand = new DeleteCommentCommand(INDEX_SECOND_TUTOR,
                Index.fromZeroBased(0));

        // same object -> returns true
        assertTrue(deleteCommentFirstCommand.equals(deleteCommentFirstCommand));

        // same values -> returns true
        DeleteCommentCommand deleteCommentFirstCommandCopy = new DeleteCommentCommand(INDEX_FIRST_TUTOR,
                Index.fromZeroBased(0));
        assertTrue(deleteCommentFirstCommand.equals(deleteCommentFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteCommentFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteCommentFirstCommand.equals(null));

        // different tutor -> returns false
        assertFalse(deleteCommentFirstCommand.equals(deleteCommentThirdCommand));

        // different comment index -> returns false
        assertFalse(deleteCommentSecondCommand.equals(deleteCommentThirdCommand));

        // different all -> returns false
        assertFalse(deleteCommentFirstCommand.equals(deleteCommentSecondCommand));
    }
}

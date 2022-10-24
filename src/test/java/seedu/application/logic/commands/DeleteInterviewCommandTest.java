package seedu.application.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.application.commons.core.Messages;
import seedu.application.commons.core.index.Index;
import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;
import seedu.application.model.application.Application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.commands.CommandTestUtil.*;
import static seedu.application.testutil.TypicalApplicationsWithInterview.getTypicalApplicationBookWithInterview;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.application.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteInterviewCommand}.
 */
public class DeleteInterviewCommandTest {

    private Model model = new ModelManager(getTypicalApplicationBookWithInterview(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Application applicationWithInterviewToDelete = model.getApplicationListWithInterview()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());
        DeleteInterviewCommand deleteCommand = new DeleteInterviewCommand(INDEX_FIRST_APPLICATION);

        String expectedMessage = String.format(DeleteInterviewCommand.MESSAGE_DELETE_APPLICATION_SUCCESS, applicationWithInterviewToDelete.getInterview().get());

        ModelManager expectedModel = new ModelManager(model.getApplicationBook(), new UserPrefs());
        expectedModel.setApplication(applicationWithInterviewToDelete, new Application(applicationWithInterviewToDelete));

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getApplicationListWithInterview().size() + 1);
        DeleteInterviewCommand deleteCommand = new DeleteInterviewCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        DeleteInterviewCommand deleteFirstCommand = new DeleteInterviewCommand(INDEX_FIRST_APPLICATION);
        DeleteInterviewCommand deleteSecondCommand = new DeleteInterviewCommand(INDEX_SECOND_APPLICATION);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteInterviewCommand deleteFirstCommandCopy = new DeleteInterviewCommand(INDEX_FIRST_APPLICATION);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different interview -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
    
}

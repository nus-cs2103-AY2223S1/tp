package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.testutil.TypicalApplicationsWithInterview.getTypicalApplicationBookWithInterview;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.application.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.application.commons.core.Messages;
import seedu.application.commons.core.index.Index;
import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;
import seedu.application.model.application.Application;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RemoveInterviewCommand}.
 */
public class RemoveInterviewCommandTest {

    private Model model = new ModelManager(getTypicalApplicationBookWithInterview(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Application applicationWithInterviewToRemove = model.getApplicationListWithInterview()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());
        RemoveInterviewCommand removeCommand = new RemoveInterviewCommand(INDEX_FIRST_APPLICATION);

        String expectedMessage = String.format(RemoveInterviewCommand.MESSAGE_REMOVE_INTERVIEW_SUCCESS,
                applicationWithInterviewToRemove.getInterview().get());

        ModelManager expectedModel = new ModelManager(model.getApplicationBook(), new UserPrefs());
        expectedModel.setApplication(applicationWithInterviewToRemove,
                new Application(applicationWithInterviewToRemove));

        assertCommandSuccess(removeCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getApplicationListWithInterview().size() + 1);
        RemoveInterviewCommand removeCommand = new RemoveInterviewCommand(outOfBoundIndex);

        assertCommandFailure(removeCommand, model, Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        RemoveInterviewCommand removeFirstCommand = new RemoveInterviewCommand(INDEX_FIRST_APPLICATION);
        RemoveInterviewCommand removeSecondCommand = new RemoveInterviewCommand(INDEX_SECOND_APPLICATION);

        // same object -> returns true
        assertTrue(removeFirstCommand.equals(removeFirstCommand));

        // same values -> returns true
        RemoveInterviewCommand removeFirstCommandCopy = new RemoveInterviewCommand(INDEX_FIRST_APPLICATION);
        assertTrue(removeFirstCommand.equals(removeFirstCommandCopy));

        // different types -> returns false
        assertFalse(removeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(removeFirstCommand.equals(null));

        // different interview -> returns false
        assertFalse(removeFirstCommand.equals(removeSecondCommand));
    }

}

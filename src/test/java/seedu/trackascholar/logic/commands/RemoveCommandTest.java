package seedu.trackascholar.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.trackascholar.testutil.TypicalApplicants.getTypicalTrackAScholar;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.ModelManager;
import seedu.trackascholar.model.UserPrefs;
import seedu.trackascholar.model.applicant.ApplicationStatus;


public class RemoveCommandTest {

    @Test
    public void confirmRemove_nonEmptyTrackAScholar_success() {
        RemoveCommand firstRemoveCommand =
                new RemoveCommand(new ApplicationStatus("accepted"));
        RemoveCommand secondRemoveCommand =
                new RemoveCommand(new ApplicationStatus("rejected"));
        Model model = new ModelManager(getTypicalTrackAScholar(), new UserPrefs());
        CommandResult commandResult = firstRemoveCommand.confirmRemove(model);
        CommandResult successResult = new CommandResult(RemoveCommand.MESSAGE_REMOVE_APPLICANTS_SUCCESS);
        assertEquals(commandResult, successResult);

        commandResult = secondRemoveCommand.confirmRemove(model);
        assertEquals(commandResult, successResult);

    }

    @Test
    public void confirmRemove_emptyTrackAScholar_success() {
        RemoveCommand firstRemoveCommand =
                new RemoveCommand(new ApplicationStatus("accepted"));
        RemoveCommand secondRemoveCommand =
                new RemoveCommand(new ApplicationStatus("rejected"));
        Model model = new ModelManager();
        CommandResult commandResult = firstRemoveCommand.confirmRemove(model);
        CommandResult successResult = new CommandResult(RemoveCommand.MESSAGE_REMOVE_APPLICANTS_SUCCESS);
        assertEquals(commandResult, successResult);

        commandResult = secondRemoveCommand.confirmRemove(model);
        assertEquals(commandResult, successResult);

    }

    @Test
    public void cancelClear_trackAScholar_success() {
        RemoveCommand firstRemoveCommand =
                new RemoveCommand(new ApplicationStatus("accepted"));
        RemoveCommand secondRemoveCommand =
                new RemoveCommand(new ApplicationStatus("rejected"));
        CommandResult commandResult = firstRemoveCommand.cancelRemove();
        CommandResult terminationResult = new CommandResult(RemoveCommand.MESSAGE_REMOVE_APPLICANTS_TERMINATION);
        assertEquals(commandResult, terminationResult);

        commandResult = secondRemoveCommand.cancelRemove();
        assertEquals(commandResult, terminationResult);

    }

    @Test
    public void equals() {
        ApplicationStatus acceptedStatus = new ApplicationStatus("accepted");
        ApplicationStatus rejectedStatus = new ApplicationStatus("rejected");
        RemoveCommand removeFirstCommand = new RemoveCommand(acceptedStatus);
        RemoveCommand removeSecondCommand = new RemoveCommand(rejectedStatus);

        // same object -> returns true
        assertTrue(removeFirstCommand.equals(removeFirstCommand));

        // same values -> returns true
        RemoveCommand removeFirstCommandCopy = new RemoveCommand(acceptedStatus);
        assertTrue(removeFirstCommand.equals(removeFirstCommandCopy));

        // different types -> returns false
        assertFalse(removeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(removeFirstCommand.equals(null));

        // different applicant status -> returns false
        assertFalse(removeFirstCommand.equals(removeSecondCommand));
    }
}

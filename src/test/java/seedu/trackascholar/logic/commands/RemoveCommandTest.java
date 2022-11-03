package seedu.trackascholar.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.model.applicant.ApplicationStatus;


public class RemoveCommandTest {

    //    public void confirmRemoveAccepted_nonEmptyTrackAScholar_success() {
    //        Model model = new ModelManager(getTypicalTrackAScholar(), new UserPrefs());
    //        Model expectedModel = new ModelManager(getTypicalTrackAScholar(), new UserPrefs());
    //        ApplicationStatus acceptedStatus = new ApplicationStatus("accepted");
    //        RemoveCommand removeCommand = new RemoveCommand(acceptedStatus);
    //        removeCommand.confirmRemove(model); //
    //    }

    @Test
    public void equals() {
        ApplicationStatus acceptedStatus = new ApplicationStatus(ApplicationStatus.ACCEPTED);
        ApplicationStatus rejectedStatus = new ApplicationStatus(ApplicationStatus.REJECTED);
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

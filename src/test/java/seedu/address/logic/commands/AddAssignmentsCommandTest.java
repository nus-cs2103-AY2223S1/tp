package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddAssignmentCommand}.
 */
public class AddAssignmentsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validAssignment_success() {
        AddAssignmentsCommand addAssignmentCommand = new AddAssignmentsCommand("Assignment 1 w/100");

        String expectedMessage = String.format(AddAssignmentsCommand.MESSAGE_ADD_ASSIGNMENTS_SUCCESS);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(addAssignmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        AddAssignmentsCommand addAssignmentsFirstCommand = new AddAssignmentsCommand("Assignment 1 w/100");
        AddAssignmentsCommand addAssignmentsSecondCommand = new AddAssignmentsCommand("Assignment 1 w/100");

        // same object -> returns true
        assertTrue(addAssignmentsFirstCommand.equals(addAssignmentsFirstCommand));

        // same values -> returns true
        AddAssignmentsCommand addAssignmentsFirstCommandCopy = new AddAssignmentsCommand("Assignment 1 w/100");
        assertTrue(addAssignmentsFirstCommand.equals(addAssignmentsFirstCommandCopy));

        // different types -> returns false
        assertFalse(addAssignmentsFirstCommand.equals(1));

        // null -> returns false
        assertFalse(addAssignmentsFirstCommand.equals(null));

        // same assignment -> returns true
        assertTrue(addAssignmentsFirstCommand.equals(addAssignmentsSecondCommand));
    }

}

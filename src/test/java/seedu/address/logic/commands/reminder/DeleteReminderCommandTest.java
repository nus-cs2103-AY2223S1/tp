package seedu.address.logic.commands.reminder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_REMINDER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_REMINDER;
import static seedu.address.testutil.TypicalTutorials.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteReminderCommand}.
 */
public class DeleteReminderCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredReminderList().size() + 1);
        DeleteReminderCommand deleteReminderCommand = new DeleteReminderCommand(outOfBoundIndex);

        assertCommandFailure(deleteReminderCommand, model, Messages.MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteReminderCommand deleteFirstReminderCommand = new DeleteReminderCommand(INDEX_FIRST_REMINDER);
        DeleteReminderCommand deleteSecondReminderCommand = new DeleteReminderCommand(INDEX_SECOND_REMINDER);

        // same object -> returns true
        assertTrue(deleteFirstReminderCommand.equals(deleteFirstReminderCommand));

        // same values -> returns true
        DeleteReminderCommand deleteFirstReminderCommandCopy = new DeleteReminderCommand(INDEX_FIRST_REMINDER);
        assertTrue(deleteFirstReminderCommand.equals(deleteFirstReminderCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstReminderCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstReminderCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstReminderCommand.equals(deleteSecondReminderCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoReminder(Model model) {
        model.updateFilteredReminderList(p -> false);

        assertTrue(model.getFilteredReminderList().isEmpty());
    }
}

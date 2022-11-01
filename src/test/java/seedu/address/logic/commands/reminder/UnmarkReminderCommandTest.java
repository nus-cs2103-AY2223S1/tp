package seedu.address.logic.commands.reminder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_REMINDER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_REMINDER;
import static seedu.address.testutil.TypicalReminders.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UnmarkReminderCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredReminderList().size() + 1);
        UnmarkReminderCommand unmarkReminderCommand = new UnmarkReminderCommand(outOfBoundIndex);

        assertCommandFailure(unmarkReminderCommand, model, Messages.MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {

        UnmarkReminderCommand unmarkFirstReminderCommand = new UnmarkReminderCommand(INDEX_FIRST_REMINDER);
        UnmarkReminderCommand unmarkSecondReminderCommand = new UnmarkReminderCommand(INDEX_SECOND_REMINDER);

        // same object -> returns true
        assertTrue(unmarkFirstReminderCommand.equals(unmarkFirstReminderCommand));

        UnmarkReminderCommand unmarkReminderCommandCopy = new UnmarkReminderCommand(INDEX_FIRST_REMINDER);
        assertTrue(unmarkFirstReminderCommand.equals(unmarkReminderCommandCopy));

        // different types -> returns false
        assertFalse(unmarkFirstReminderCommand.equals(1));

        // null -> returns false
        assertFalse(unmarkFirstReminderCommand.equals(null));

        // different person -> returns false
        assertFalse(unmarkFirstReminderCommand.equals(unmarkSecondReminderCommand));

    }
}

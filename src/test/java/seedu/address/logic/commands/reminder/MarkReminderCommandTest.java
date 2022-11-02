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

class MarkReminderCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredReminderList().size() + 1);
        MarkReminderCommand markReminderCommand = new MarkReminderCommand(outOfBoundIndex);

        assertCommandFailure(markReminderCommand, model, Messages.MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {

        MarkReminderCommand markFirstReminderCommand = new MarkReminderCommand(INDEX_FIRST_REMINDER);
        MarkReminderCommand markSecondReminderCommand = new MarkReminderCommand(INDEX_SECOND_REMINDER);

        // same object -> returns true
        assertTrue(markFirstReminderCommand.equals(markFirstReminderCommand));

        MarkReminderCommand markReminderCommandCopy = new MarkReminderCommand(INDEX_FIRST_REMINDER);
        assertTrue(markFirstReminderCommand.equals(markReminderCommandCopy));

        // different types -> returns false
        assertFalse(markFirstReminderCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstReminderCommand.equals(null));

        // different person -> returns false
        assertFalse(markFirstReminderCommand.equals(markSecondReminderCommand));

    }
}

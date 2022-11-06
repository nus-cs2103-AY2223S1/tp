package seedu.modquik.logic.commands.reminder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modquik.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.modquik.testutil.TypicalIndexes.INDEX_FIRST_REMINDER;
import static seedu.modquik.testutil.TypicalIndexes.INDEX_SECOND_REMINDER;
import static seedu.modquik.testutil.TypicalReminders.getTypicalModQuik;

import org.junit.jupiter.api.Test;

import seedu.modquik.commons.core.Messages;
import seedu.modquik.commons.core.index.Index;
import seedu.modquik.model.Model;
import seedu.modquik.model.ModelManager;
import seedu.modquik.model.UserPrefs;

class MarkReminderCommandTest {

    private Model model = new ModelManager(getTypicalModQuik(), new UserPrefs());

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

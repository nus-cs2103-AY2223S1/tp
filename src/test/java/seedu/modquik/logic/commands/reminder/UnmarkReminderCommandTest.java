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

public class UnmarkReminderCommandTest {

    private Model model = new ModelManager(getTypicalModQuik(), new UserPrefs());

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

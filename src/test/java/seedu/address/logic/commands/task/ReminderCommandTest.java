package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_VALID_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.SECOND_VALID_DEADLINE;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.TaskUntilDeadlinePredicate;

public class ReminderCommandTest {

    private TaskUntilDeadlinePredicate firstPredicate = new TaskUntilDeadlinePredicate(FIRST_VALID_DEADLINE);
    private TaskUntilDeadlinePredicate secondPredicate = new TaskUntilDeadlinePredicate(SECOND_VALID_DEADLINE);

    @Test
    public void constructor_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ReminderCommand(null));
    }

    @Test
    public void equals() {
        final ReminderCommand firstReminderCommand = new ReminderCommand(firstPredicate);
        final ReminderCommand secondReminderCommand = new ReminderCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstReminderCommand.equals(firstReminderCommand));

        // same values -> returns true
        ReminderCommand firstReminderCommandCopy = new ReminderCommand(firstPredicate);
        assertTrue(firstReminderCommand.equals(firstReminderCommandCopy));

        // different types -> returns false
        assertFalse(firstReminderCommand.equals(1));

        // null -> returns false
        assertFalse(firstReminderCommand.equals(null));

        // different person -> returns false
        assertFalse(firstReminderCommand.equals(secondReminderCommand));
    }
}

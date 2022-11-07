package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_VALID_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.SECOND_VALID_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.THIRD_VALID_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTasks;
import static seedu.address.testutil.TypicalTasks.getTypicalTasksSortedByDeadline;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskUntilDeadlinePredicate;

public class ReminderCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private TaskUntilDeadlinePredicate firstPredicate = new TaskUntilDeadlinePredicate(FIRST_VALID_DEADLINE);
    private TaskUntilDeadlinePredicate secondPredicate = new TaskUntilDeadlinePredicate(SECOND_VALID_DEADLINE);

    private Deadline lastDeadline = new Deadline("25-12-2029");

    @Test
    public void constructor_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ReminderCommand(null));
    }

    @Test
    public void execute_earliestDeadlineUnfilteredList_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateFilteredTaskList(p -> false);

        TaskUntilDeadlinePredicate earliestDeadlinePredicate = new TaskUntilDeadlinePredicate(THIRD_VALID_DEADLINE);

        ReminderCommand reminderCommand = new ReminderCommand(earliestDeadlinePredicate);

        String expectedMessage = String.format(ReminderCommand.MESSAGE_REMINDER_SUCCESS, reminderCommand);

        assertCommandSuccess(reminderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_lastDeadlineUnfilteredList_success() {
        TaskUntilDeadlinePredicate lastDeadlinePredicate = new TaskUntilDeadlinePredicate(lastDeadline);

        ReminderCommand reminderCommand = new ReminderCommand(lastDeadlinePredicate);

        String expectedMessage = String.format(ReminderCommand.MESSAGE_REMINDER_SUCCESS, reminderCommand);

        assertCommandSuccess(reminderCommand, model, expectedMessage, model);
        assertEquals(getTypicalTasks(), model.getFilteredTaskList());
    }

    @Test
    public void execute_lastDeadlineSortedList_success() {
        Model sortedModel = new ModelManager(new AddressBook(), new UserPrefs());
        for (Task task : getTypicalTasksSortedByDeadline()) {
            sortedModel.addTask(task);
        }

        TaskUntilDeadlinePredicate lastDeadlinePredicate = new TaskUntilDeadlinePredicate(lastDeadline);

        ReminderCommand reminderCommand = new ReminderCommand(lastDeadlinePredicate);

        String expectedMessage = String.format(ReminderCommand.MESSAGE_REMINDER_SUCCESS, reminderCommand);

        assertCommandSuccess(reminderCommand, sortedModel, expectedMessage, sortedModel);
        assertEquals(getTypicalTasksSortedByDeadline(), sortedModel.getFilteredTaskList());
    }

    @Test
    public void execute_validDeadlineEmptyList_success() {
        Model noTasks = new ModelManager(new AddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());

        ReminderCommand reminderCommand = new ReminderCommand(firstPredicate);

        String expectedMessage = String.format(ReminderCommand.MESSAGE_REMINDER_SUCCESS, reminderCommand);

        assertCommandSuccess(reminderCommand, noTasks, expectedMessage, expectedModel);
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

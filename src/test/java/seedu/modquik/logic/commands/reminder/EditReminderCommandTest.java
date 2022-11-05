package seedu.modquik.logic.commands.reminder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modquik.logic.commands.CommandTestUtil.DESC_REMINDER1;
import static seedu.modquik.logic.commands.CommandTestUtil.DESC_REMINDER2;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_DESCRIPTION_REMINDER1;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_NAME_REMINDER1;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_PRIORITY_REMINDER1;
import static seedu.modquik.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.modquik.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modquik.testutil.TypicalIndexes.INDEX_FIRST_REMINDER;
import static seedu.modquik.testutil.TypicalIndexes.INDEX_SECOND_REMINDER;
import static seedu.modquik.testutil.TypicalReminders.getTypicalModQuik;

import org.junit.jupiter.api.Test;

import seedu.modquik.commons.core.Messages;
import seedu.modquik.commons.core.index.Index;
import seedu.modquik.logic.commands.ClearCommand;
import seedu.modquik.logic.commands.reminder.EditReminderCommand.EditReminderDescriptor;
import seedu.modquik.model.ModQuik;
import seedu.modquik.model.Model;
import seedu.modquik.model.ModelManager;
import seedu.modquik.model.UserPrefs;
import seedu.modquik.model.reminder.Reminder;
import seedu.modquik.testutil.EditReminderDescriptorBuilder;
import seedu.modquik.testutil.ReminderBuilder;

public class EditReminderCommandTest {

    private Model model = new ModelManager(getTypicalModQuik(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Reminder editedReminder = new ReminderBuilder().build();
        EditReminderDescriptor descriptor = new EditReminderDescriptorBuilder(editedReminder).build();
        EditReminderCommand editReminderCommand = new EditReminderCommand(INDEX_FIRST_REMINDER, descriptor);

        String expectedMessage = String.format(EditReminderCommand.MESSAGE_EDIT_REMINDER_SUCCESS, editedReminder);

        Model expectedModel = new ModelManager(new ModQuik(model.getModQuik()), new UserPrefs());
        expectedModel.setReminder(model.getFilteredReminderList().get(0), editedReminder);

        assertCommandSuccess(editReminderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastReminder = Index.fromOneBased(model.getFilteredReminderList().size());
        Reminder lastReminder = model.getFilteredReminderList().get(indexLastReminder.getZeroBased());

        ReminderBuilder reminderInList = new ReminderBuilder(lastReminder);
        Reminder editedReminder = reminderInList.withName(VALID_NAME_REMINDER1).withPriority(VALID_PRIORITY_REMINDER1)
                .withDescription(VALID_DESCRIPTION_REMINDER1).build();

        EditReminderDescriptor descriptor = new EditReminderDescriptorBuilder().withName(VALID_NAME_REMINDER1)
                .withPriority(VALID_PRIORITY_REMINDER1).withDescription(VALID_DESCRIPTION_REMINDER1).build();
        EditReminderCommand editReminderCommand = new EditReminderCommand(indexLastReminder, descriptor);

        String expectedMessage = String.format(EditReminderCommand.MESSAGE_EDIT_REMINDER_SUCCESS, editedReminder);

        Model expectedModel = new ModelManager(new ModQuik(model.getModQuik()), new UserPrefs());
        expectedModel.setReminder(lastReminder, editedReminder);

        assertCommandSuccess(editReminderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditReminderCommand editReminderCommand = new EditReminderCommand(INDEX_FIRST_REMINDER,
                new EditReminderDescriptor());

        assertCommandFailure(editReminderCommand, model, Messages.MESSAGE_UNCHANGED_FIELD);
    }

    @Test
    public void execute_duplicateReminderUnfilteredList_failure() {
        Reminder firstReminder = model.getFilteredReminderList().get(INDEX_FIRST_REMINDER.getZeroBased());
        EditReminderDescriptor descriptor = new EditReminderDescriptorBuilder(firstReminder).build();
        EditReminderCommand editReminderCommand = new EditReminderCommand(INDEX_SECOND_REMINDER, descriptor);

        assertCommandFailure(editReminderCommand, model, EditReminderCommand.MESSAGE_DUPLICATE_REMINDER);
    }

    @Test
    public void execute_invalidReminderIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredReminderList().size() + 1);
        EditReminderDescriptor descriptor = new EditReminderDescriptorBuilder().withName(VALID_NAME_REMINDER1).build();
        EditReminderCommand editReminderCommand = new EditReminderCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editReminderCommand, model, Messages.MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditReminderCommand standardCommand = new EditReminderCommand(INDEX_FIRST_REMINDER, DESC_REMINDER1);

        // same values -> returns true
        EditReminderDescriptor copyDescriptor = new EditReminderDescriptor(DESC_REMINDER1);
        EditReminderCommand commandWithSameValues = new EditReminderCommand(INDEX_FIRST_REMINDER, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand("all")));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditReminderCommand(INDEX_SECOND_REMINDER, DESC_REMINDER1)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditReminderCommand(INDEX_FIRST_REMINDER, DESC_REMINDER2)));
    }
}

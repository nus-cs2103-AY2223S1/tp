package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX;
import static seedu.address.logic.commands.ReminderDeleteCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalReminders.VALID_REMINDER_MEETING;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;
import seedu.address.model.person.TargetPerson;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderList;

class ReminderDeleteCommandTest {
    @Test
    public void constructor_null_nullPointerException() {
        assertThrows(NullPointerException.class, () -> new ReminderDeleteCommand(null));
    }

    @Test
    public void execute_invalidIndex() throws CommandException {
        ReminderDeleteCommandTest.ModelStubDeleteCommand
                modelStub = new ModelStubDeleteCommand();
        Index index = Index.fromZeroBased(0);
        CommandException exception =
                assertThrows(CommandException.class, () -> new ReminderDeleteCommand(index).execute(modelStub));
        assertEquals(MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX, exception.getMessage());
    }

    @Test
    public void execute_reminderExists() throws CommandException {
        ReminderDeleteCommandTest.ModelStubDeleteCommand
                modelStub = new ModelStubDeleteCommand();
        Index index = Index.fromZeroBased(0);
        modelStub.addReminder(VALID_REMINDER_MEETING);
        assertEquals(String.format(MESSAGE_SUCCESS, VALID_REMINDER_MEETING),
                new ReminderDeleteCommand(index).execute(modelStub).getFeedbackToUser());
    }

    @Test
    public void equals() {
        Index index0 = Index.fromZeroBased(0);
        Index index1 = Index.fromZeroBased(1);

        ReminderDeleteCommand reminderDeleteCommand1 = new ReminderDeleteCommand(index0);
        ReminderDeleteCommand reminderDeleteCommand2 = new ReminderDeleteCommand(index0);
        ReminderDeleteCommand reminderDeleteCommand3 = new ReminderDeleteCommand(index1);

        // same index -> return true
        assertTrue(reminderDeleteCommand1.equals(reminderDeleteCommand2));

        // different indices -> return false
        assertFalse(reminderDeleteCommand1.equals(reminderDeleteCommand3));

        // null -> return false
        assertFalse(reminderDeleteCommand1.equals(null));

    }

    private class ModelStubDeleteCommand extends CommandTestUtil.ModelStub {
        private TargetPerson targetPerson = new TargetPerson();
        private ReminderList reminderList = new ReminderList();
        private ReminderList targetPersonReminderList = new ReminderList();

        @Override
        public void setTargetPerson(Person person) {
            targetPerson.set(person);
        }

        @Override
        public void deleteReminder(Reminder reminder) {
            reminderList.delete(reminder);
            if (isTargetPersonReminder(reminder)) {
                targetPersonReminderList.delete(reminder);
            }
        }

        @Override
        public void clearCurrentReminderList() {
            if (targetPerson.isPresent()) {
                reminderList.deleteRemindersWithNameAndPhone(targetPerson.get().getName(),
                        targetPerson.get().getPhone());
                targetPersonReminderList.clear();
            } else {
                reminderList.clear();
            }
        }

        @Override
        public void addReminder(Reminder reminder) {
            reminderList.add(reminder);
            if (isTargetPersonReminder(reminder)) {
                targetPersonReminderList.add(reminder);
            }
        }

        @Override
        public boolean hasTargetPerson() {
            return targetPerson.isPresent();
        }

        @Override
        public Person getTargetPerson() {
            return targetPerson.get();
        }

        @Override
        public ObservableList<Reminder> getReminderListAsObservableList() {
            return reminderList.getAllReminders();
        }

        @Override
        public ObservableList<Reminder> getTargetPersonReminderListAsObservableList() {
            return targetPersonReminderList.getAllReminders();
        }

        @Override
        public ObservableList<Reminder> getCurrentReminderList() {
            if (targetPerson.isPresent()) {
                return getTargetPersonReminderListAsObservableList();
            } else {
                return getReminderListAsObservableList();
            }
        }

        private boolean isTargetPersonReminder(Reminder reminder) {
            return hasTargetPerson()
                    && reminder.matchesNameAndPhone(targetPerson.get().getName(), targetPerson.get().getPhone());
        }

    }
}

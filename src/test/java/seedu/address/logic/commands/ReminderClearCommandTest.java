package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.ReminderClearCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.ReminderClearCommand.MESSAGE_SUCCESS_TARGET_PERSON_LIST;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalReminders.REMINDER_PROMOTION_DATETIME;
import static seedu.address.testutil.TypicalReminders.REMINDER_PROMOTION_DESCRIPTION;
import static seedu.address.testutil.TypicalReminders.VALID_REMINDER_MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;
import seedu.address.model.person.TargetPerson;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderList;

class ReminderClearCommandTest {
    @Test
    public void execute_nullModel_nullPointerException() throws CommandException {
        assertThrows(NullPointerException.class, () -> new ReminderClearCommand().execute(null));
    }

    @Test
    public void execute_targetPersonEmptyList_success() throws CommandException {
        ReminderClearCommandTest.ModelStubClearCommand
                modelStub = new ReminderClearCommandTest.ModelStubClearCommand();
        modelStub.setTargetPerson(ALICE);

        CommandResult commandResult =
                new ReminderClearCommand().execute(modelStub);
        assertEquals(0, modelStub.targetPersonReminderList.size());
        assertEquals(String.format(MESSAGE_SUCCESS_TARGET_PERSON_LIST, modelStub.getTargetPerson().getName().fullName),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_targetPersonNonEmptyList_success() throws CommandException {
        ReminderClearCommandTest.ModelStubClearCommand
                modelStub = new ReminderClearCommandTest.ModelStubClearCommand();
        modelStub.setTargetPerson(ALICE);
        modelStub.addReminder(VALID_REMINDER_MEETING);

        CommandResult commandResult =
                new ReminderClearCommand().execute(modelStub);
        assertEquals(0, modelStub.targetPersonReminderList.size());
        assertEquals(String.format(MESSAGE_SUCCESS_TARGET_PERSON_LIST, modelStub.getTargetPerson().getName().fullName),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_noTargetPersonEmptyList_success() throws CommandException {
        ReminderClearCommandTest.ModelStubClearCommand
                modelStub = new ReminderClearCommandTest.ModelStubClearCommand();

        CommandResult commandResult =
                new ReminderClearCommand().execute(modelStub);
        assertEquals(0, modelStub.reminderList.size());
        assertEquals(MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_noTargetPersonNonEmptyList_success() throws CommandException {
        ReminderClearCommandTest.ModelStubClearCommand
                modelStub = new ReminderClearCommandTest.ModelStubClearCommand();
        modelStub.addReminder(VALID_REMINDER_MEETING);

        CommandResult commandResult =
                new ReminderClearCommand().execute(modelStub);
        assertEquals(0, modelStub.reminderList.size());
        assertEquals(MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        ReminderClearCommand reminderClearCommand1 = new ReminderClearCommand();
        ReminderClearCommand reminderClearCommand2 = new ReminderClearCommand();
        ReminderCreateCommand reminderClearCommand3 = new ReminderCreateCommand(REMINDER_PROMOTION_DESCRIPTION,
                REMINDER_PROMOTION_DATETIME);

        assertTrue(reminderClearCommand1.equals(reminderClearCommand1));
        assertTrue(reminderClearCommand1.equals(reminderClearCommand2));
        assertFalse(reminderClearCommand1.equals(reminderClearCommand3));
    }

    private class ModelStubClearCommand extends CommandTestUtil.ModelStub {
        private TargetPerson targetPerson = new TargetPerson();
        private ReminderList reminderList = new ReminderList();
        private ReminderList targetPersonReminderList = new ReminderList();

        @Override
        public void setTargetPerson(Person person) {
            targetPerson.set(person);
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

        private boolean isTargetPersonReminder(Reminder reminder) {
            return hasTargetPerson()
                    && reminder.matchesNameAndPhone(targetPerson.get().getName(), targetPerson.get().getPhone());
        }

    }
}

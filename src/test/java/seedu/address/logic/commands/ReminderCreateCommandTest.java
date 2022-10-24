package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.commands.ReminderCreateCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalReminders.REMINDER_MEETING_DATETIME;
import static seedu.address.testutil.TypicalReminders.REMINDER_MEETING_DESCRIPTION;
import static seedu.address.testutil.TypicalReminders.REMINDER_PROMOTION_DATETIME;
import static seedu.address.testutil.TypicalReminders.REMINDER_PROMOTION_DESCRIPTION;
import static seedu.address.testutil.TypicalReminders.VALID_REMINDER_MEETING;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.TargetPerson;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderList;
import seedu.address.testutil.AddressBookBuilder;

class ReminderCreateCommandTest {
    @Test
    public void constructor_null_nullPointerException() {
        Index index = Index.fromZeroBased(0);

        assertThrows(NullPointerException.class, ()
                -> new ReminderCreateCommand(null, REMINDER_MEETING_DESCRIPTION, REMINDER_MEETING_DATETIME));
        assertThrows(NullPointerException.class, ()
                -> new ReminderCreateCommand(index, null, REMINDER_MEETING_DATETIME));
        assertThrows(NullPointerException.class, ()
                -> new ReminderCreateCommand(index, REMINDER_MEETING_DESCRIPTION, null));

        assertThrows(NullPointerException.class, ()
                -> new ReminderCreateCommand(null, REMINDER_MEETING_DATETIME));
        assertThrows(NullPointerException.class, ()
                -> new ReminderCreateCommand(REMINDER_MEETING_DESCRIPTION, null));
    }

    @Test
    public void execute_invalidIndex_commandException() {
        ReminderCreateCommandTest.ModelStubCreateCommand
                modelStub = new ModelStubCreateCommand();
        Index index = Index.fromZeroBased(10);
        modelStub.setTargetPerson(ALICE);

        CommandException exception = assertThrows(CommandException.class, () -> new ReminderCreateCommand(index,
                REMINDER_MEETING_DESCRIPTION, REMINDER_MEETING_DATETIME).execute(modelStub));
        assertEquals(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, exception.getMessage());
    }

    @Test
    public void execute_duplicateReminder_commandException() {
        ReminderCreateCommandTest.ModelStubCreateCommand
                modelStub = new ModelStubCreateCommand();
        modelStub.addReminder(VALID_REMINDER_MEETING);
        modelStub.setTargetPerson(ALICE);

        CommandException exception = assertThrows(CommandException.class, ()
                -> new ReminderCreateCommand(REMINDER_MEETING_DESCRIPTION, REMINDER_MEETING_DATETIME)
                .execute(modelStub));
        assertEquals(String.format(Messages.MESSAGE_REMINDER_ALREADY_EXIST,
                VALID_REMINDER_MEETING), exception.getMessage());
    }

    @Test
    public void execute_uniqueMessage_success() throws CommandException {
        ReminderCreateCommandTest.ModelStubCreateCommand
                modelStub = new ModelStubCreateCommand();
        modelStub.setTargetPerson(ALICE);
        ReminderCreateCommand reminderCreateCommand =
                new ReminderCreateCommand(REMINDER_MEETING_DESCRIPTION, REMINDER_MEETING_DATETIME);
        assertEquals(String.format(MESSAGE_SUCCESS, VALID_REMINDER_MEETING),
                reminderCreateCommand.execute(modelStub).getFeedbackToUser());
    }

    @Test
    public void equals() {
        ReminderCreateCommand reminderCreateCommand1 = new ReminderCreateCommand(REMINDER_MEETING_DESCRIPTION,
                REMINDER_MEETING_DATETIME);
        ReminderCreateCommand reminderCreateCommand2 = new ReminderCreateCommand(REMINDER_MEETING_DESCRIPTION,
                REMINDER_MEETING_DATETIME);
        ReminderCreateCommand reminderCreateCommand3 = new ReminderCreateCommand(REMINDER_PROMOTION_DESCRIPTION,
                REMINDER_MEETING_DATETIME);
        ReminderCreateCommand reminderCreateCommand4 = new ReminderCreateCommand(REMINDER_MEETING_DESCRIPTION,
                REMINDER_PROMOTION_DATETIME);
        ReminderCreateCommand reminderCreateCommand5 = new ReminderCreateCommand(REMINDER_PROMOTION_DESCRIPTION,
                REMINDER_PROMOTION_DATETIME);

        assertTrue(reminderCreateCommand1.equals(reminderCreateCommand1));
        assertTrue(reminderCreateCommand1.equals(reminderCreateCommand2));
        assertFalse(reminderCreateCommand1.equals(reminderCreateCommand3));
        assertFalse(reminderCreateCommand1.equals(reminderCreateCommand4));
        assertFalse(reminderCreateCommand1.equals(reminderCreateCommand5));
    }

    private class ModelStubCreateCommand extends CommandTestUtil.ModelStub {
        private AddressBook addressBook = createAddressBook();
        private TargetPerson targetPerson = new TargetPerson();
        private ReminderList reminderList = new ReminderList();
        private ReminderList targetPersonReminderList = new ReminderList();
        private FilteredList<Person> filteredPersons = new FilteredList<Person>(this.addressBook.getPersonList());

        public AddressBook createAddressBook() {
            AddressBookBuilder addressBookBuilder = new AddressBookBuilder();
            addressBookBuilder.withPerson(ALICE);
            addressBookBuilder.withPerson(BENSON);
            addressBookBuilder.withPerson(CARL);
            return addressBookBuilder.build();
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return filteredPersons;
        }

        @Override
        public void setTargetPerson(Person person) {
            targetPerson.set(person);
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
        public boolean reminderExists(Reminder reminder) {
            return reminderList.contains(reminder);
        }

        private boolean isTargetPersonReminder(Reminder reminder) {
            return hasTargetPerson()
                    && reminder.matchesNameAndPhone(targetPerson.get().getName(), targetPerson.get().getPhone());
        }

    }
}

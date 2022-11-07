package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.util.Pair;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Reminder;
import seedu.address.testutil.PersonBuilder;

public class ReminderCommandTest {
    private Reminder validReminder = new Reminder(CommandTestUtil.VALID_REMINDER_BOB,
            CommandTestUtil.VALID_REMINDER_DATE_BOB);

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {

        // index null
        assertThrows(NullPointerException.class, () -> new ReminderCommand(null, validReminder));

        // reminder null
        assertThrows(NullPointerException.class, () -> new ReminderCommand(INDEX_FIRST_PERSON, null));

        // index and reminder null
        assertThrows(NullPointerException.class, () -> new ReminderCommand(null, null));
    }

    @Test
    public void execute_reminder_addSuccessful() throws Exception {
        ModelStubAcceptingReminderAdded modelStub = new ModelStubAcceptingReminderAdded();
        Person validPerson = new PersonBuilder().build();
        new AddCommand(validPerson).execute(modelStub);

        CommandResult commandResult = new ReminderCommand(INDEX_FIRST_PERSON, validReminder).execute(modelStub);

        assertEquals(String.format(ReminderCommand.MESSAGE_ADD_REMINDER_SUCCESS,
                validPerson), commandResult.getFeedbackToUser());
        assertTrue(modelStub.remindersAdded.contains(new Pair<>(validPerson, validReminder)));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public SortedList<Pair<Person, Reminder>> getSortedReminderPairs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addReminder(Person person, Reminder reminder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Reminder deleteReminder(Index targetIndex) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePersonReminders(Person personToDelete) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the reminder being added.
     */
    private class ModelStubAcceptingReminderAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();
        final ArrayList<Pair<Person, Reminder>> remindersAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public void addReminder(Person person, Reminder reminder) {
            // Adds the reminder linked to the person.
            requireNonNull(person);
            requireNonNull(reminder);
            remindersAdded.add(new Pair<>(person, reminder));
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return FXCollections.observableList(personsAdded);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}

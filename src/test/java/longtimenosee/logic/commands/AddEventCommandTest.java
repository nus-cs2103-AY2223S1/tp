package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;
import static longtimenosee.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import longtimenosee.commons.core.GuiSettings;
import longtimenosee.logic.commands.exceptions.CommandException;
import longtimenosee.model.Model;
import longtimenosee.model.ReadOnlyAddressBook;
import longtimenosee.model.ReadOnlyUserPrefs;
import longtimenosee.model.event.Date;
import longtimenosee.model.event.Description;
import longtimenosee.model.event.Duration;
import longtimenosee.model.event.Event;
import longtimenosee.model.person.Name;
import longtimenosee.model.person.Person;
import longtimenosee.model.policy.FinancialAdvisorIncome;
import longtimenosee.model.policy.Policy;
import longtimenosee.testutil.EventBuilder;
import longtimenosee.testutil.PersonBuilder;

public class AddEventCommandTest {


    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null));
    }


    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        Event validEvent = new Event(new Description("Lunch with Amy"), new Name("Amy Bee"),
                new Date("2020-10-10"), new Duration("10:00__11:00"));

        AddEventCommand addEventCommand = new AddEventCommand(validEvent);
        AddEventCommandTest.ModelStub modelStub = new AddEventCommandTest.SingleModelStub(validPerson, validEvent);

        assertThrows(CommandException.class, AddEventCommand.MESSAGE_DUPLICATE_EVENT, () ->
                addEventCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Event withAlice = new EventBuilder().build();
        Event withBenson = new EventBuilder().withName("Benson Meier")
                .withDate("2022-11-11")
                .withDescription("Coffee with Benson")
                .withDuration("15:00__16:00")
                .build();
        AddEventCommand addAliceEventCommand = new AddEventCommand(withAlice);
        AddEventCommand addBensonEventCommand = new AddEventCommand(withBenson);

        // same object -> returns true
        assertTrue(addAliceEventCommand.equals(addAliceEventCommand));

        // same values -> returns true
        AddEventCommand addAliceCommandCopy = new AddEventCommand(withAlice);
        assertTrue(addAliceEventCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceEventCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceEventCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceEventCommand.equals(addBensonEventCommand));
    }



    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        public FinancialAdvisorIncome getIncome() {
            return new FinancialAdvisorIncome();
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
        public void pinPerson(Person p) {
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
        public void sort(Comparator comparator) {
            throw new AssertionError("This method should not be called");
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
        public boolean hasPolicy(Policy toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPolicy(Policy toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePolicy(Policy toDelete) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPolicy(Policy target, Policy editedPolicy) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Policy> getFilteredPolicyList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPolicyList(Predicate<Policy> predicate) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void addEvent(Event toAdd, String personName) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean hasEventOverlap(Event toAdd) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public List<Event> listEventsOverlap(Event toAdd) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean hasEvent(Event toAdd) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void deleteEvent(Event toDelete) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public List<Event> listEventsSameDay(Event toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * @return
         */
        @Override
        public List<Event> calendarView() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void removeEventsUnderPerson(Person personToDelete) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person and event
     */
    private class SingleModelStub extends AddEventCommandTest.ModelStub {
        private final Person person;
        private Event event;

        SingleModelStub(Person person, Event event) {
            requireNonNull(person);
            requireNonNull(event);
            this.person = person;
            this.event = event;
        }

        SingleModelStub(Person person) {
            requireNonNull(person);
            this.person = person;
            this.event = null;
        }

        public void addEvent(Event event) {
            requireNonNull(event);
            this.event = event;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return this.event.isSameEvent(event);
        }
    }
}

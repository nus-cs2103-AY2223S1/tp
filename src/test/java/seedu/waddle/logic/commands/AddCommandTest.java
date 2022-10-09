package seedu.waddle.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.waddle.commons.core.GuiSettings;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.AddressBook;
import seedu.waddle.model.Model;
import seedu.waddle.model.ReadOnlyAddressBook;
import seedu.waddle.model.ReadOnlyUserPrefs;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Itinerary validItinerary = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validItinerary).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validItinerary), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validItinerary), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Itinerary validItinerary = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validItinerary);
        ModelStub modelStub = new ModelStubWithPerson(validItinerary);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Itinerary alice = new PersonBuilder().withName("Alice").build();
        Itinerary bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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
        public void addPerson(Itinerary itinerary) {
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
        public boolean hasPerson(Itinerary itinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Itinerary target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Itinerary target, Itinerary editedItinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Itinerary> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Itinerary> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Itinerary itinerary;

        ModelStubWithPerson(Itinerary itinerary) {
            requireNonNull(itinerary);
            this.itinerary = itinerary;
        }

        @Override
        public boolean hasPerson(Itinerary itinerary) {
            requireNonNull(itinerary);
            return this.itinerary.isSamePerson(itinerary);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Itinerary> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Itinerary itinerary) {
            requireNonNull(itinerary);
            return personsAdded.stream().anyMatch(itinerary::isSamePerson);
        }

        @Override
        public void addPerson(Itinerary itinerary) {
            requireNonNull(itinerary);
            personsAdded.add(itinerary);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}

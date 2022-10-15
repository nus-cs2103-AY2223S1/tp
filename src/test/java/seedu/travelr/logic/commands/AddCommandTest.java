package seedu.travelr.logic.commands;


import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.travelr.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.travelr.commons.core.GuiSettings;
import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.model.AddressBook;
import seedu.travelr.model.Model;
import seedu.travelr.model.ReadOnlyAddressBook;
import seedu.travelr.model.ReadOnlyUserPrefs;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;
import seedu.travelr.testutil.TripBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_tripAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTripAdded modelStub = new ModelStubAcceptingTripAdded();
        Trip validTrip = new TripBuilder().build();

        CommandResult commandResult = new AddCommand(validTrip).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validTrip), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTrip), modelStub.tripsAdded);
    }

    @Test
    public void execute_duplicateTrip_throwsCommandException() {
        Trip validTrip = new TripBuilder().build();
        AddCommand addCommand = new AddCommand(validTrip);
        ModelStub modelStub = new ModelStubWithTrip(validTrip);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_TRIP, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Trip alice = new TripBuilder().withTitle("Alice").build();
        Trip bob = new TripBuilder().withTitle("Bob").build();
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

        // different trip -> returns false
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
        public void addTrip(Trip trip) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
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
        public boolean hasTrip(Trip trip) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void deleteEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Event getEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Trip getTrip(Trip trip) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTrip(Trip target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTrip(Trip target, Trip editedTrip) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEvent(Event target, Event editedEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Trip> getFilteredTripList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTripList(Predicate<Trip> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single trip.
     */
    private class ModelStubWithTrip extends ModelStub {
        private final Trip trip;

        ModelStubWithTrip(Trip trip) {
            requireNonNull(trip);
            this.trip = trip;
        }

        @Override
        public boolean hasTrip(Trip trip) {
            requireNonNull(trip);
            return this.trip.isSameTrip(trip);
        }
    }

    /**
     * A Model stub that always accept the trip being added.
     */
    private class ModelStubAcceptingTripAdded extends ModelStub {
        final ArrayList<Trip> tripsAdded = new ArrayList<>();

        @Override
        public boolean hasTrip(Trip trip) {
            requireNonNull(trip);
            return tripsAdded.stream().anyMatch(trip::isSameTrip);
        }

        @Override
        public void addTrip(Trip trip) {
            requireNonNull(trip);
            tripsAdded.add(trip);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}

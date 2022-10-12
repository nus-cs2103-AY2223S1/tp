package seedu.travelr.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;
import seedu.travelr.model.trip.exceptions.DuplicateTripException;
import seedu.travelr.testutil.TripBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ANTARCTICA;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_EVENT_SIGHTSEEING;
import static seedu.travelr.testutil.Assert.assertThrows;
import static seedu.travelr.testutil.TypicalTrips.SUN;
import static seedu.travelr.testutil.TypicalTrips.getTypicalAddressBook;

public class AddressBookTest {

    private final seedu.travelr.model.AddressBook addressBook = new seedu.travelr.model.AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getTripList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateTrips_throwsDuplicateTripException() {
        // Two trips with the same identity fields
        Trip editedAlice = new TripBuilder(SUN).withDescription(VALID_DESCRIPTION_ANTARCTICA).withEvents(VALID_EVENT_SIGHTSEEING)
                .build();
        List<Trip> newTrips = Arrays.asList(SUN, editedAlice);
        AddressBookStub newData = new AddressBookStub(newTrips);

        assertThrows(DuplicateTripException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasTrip_nullTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTrip(null));
    }

    @Test
    public void hasTrip_tripNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTrip(SUN));
    }

    @Test
    public void hasTrip_tripInAddressBook_returnsTrue() {
        addressBook.addTrip(SUN);
        assertTrue(addressBook.hasTrip(SUN));
    }

    @Test
    public void hasTrip_tripWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addTrip(SUN);
        Trip editedAlice = new TripBuilder(SUN).withDescription(VALID_DESCRIPTION_ANTARCTICA).withEvents(VALID_EVENT_SIGHTSEEING)
                .build();
        assertTrue(addressBook.hasTrip(editedAlice));
    }

    @Test
    public void getTripList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTripList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose trips list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Trip> trips = FXCollections.observableArrayList();

        AddressBookStub(Collection<Trip> trips) {
            this.trips.setAll(trips);
        }

        @Override
        public ObservableList<Trip> getTripList() {
            return trips;
        }

        @Override
        public ObservableList<Event> getEventList() {
            return null;
        }
    }

}

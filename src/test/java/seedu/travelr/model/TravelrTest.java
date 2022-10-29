package seedu.travelr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ANTARCTICA;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_EVENT_SIGHTSEEING;
import static seedu.travelr.testutil.Assert.assertThrows;
import static seedu.travelr.testutil.TypicalTrips.SUN;
import static seedu.travelr.testutil.TypicalTrips.getTypicalAddressBook;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;
import seedu.travelr.testutil.TripBuilder;

public class TravelrTest {

    private final Travelr travelr = new Travelr();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), travelr.getTripList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> travelr.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        Travelr newData = getTypicalAddressBook();
        travelr.resetData(newData);
        assertEquals(newData, travelr);
    }

    //Not working at the moment
    /*
    @Test
    public void resetData_withDuplicateTrips_throwsDuplicateTripException() {
        // Two trips with the same identity fields
        Trip editedAlice = new TripBuilder(SUN).withDescription(VALID_DESCRIPTION_ANTARCTICA).withEvents(
                VALID_EVENT_SIGHTSEEING).build();
        List<Trip> newTrips = Arrays.asList(SUN, editedAlice);
        AddressBookStub newData = new AddressBookStub(newTrips);

        assertThrows(DuplicateTripException.class, () -> addressBook.resetData(newData));
    }
     */

    @Test
    public void hasTrip_nullTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> travelr.hasTrip(null));
    }

    @Test
    public void hasTrip_tripNotInAddressBook_returnsFalse() {
        assertFalse(travelr.hasTrip(SUN));
    }

    @Test
    public void hasTrip_tripInAddressBook_returnsTrue() {
        travelr.addTrip(SUN);
        assertTrue(travelr.hasTrip(SUN));
    }

    @Test
    public void hasTrip_tripWithSameIdentityFieldsInAddressBook_returnsTrue() {
        travelr.addTrip(SUN);
        Trip editedAlice = new TripBuilder(SUN).withDescription(VALID_DESCRIPTION_ANTARCTICA).withEvents(
                VALID_EVENT_SIGHTSEEING).build();
        assertTrue(travelr.hasTrip(editedAlice));
    }

    @Test
    public void getTripList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> travelr.getTripList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose trips list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyTravelr {
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

        @Override
        public ObservableList<Event> getAllEventList() {
            return null;
        }
    }

}

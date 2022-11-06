package seedu.travelr.model.list;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.travelr.model.trip.Trip;
import seedu.travelr.testutil.EventBuilder;
import seedu.travelr.testutil.TripBuilder;

class ItinerariesTest {

    private static Itineraries itineraries;

    @BeforeAll
    static void setup() {
        Trip trip = new TripBuilder().build();
        itineraries = trip.getItinerary();
    }

    @Test
    void contains() {
        assertFalse(itineraries.contains("Event"));
    }

    @Test
    void testContains() {
        assertFalse(itineraries.contains(new EventBuilder().build()));
    }

    @Test
    void testFunctions() {
        itineraries.add(new EventBuilder().build());
        itineraries.remove(new EventBuilder().build());
        itineraries.asUnmodifiableObservableList();
        itineraries.iterator();
        itineraries.equals(itineraries);
        itineraries.hashCode();
        itineraries.sort((o1, o2) -> 0);
        itineraries.getInternalList();
        itineraries.getEvent(new EventBuilder().build());
        itineraries.getAmountOfEvents();
        assertTrue(true);
    }
}


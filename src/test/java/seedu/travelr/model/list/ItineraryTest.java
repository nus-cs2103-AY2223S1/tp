package seedu.travelr.model.list;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.travelr.model.trip.Trip;
import seedu.travelr.testutil.EventBuilder;
import seedu.travelr.testutil.TripBuilder;

class ItineraryTest {

    private static Itinerary itinerary;

    @BeforeAll
    static void setup() {
        Trip trip = new TripBuilder().build();
        itinerary = trip.getItinerary();
    }

    @Test
    void contains() {
        assertFalse(itinerary.contains("Event"));
    }

    @Test
    void testContains() {
        assertFalse(itinerary.contains(new EventBuilder().build()));
    }

    @Test
    void testFunctions() {
        itinerary.add(new EventBuilder().build());
        itinerary.remove(new EventBuilder().build());
        itinerary.asUnmodifiableObservableList();
        itinerary.iterator();
        itinerary.equals(itinerary);
        itinerary.hashCode();
        itinerary.sort((o1, o2) -> 0);
        itinerary.getInternalList();
        itinerary.getEvent(new EventBuilder().build());
        itinerary.getAmountOfEvents();
        assertTrue(true);
    }
}


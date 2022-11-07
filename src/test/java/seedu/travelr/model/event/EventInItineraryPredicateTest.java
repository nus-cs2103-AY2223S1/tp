package seedu.travelr.model.event;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.travelr.commons.core.index.Index;
import seedu.travelr.model.component.Description;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.list.Itineraries;

import static org.junit.jupiter.api.Assertions.*;

class EventInItineraryPredicateTest {
    private static EventInItineraryPredicate pred;
    private static Index index;
    private static Itineraries itinerary;
    private static Event event;

    @BeforeAll
    static void setup() {
        index = Index.fromOneBased(1);
        pred = new EventInItineraryPredicate(index);
        itinerary = new Itineraries();
        event = new Event(new Title("a"), new Description("b"));
        itinerary.add(event);
        pred.setItinerary(itinerary);
    }

    @Test
    void setItineraryTest() {
        pred.setItinerary(itinerary);
        assertTrue(true);
    }

    @Test
    void getIndexTest() {
        assertEquals(Index.fromZeroBased(0), index);
    }

    @Test
    void test_test() {
        assertTrue(pred.test(event));
    }

    @Test
    void testEquals() {
        assertEquals(pred, new EventInItineraryPredicate(index));
    }
}
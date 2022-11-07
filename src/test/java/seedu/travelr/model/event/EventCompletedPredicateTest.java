package seedu.travelr.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.travelr.model.trip.Trip;

class EventCompletedPredicateTest {

    private static FilteredList<Trip> completedTrips;
    private static EventCompletedPredicate pred;

    @BeforeAll
    static void setup() {
        completedTrips = FXCollections.<Trip>observableArrayList().filtered(t -> true);
        pred = new EventCompletedPredicate(completedTrips);
    }

    @Test
    void test_test() {
        assertFalse(pred.test(null));
    }

    @Test
    void testEquals() {
        assertEquals(pred, new EventCompletedPredicate(completedTrips));
    }
}
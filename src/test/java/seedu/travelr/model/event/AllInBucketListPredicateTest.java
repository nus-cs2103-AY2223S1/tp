package seedu.travelr.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;

class AllInBucketListPredicateTest {
    private static AllInBucketListPredicate pred;
    private static FilteredList<Event> events;

    @BeforeAll
    static void setup() {
        events = FXCollections.<Event>observableArrayList().filtered(t -> true);
        pred = new AllInBucketListPredicate(events);
    }

    @Test
    void test_test() {
        assertFalse(pred.test(null));
    }

    @Test
    void testEquals() {
        assertEquals(pred, new AllInBucketListPredicate(events));
    }
}

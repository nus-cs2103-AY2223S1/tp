package seedu.nutrigoals.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LocationTest {

    @Test
    void distTo() {
        Location a = new Location("A", "0, 0");
        Location b = new Location("B", "15, 0");
        Location c = new Location("C", "31, 0");
        assertTrue(a.distTo(b) < b.distTo(c));
    }
}

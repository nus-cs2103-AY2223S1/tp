package seedu.nutrigoals.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LocationTest {

    @Test
    void distTo() {
        Location A = new Location("A", "0, 0");
        Location B = new Location("B", "15, 0");
        Location C = new Location("C", "31, 0");
        assertTrue(A.distTo(B) < B.distTo(C));
    }
}

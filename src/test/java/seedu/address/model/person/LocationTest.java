package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LocationTest {

    @Test
    public void equals_sameObject() {
        Location singapore = new Location("Singapore");
        assertEquals(singapore, singapore);
    }

    @Test
    public void equals_differentObject_success() {
        Location singapore1 = new Location("Singapore");
        Location singapore2 = new Location("Singapore");
        assertEquals(singapore1, singapore2);
    }

    @Test
    public void hashcode() {
        int expected = "Singapore".hashCode();
        Location singapore = new Location("Singapore");
        assertEquals(singapore.hashCode(), expected);
    }
}

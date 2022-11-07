package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class PrefixTest {

    @Test
    public void hashcode() {
        final Prefix standardPrefix = new Prefix("h/");

        // same values -> returns same hashcode
        Prefix samePrefix = new Prefix("h/");
        assertEquals(standardPrefix.hashCode(), samePrefix.hashCode());

        // null prefix -> returns 0
        Prefix nullPrefix = new Prefix(null);
        assertEquals(nullPrefix.hashCode(), 0);

        // different addresses -> returns different hashcode
        assertNotEquals(standardPrefix.hashCode(), (new Prefix("i/")).hashCode());
    }
}

package seedu.boba.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class VersionedBobaBotTest {
    @Test
    public void constructor() {
        assertEquals(new VersionedBobaBot(null), new VersionedBobaBot(null, 20));
        assertNotEquals(new VersionedBobaBot(null), new VersionedBobaBot(null, 25));
    }

    @Test
    public void are_equals() {
        VersionedBobaBot bbbt = new VersionedBobaBot(null, 20);
        assertEquals(bbbt, bbbt);
        assertEquals(bbbt, new VersionedBobaBot(null));
    }
}

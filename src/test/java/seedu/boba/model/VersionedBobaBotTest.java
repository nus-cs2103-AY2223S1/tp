package seedu.boba.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class VersionedBobaBotTest {
    BobaBot bbt = new BobaBot();
    @Test
    public void constructor() {
        assertEquals(new VersionedBobaBot(bbt), new VersionedBobaBot(bbt, 20));
        assertNotEquals(new VersionedBobaBot(bbt), new VersionedBobaBot(bbt, 25));
    }

    @Test
    public void are_equals() {
        VersionedBobaBot bbbt = new VersionedBobaBot(bbt, 20);
        assertEquals(bbbt, bbbt);
        assertEquals(bbbt, new VersionedBobaBot(bbt));
    }
}

package seedu.boba.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class VersionedBobaBotTest {
    private static BobaBot bbt = new BobaBot();
    @Test
    public void constructor() {
        assertEquals(new VersionedBobaBot(bbt), new VersionedBobaBot(bbt, 20));
        assertNotEquals(new VersionedBobaBot(bbt), new VersionedBobaBot(bbt, 25));
    }

    @Test
    public void are_equals() {
        VersionedBobaBot versionedBobaBot = new VersionedBobaBot(bbt, 20);
        assertEquals(versionedBobaBot, versionedBobaBot);
        assertEquals(versionedBobaBot, new VersionedBobaBot(bbt));
    }
}

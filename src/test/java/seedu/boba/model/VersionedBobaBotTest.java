package seedu.boba.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class VersionedBobaBotTest {
    @Test
    public void constructor() {
        assertEquals(new VersionedBobaBot(null), new VersionedBobaBot(null, 20));
        assertNotEquals(new VersionedBobaBot(null), new VersionedBobaBot(null, 25));
    }

    @Test
    public void are_equals() {
        VersionedBobaBot bbbt=new VersionedBobaBot(null, 20);
        assertEquals(bbbt, bbbt);
        assertEquals(bbbt, new VersionedBobaBot(null));
        
    }
}

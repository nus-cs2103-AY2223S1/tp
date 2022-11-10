package seedu.address.model.commission;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class CompletionStatusTest {
    @Test
    public void constructor_anyBoolean_success() {
        assertDoesNotThrow(() -> new CompletionStatus(true));
        assertDoesNotThrow(() -> new CompletionStatus(false));
    }

    @Test
    public void equals() {
        assertEquals(new CompletionStatus(true), new CompletionStatus(true));
        assertEquals(new CompletionStatus(false), new CompletionStatus(false));
        assertNotEquals(new CompletionStatus(true), new CompletionStatus(false));
    }
}

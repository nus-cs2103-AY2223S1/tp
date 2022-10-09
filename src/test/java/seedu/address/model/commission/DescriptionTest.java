package seedu.address.model.commission;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DescriptionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_anyString_success() {
        // any description is valid
        assertDoesNotThrow(() -> new Description(""));
        assertDoesNotThrow(() -> new Description("non empty description"));
    }

    @Test
    public void equals() {
        String description = "test description";
        String differentDescription = "different description";
        assertEquals(new Description(description), new Description(description));
        assertNotEquals(new Description(description), new Description(differentDescription));
    }
}

package longtimenosee.model.event;

import static longtimenosee.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid addresses
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription("++--")); // non-alphanumeric characters
        assertFalse(Description.isValidDescription("  ")); // 2 spaces

        // valid addresses
        assertTrue(Description.isValidDescription("Lunch with Alice Pauline")); // normal description
        assertTrue(Description.isValidDescription("Lunch and Dinner and "
                + "Breakfast at 123 Street with Alice")); // long address
        assertTrue(Description.isValidDescription(("ABC 123 DEF 456"))); // alphanumeric
    }
}

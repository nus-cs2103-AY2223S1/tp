package seedu.address.model.entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentExceptio() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        //null
        assertThrows(NullPointerException.class, () -> new Description(null));

        //invalid description
        assertFalse(Description.isValidDescription(""));
        assertFalse(Description.isValidDescription(" "));

        String validDescription = "Food at hawker";
        //valid description
        assertTrue(Description.isValidDescription(validDescription));
    }
}

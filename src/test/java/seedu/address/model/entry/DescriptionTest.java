package seedu.address.model.entry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throws_IllegalArgumentException() {
        String invalidDescription= "";
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

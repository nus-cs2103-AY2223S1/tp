package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriorityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Priority(null));
    }

    @Test
    public void constructor_invalidPriorityName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Priority(invalidTagName));
    }

    @Test
    public void isValidPriorityName() {
        // null priority value
        assertThrows(NullPointerException.class, () -> Priority.isValidPriority(null));

        //invalid priority
        assertFalse(Priority.isValidPriority("")); //empty string
        assertFalse(Priority.isValidPriority("high**")); //valid priority with invalid character
        assertFalse(Priority.isValidPriority("14123high")); //numeric characters
        assertFalse(Priority.isValidPriority("high ")); //space in input

        //valid priority
        assertTrue(Priority.isValidPriority("high")); //all lowercase
        assertTrue(Priority.isValidPriority("LOW")); //all uppercase
        assertTrue(Priority.isValidPriority("NormaL")); //upper and lowercase
    }

}

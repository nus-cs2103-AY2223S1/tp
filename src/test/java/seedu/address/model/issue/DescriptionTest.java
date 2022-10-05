package seedu.address.model.issue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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
    public void isValidDescription() {
        //null description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        //invalid description
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only

        //valid description
        assertTrue(Description.isValidDescription("create class")); // alphabets only
        assertTrue(Description.isValidDescription("12345")); // numbers only
        assertTrue(Description.isValidDescription("^/*")); // non-alphanumeric characters only
        assertTrue(Description.isValidDescription("redo 2 classes")); // alphanumeric characters only
        assertTrue(Description.isValidDescription("a^2 = b^2 + c^2")); // all characters
        assertTrue(Description.isValidDescription("Complete Final Class")); // with capital letters
        assertTrue(Description.isValidDescription("Finish project class creation " +
                "before doing 2 sub-classes")); //long description
    }

}

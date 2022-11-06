package swift.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swift.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "~";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidDescription_invalidDescription_false() {
        // null name
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid name
        assertFalse(Description.isValidDescription("~milk")); // contains illegal symbol
    }

    @Test
    public void isValidDescription_validDescription_true() {
        assertTrue(Description.isValidDescription("")); // empty string
        assertTrue(Description.isValidDescription(" ")); // spaces only
        assertTrue(Description.isValidDescription("buy milk")); // alphabets only
        assertTrue(Description.isValidDescription("12345")); // numbers only
        assertTrue(Description.isValidDescription("2nd time!")); // alphanumeric characters and symbols
        assertTrue(Description.isValidDescription("Buy Milk")); // with capital letters
    }

    @Test
    public void equals_sameObject_true() {
        Description description = new Description("Buy some milk");
        assertEquals(description, description);
    }

    @Test
    public void equals_sameDescription_true() {
        assertEquals(new Description("Buy some milk"), new Description("Buy some milk"));
    }

    @Test
    public void equals_differentDescription_false() {
        assertNotEquals(new Description("Buy some milk"), new Description("Grade assignment"));
    }
}

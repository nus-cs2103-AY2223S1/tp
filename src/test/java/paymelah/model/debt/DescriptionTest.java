package paymelah.model.debt;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static paymelah.testutil.Assert.assertThrows;

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
        // null description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid description
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only
        assertFalse(Money.isValidMoney("       ")); // many white spaces

        // valid description
        assertTrue(Description.isValidDescription("mcdonalds")); // alphabets only
        assertTrue(Description.isValidDescription("^")); // only non-alphanumeric characters
        assertTrue(Description.isValidDescription("mcdonald's")); // contains non-alphanumeric characters
        assertTrue(Description.isValidDescription("12345")); // numbers only
        assertTrue(Description.isValidDescription("mcd0na1d5")); // alphanumeric characters
        assertTrue(Description.isValidDescription("McDonalds")); // with capital letters
        assertTrue(Description.isValidDescription(
                "Samurai Chicken Burger Meal Upsized with Coke")); // long descriptions
    }
}

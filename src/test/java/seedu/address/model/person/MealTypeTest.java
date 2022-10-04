package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MealTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MealType(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new MealType(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> MealType.isValidMealType(null));

        // invalid phone numbers
        assertFalse(MealType.isValidMealType("")); // empty string
        assertFalse(MealType.isValidMealType(" ")); // spaces only
        assertFalse(MealType.isValidMealType("91")); // less than 3 numbers
        assertFalse(MealType.isValidMealType("phone")); // non-numeric
        assertFalse(MealType.isValidMealType("9011p041")); // alphabets within digits
        assertFalse(MealType.isValidMealType("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(MealType.isValidMealType("123")); // exactly 3 numbers
        assertTrue(MealType.isValidMealType("12334"));
        assertTrue(MealType.isValidMealType("1234567891011")); // long phone numbers
    }
}

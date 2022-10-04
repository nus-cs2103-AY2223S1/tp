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
        assertThrows(NullPointerException.class, () -> MealType.isValidPhone(null));

        // invalid phone numbers
        assertFalse(MealType.isValidPhone("")); // empty string
        assertFalse(MealType.isValidPhone(" ")); // spaces only
        assertFalse(MealType.isValidPhone("91")); // less than 3 numbers
        assertFalse(MealType.isValidPhone("phone")); // non-numeric
        assertFalse(MealType.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(MealType.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(MealType.isValidPhone("911")); // exactly 3 numbers
        assertTrue(MealType.isValidPhone("93121534"));
        assertTrue(MealType.isValidPhone("124293842033123")); // long phone numbers
    }
}

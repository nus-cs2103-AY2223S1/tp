package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Phone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(Phone.isValidPhone("")); // empty string
        assertFalse(Phone.isValidPhone(" ")); // spaces only
        assertFalse(Phone.isValidPhone("91")); // less than 3 numbers
        assertFalse(Phone.isValidPhone("phone")); // non-numeric
        assertFalse(Phone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Phone.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(Phone.isValidPhone("911")); // exactly 3 numbers
        assertTrue(Phone.isValidPhone("93121534"));
        assertTrue(Phone.isValidPhone("124293842033123")); // long phone numbers
    }

    @Test
    public void equals() {
        final Phone standardPhone = new Phone("94445555");

        // same values -> returns true
        Phone sameName = new Phone("94445555");
        assertTrue(standardPhone.equals(sameName));

        // same object -> returns true
        assertTrue(standardPhone.equals(standardPhone));

        // null -> returns false
        assertFalse(standardPhone.equals(null));

        // different types -> returns false
        assertFalse(standardPhone.equals(new Name("Kevin Malone")));

        // different numbers -> returns false
        assertFalse(standardPhone.equals(new Phone("98887777")));
    }

    @Test
    public void hashcode() {
        final Phone standardPhone = new Phone("94445555");

        // same values -> returns same hashcode
        Phone sameName = new Phone("94445555");
        assertEquals(standardPhone.hashCode(), sameName.hashCode());

        // different numbers -> returns different hashcode
        assertNotEquals(standardPhone.hashCode(), (new Phone("98887777")).hashCode());
    }

}

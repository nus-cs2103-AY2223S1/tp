package seedu.application.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Contact(null));
    }

    @Test
    public void constructor_invalidContact_throwsIllegalArgumentException() {
        String invalidContact = "";
        assertThrows(IllegalArgumentException.class, () -> new Contact(invalidContact));
    }

    @Test
    public void isValidContact() {
        // null contact number
        assertThrows(NullPointerException.class, () -> Contact.isValidContact(null));

        // invalid contact numbers
        assertFalse(Contact.isValidContact("")); // empty string
        assertFalse(Contact.isValidContact(" ")); // spaces only
        assertFalse(Contact.isValidContact("91")); // less than 5 digits
        assertFalse(Contact.isValidContact("12429384203312335945")); // more than 15 digits
        assertFalse(Contact.isValidContact("Contact")); // non-numeric
        assertFalse(Contact.isValidContact("9011p041")); // alphabets within digits
        assertFalse(Contact.isValidContact("9312 1534")); // spaces within digits

        // valid Contact numbers
        assertTrue(Contact.isValidContact("91111")); // exactly 5 digits
        assertTrue(Contact.isValidContact("93121534"));
        assertTrue(Contact.isValidContact("124293842033123")); // exactly 15 digits
    }
}

package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompanyNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompanyName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new CompanyName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> CompanyName.isValidName(null));

        // invalid name
        assertFalse(CompanyName.isValidName("")); // empty string
        assertFalse(CompanyName.isValidName(" ")); // spaces only
        assertFalse(CompanyName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(CompanyName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(CompanyName.isValidName("peter jack")); // alphabets only
        assertTrue(CompanyName.isValidName("12345")); // numbers only
        assertTrue(CompanyName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(CompanyName.isValidName("Capital Tan")); // with capital letters
        assertTrue(CompanyName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void toString_email_returnsValueInName() {
        String value = "peter jack";
        CompanyName name = new CompanyName(value);
        assertEquals(name.toString(), value);
    }

}

package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class WardNumberTest {
    
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new WardNumber(null));
    }

    @Test
    public void constructor_emptyString_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new WardNumber(""));
        assertThrows(IllegalArgumentException.class, () -> new WardNumber(" "));
    }

    @Test
    public void constructor_garbledWardNumber_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new WardNumber("asdfghjkl")); // garbled text
        assertThrows(IllegalArgumentException.class, () -> new WardNumber("/inp /fn /outp")); // other prefixes
        assertThrows(IllegalArgumentException.class, () -> new WardNumber("@@")); // symbols
    }

    @Test
    public void constructor_invalidWardNumber_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new WardNumber("D")); // just 1 letter
        assertThrows(IllegalArgumentException.class, () -> new WardNumber("D1234567")); // more than 3 digits
        assertThrows(IllegalArgumentException.class, () -> new WardNumber("D1")); // less than 3 digits
        assertThrows(IllegalArgumentException.class, () -> new WardNumber("DD123")); // more than 1 letter
        assertThrows(IllegalArgumentException.class, () -> new WardNumber("D1234")); // more than 3 digits
        assertThrows(IllegalArgumentException.class, () -> new WardNumber("d123")); // uncapitalised letter
        assertThrows(IllegalArgumentException.class, () -> new WardNumber("123")); // no letter
    }

    @Test
    public void isValidWardNumber() {
        // invalid ward numbers
        assertFalse(WardNumber.isValidWardNumber("D"));
        assertFalse(WardNumber.isValidWardNumber("D1234567"));
        assertFalse(WardNumber.isValidWardNumber("D1"));
        assertFalse(WardNumber.isValidWardNumber("DD123"));
        assertFalse(WardNumber.isValidWardNumber("D1234"));
        assertFalse(WardNumber.isValidWardNumber("d123"));
        assertFalse(WardNumber.isValidWardNumber("123"));

        // valid ward numbers
        assertTrue(WardNumber.isValidWardNumber("D123"));
        assertTrue(WardNumber.isValidWardNumber("F000"));
        assertTrue(WardNumber.isValidWardNumber("A100"));
        assertTrue(WardNumber.isValidWardNumber("A000"));
        assertTrue(WardNumber.isValidWardNumber("Z999"));
        assertTrue(WardNumber.isValidWardNumber("B375"));
        assertTrue(WardNumber.isValidWardNumber("K059"));
    }

    @Test
    public void toStringTest() {
        String expectedMessage1 = "Ward: D123";
        String expectedMessage2 = "Ward: F690";
        WardNumber wardNumber1 = new WardNumber("D123");
        WardNumber wardNumber2 = new WardNumber("F690");
        assertEquals(expectedMessage1, wardNumber1.toString());
        assertEquals(expectedMessage2, wardNumber2.toString());

        assertNotEquals(expectedMessage1, wardNumber2.toString());
        assertNotEquals(expectedMessage2, wardNumber1.toString());
    }

    @Test
    public void equals() {
        WardNumber wardNumber = new WardNumber("D123");

        // same object -> returns true
        assertTrue(wardNumber.equals(wardNumber));

        // same values -> returns true
        WardNumber wardNumberCopy = new WardNumber("D123");
        assertTrue(wardNumber.equals(wardNumberCopy));

        // different types -> returns false
        assertFalse(wardNumber.equals(1));
        assertFalse(wardNumber.equals("D123"));

        // null -> returns false
        assertFalse(wardNumber.equals(null));

        // different ward number -> returns false
        WardNumber differentWardNumber = new WardNumber("D456");
        assertFalse(wardNumber.equals(differentWardNumber));
    }

}

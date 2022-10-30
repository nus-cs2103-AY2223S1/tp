package seedu.rc4hdb.model.venues;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.model.resident.fields.Name;

/**
 * Unit tests for {@link VenueName}.
 */
public class VenueNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new VenueName(null));
    }

    @Test
    public void constructor_invalidVenueName_throwsIllegalArgumentException() {
        String invalidVenueName = "";
        assertThrows(IllegalArgumentException.class, () -> new VenueName(invalidVenueName));
    }

    @Test
    public void constructor_validVenueName_constructVenueName() {
        assertTrue(new VenueName("validName") instanceof VenueName);
    }

    @Test
    public void isValidVenueName() {
        // null name
        assertThrows(NullPointerException.class, () -> VenueName.isValidVenueName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void isSameVenueName() {
        VenueName original = new VenueName("abcd");
        VenueName otherSame = new VenueName("abcd");
        VenueName diffCase = new VenueName("aBcD");
        VenueName diffChar = new VenueName("bcde");

        // EP: all characters same but possibly different case
        assertTrue(original.isSameVenueName(original));
        assertTrue(original.isSameVenueName(otherSame));
        assertTrue(original.isSameVenueName(diffCase));

        // EP: different characters
        assertFalse(original.isSameVenueName(diffChar));
    }

}

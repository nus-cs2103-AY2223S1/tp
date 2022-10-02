package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ReligionTest {
    @Test
    public void isValidReligion() {
        // null religion
        assertThrows(NullPointerException.class, () -> Religion.isValidReligion(null));

        // invalid religion
        assertFalse(Religion.isValidReligion(""));
        assertFalse(Religion.isValidReligion(" "));
        assertFalse(Religion.isValidReligion("^"));
        assertFalse(Religion.isValidReligion("Buddhist^"));
        
        // valid name
        assertTrue(Religion.isValidReligion("Buddhist")); // alphabets only
        assertTrue(Religion.isValidReligion("12345")); // numbers only
        assertTrue(Religion.isValidReligion("1st Order Christians")); // alphanumeric characters
    }
}

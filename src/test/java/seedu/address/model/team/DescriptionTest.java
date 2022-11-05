package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidName));
    }

    @Test
    public void isValidTeamDescription() {
        // null name
        assertThrows(NullPointerException.class, () -> Description.isValidTeamDescription(null));

        // invalid name
        assertFalse(Description.isValidTeamDescription("")); // empty string
        assertFalse(Description.isValidTeamDescription(" ")); // spaces only
        assertFalse(Description.isValidTeamDescription("^")); // only non-alphanumeric characters
        assertFalse(Description.isValidTeamDescription("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Description.isValidTeamDescription("peter jack")); // alphabets only
        assertTrue(Description.isValidTeamDescription("12345")); // numbers only
        assertTrue(Description.isValidTeamDescription("peter the 2nd")); // alphanumeric characters
        assertTrue(Description.isValidTeamDescription("Capital Tan")); // with capital letters
        assertTrue(Description.isValidTeamDescription("David Roger Jackson Ray Jr 2nd")); // long names
    }

}

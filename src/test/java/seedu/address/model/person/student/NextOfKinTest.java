package seedu.address.model.person.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NextOfKinTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NextOfKin(null));
    }

    @Test
    public void constructor_invalidNextOfKin_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new NextOfKin(invalidName));
    }

    @Test
    public void isValidNextOfKin() {
        // null name
        assertThrows(NullPointerException.class, () -> NextOfKin.isValidNextOfKin(null));

        // invalid name
        assertFalse(NextOfKin.isValidNextOfKin("")); // empty string
        assertFalse(NextOfKin.isValidNextOfKin(" ")); // spaces only
        assertFalse(NextOfKin.isValidNextOfKin("^")); // only non-alphanumeric characters
        assertFalse(NextOfKin.isValidNextOfKin("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(NextOfKin.isValidNextOfKin("peter jack")); // alphabets only
        assertTrue(NextOfKin.isValidNextOfKin("12345")); // numbers only
        assertTrue(NextOfKin.isValidNextOfKin("peter the 2nd")); // alphanumeric characters
        assertTrue(NextOfKin.isValidNextOfKin("Capital Tan")); // with capital letters
        assertTrue(NextOfKin.isValidNextOfKin("David Roger Jackson Ray Jr 2nd")); // long names
    }
}

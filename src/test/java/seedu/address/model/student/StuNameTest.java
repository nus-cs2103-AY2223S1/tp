package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StuNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StuName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new StuName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> StuName.isValidStuName(null));

        // invalid name
        assertFalse(StuName.isValidStuName("")); // empty string
        assertFalse(StuName.isValidStuName(" ")); // spaces only
        assertFalse(StuName.isValidStuName("^")); // only non-alphanumeric characters
        assertFalse(StuName.isValidStuName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(StuName.isValidStuName("peter jack")); // alphabets only
        assertTrue(StuName.isValidStuName("12345")); // numbers only
        assertTrue(StuName.isValidStuName("peter the 2nd")); // alphanumeric characters
        assertTrue(StuName.isValidStuName("Capital Tan")); // with capital letters
        assertTrue(StuName.isValidStuName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}


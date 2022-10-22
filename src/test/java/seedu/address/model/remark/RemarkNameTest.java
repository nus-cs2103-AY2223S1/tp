package seedu.address.model.remark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RemarkNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemarkName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new RemarkName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> RemarkName.isValidName(null));

        // invalid name
        assertFalse(RemarkName.isValidName("")); // empty string
        assertFalse(RemarkName.isValidName(" ")); // spaces only
        assertFalse(RemarkName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(RemarkName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(RemarkName.isValidName("peter jack")); // alphabets only
        assertTrue(RemarkName.isValidName("12345")); // numbers only
        assertTrue(RemarkName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(RemarkName.isValidName("Capital Tan")); // with capital letters
        assertTrue(RemarkName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void toString_email_returnsValueInName() {
        String value = "peter jack";
        RemarkName name = new RemarkName(value);
        assertEquals(name.toString(), value);
    }

}

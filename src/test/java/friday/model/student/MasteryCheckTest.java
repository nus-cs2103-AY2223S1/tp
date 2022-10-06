package friday.model.student;

import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class MasteryCheckTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MasteryCheck(null));
    }

    @Test
    public void constructor_invalidMasteryCheck_throwsIllegalArgumentException() {
        LocalDate invalidDate = LocalDate.parse("2022/12/03");
        assertThrows(IllegalArgumentException.class, () -> new MasteryCheck(invalidDate));
    }

    @Test
    public void isValidMasteryCheck() {
        // null address
        assertThrows(NullPointerException.class, () -> MasteryCheck.isValidMasteryCheck(null));

        // invalid addresses
        assertFalse(MasteryCheck.isValidMasteryCheck("")); // empty string
        assertFalse(MasteryCheck.isValidMasteryCheck(" ")); // spaces only

        // valid addresses
        assertTrue(MasteryCheck.isValidMasteryCheck("Blk 456, Den Road, #01-355"));
        assertTrue(MasteryCheck.isValidMasteryCheck("-")); // one character
        assertTrue(MasteryCheck.isValidMasteryCheck("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA"));
    }
}

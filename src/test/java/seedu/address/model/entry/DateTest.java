package seedu.address.model.entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null
        assertThrows(NullPointerException.class, () -> new Date(null));

        //invalid_date
        String invalidDate = "04-22-2022";
        assertFalse(Date.isValidDate(" "));
        assertFalse(Date.isValidDate(""));
        assertFalse(Date.isValidDate(invalidDate));


        //valid_date
        String validDate = "22-04-2022";
        assertTrue(Date.isValidDate(validDate));
    }
}

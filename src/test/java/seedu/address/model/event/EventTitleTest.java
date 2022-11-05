package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventTitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventTitle(null));
    }

    @Test
    public void constructor_invalidEventTitle_throwsIllegalArgumentException() {
        String invalidEventTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new EventTitle(invalidEventTitle));
    }

    @Test
    public void isValidEventTitle() {
        // null name
        assertThrows(NullPointerException.class, () -> EventTitle.isValidEventTitle(null));

        // invalid eventTitle
        assertFalse(EventTitle.isValidEventTitle("")); // empty string
        assertFalse(EventTitle.isValidEventTitle(" ")); // spaces only
        assertFalse(EventTitle.isValidEventTitle("-")); // only non-alphanumeric characters
        assertFalse(EventTitle.isValidEventTitle("$()&")); // only non-alphanumeric characters
        assertFalse(EventTitle.isValidEventTitle("Car Sale $$$")); // contains non-alphanumeric characters

        // valid eventTitle
        assertTrue(EventTitle.isValidEventTitle("toiletries sale")); // alphabets only
        assertTrue(EventTitle.isValidEventTitle("10 10 2022")); // numbers only
        assertTrue(EventTitle.isValidEventTitle("toiletries sale in 2022")); // alphanumeric characters
        assertTrue(EventTitle.isValidEventTitle("Toiletries Sale")); // with capital letters
        assertTrue(EventTitle.isValidEventTitle("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
package seedu.address.model.person;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateSlotTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateSlot(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidDateSlot = "";
        assertThrows(IllegalArgumentException.class, () -> new DateSlot(invalidDateSlot));
    }

    @Test
    public void isValidTagName() {
        // null date slot
        assertThrows(NullPointerException.class, () -> DateSlot.isValidDateSlot(null));
    }

}

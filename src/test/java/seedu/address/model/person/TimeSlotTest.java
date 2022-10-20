package seedu.address.model.person;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeSlotTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Session(null));
    }
}

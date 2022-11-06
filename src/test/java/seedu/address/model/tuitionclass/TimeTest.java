package seedu.address.model.tuitionclass;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null, "12:00"));

        assertThrows(NullPointerException.class, () -> new Time("12:00", null));

        assertThrows(NullPointerException.class, () -> new Time(null, null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {

        //bad times
        assertThrows(IllegalArgumentException.class, () -> new Time("12:00", "99:00"));
        assertThrows(IllegalArgumentException.class, () -> new Time("12:99", "13:00"));

        //end time before start time
        assertThrows(IllegalArgumentException.class, () -> new Time("13:00", "11:00"));

    }
}

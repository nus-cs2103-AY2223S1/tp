package seedu.address.model.meeting;

import org.junit.jupiter.api.Test;

import static seedu.address.testutil.Assert.assertThrows;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

}

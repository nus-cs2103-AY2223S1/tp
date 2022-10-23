package seedu.trackascholar.model.major;

import static seedu.trackascholar.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MajorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Major(null));
    }

    @Test
    public void constructor_invalidMajorName_throwsIllegalArgumentException() {
        String invalidMajorName = "";
        assertThrows(IllegalArgumentException.class, () -> new Major(invalidMajorName));
    }

    @Test
    public void isValidMajorName() {
        // null major name
        assertThrows(NullPointerException.class, () -> Major.isValidMajorName(null));
    }

}

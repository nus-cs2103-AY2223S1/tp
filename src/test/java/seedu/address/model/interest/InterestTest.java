package seedu.address.model.interest;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InterestTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Interest(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Interest(invalidTagName));
    }

    @Test
    public void isValidInterestName() {
        // null interest name
        assertThrows(NullPointerException.class, () -> Interest.isValidInterest(null));
    }

}

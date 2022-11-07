package seedu.address.model.person;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FilePathTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }
}

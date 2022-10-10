package seedu.address.ui;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentCardTest {

    @Test
    public void constructor_nullParameters_throwsAssertionError() {
        assertThrows(ExceptionInInitializerError.class, () -> new StudentCard(null, 0));
    }
}

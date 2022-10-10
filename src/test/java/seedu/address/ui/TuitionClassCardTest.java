package seedu.address.ui;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TuitionClassCardTest {

    @Test
    public void constructor_nullParameters_throwsAssertionError() {
        assertThrows(ExceptionInInitializerError.class, () -> new TuitionClassCard(null, 0));
    }
}

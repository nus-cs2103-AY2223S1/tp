package seedu.address.ui;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MainWindowTest {
    @Test
    public void constructor_nullParameters_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new MainWindow(null, null));
    }
}

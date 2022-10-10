package seedu.address.ui;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TutorListPanelTest {

    @Test
    public void constructor_nullParameters_throwsAssertionError() {
        assertThrows(ExceptionInInitializerError.class, () -> new TutorListPanel(null));
    }
}

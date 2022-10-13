package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ApplicationProcessTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ApplicationProcess(null));
    }

    @Test
    public void constructor_invalidApplicationProcess_throwsIllegalArgumentException() {
        String invalidApplicationProcess = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidApplicationProcess));
    }

    @Test
    public void isValidApplicationProcess() {
        // null application process
        assertThrows(NullPointerException.class, () -> ApplicationProcess.isValidApplicationProcess(null));

        // invalid application process
        assertFalse(ApplicationProcess.isValidApplicationProcess("aapply"));
        assertFalse(ApplicationProcess.isValidApplicationProcess("tech interview"));

        // valid application process
        assertTrue(ApplicationProcess.isValidApplicationProcess("APPLY"));
        assertTrue(ApplicationProcess.isValidApplicationProcess("Apply"));
        assertTrue(ApplicationProcess.isValidApplicationProcess("apply"));
        assertTrue(ApplicationProcess.isValidApplicationProcess("AppLy"));
        assertTrue(ApplicationProcess.isValidApplicationProcess("ASSESSMENT"));
        assertTrue(ApplicationProcess.isValidApplicationProcess("INTERVIEW"));
        assertTrue(ApplicationProcess.isValidApplicationProcess("OFFER"));
        assertTrue(ApplicationProcess.isValidApplicationProcess("ACCEPTED"));
        assertTrue(ApplicationProcess.isValidApplicationProcess("REJECTED"));
    }
}

package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_VISIT_STATUS;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class VisitStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new VisitStatus(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new VisitStatus(INVALID_VISIT_STATUS));
    }

    @Test
    public void isValidVisitStatus() {
        // null gender
        assertThrows(NullPointerException.class, () -> VisitStatus.isValidVisitStatus(null));

        // invalid gender formats
        assertFalse(VisitStatus.isValidVisitStatus("")); // empty string
        assertFalse(VisitStatus.isValidVisitStatus(" ")); // spaces only
        assertFalse(VisitStatus.isValidVisitStatus("invalid")); // lowercase word
        assertFalse(VisitStatus.isValidVisitStatus("INVALID")); // uppercase word
        assertFalse(VisitStatus.isValidVisitStatus("1")); // digits only

        // valid gender formats
        assertTrue(VisitStatus.isValidVisitStatus("true")); // true
        assertTrue(VisitStatus.isValidVisitStatus("false")); // false
        assertTrue(VisitStatus.isValidVisitStatus("TRUE")); // TRUE
        assertTrue(VisitStatus.isValidVisitStatus("FALSE")); // FALSE
    }
}


package coydir.model.person;

import static coydir.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EmployeeIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EmployeeId(null));
    }

    @Test
    public void constructor_invalidEmployeeId_throwsIllegalArgumentException() {
        String invalidEmployeeId = "abc";
        assertThrows(IllegalArgumentException.class, () -> new EmployeeId(invalidEmployeeId));
    }

    @Test
    public void isValidEmployeeId() {
        // null id
        assertThrows(NullPointerException.class, () -> EmployeeId.isValidEmployeeId(null));

        // invalid ids
        assertFalse(EmployeeId.isValidEmployeeId("")); // empty string
        assertFalse(EmployeeId.isValidEmployeeId(" ")); // spaces only
        assertFalse(EmployeeId.isValidEmployeeId("phone")); // non-numeric
        assertFalse(EmployeeId.isValidEmployeeId("9011p041")); // alphabets within digits
        assertFalse(EmployeeId.isValidEmployeeId("9312 1534")); // spaces within digits

        // valid ids
        // assertTrue(EmployeeId.isValidEmployeeId("9"));
        // assertTrue(EmployeeId.isValidEmployeeId("911"));
        // assertTrue(EmployeeId.isValidEmployeeId("93121534"));
        // assertTrue(EmployeeId.isValidEmployeeId("124293842033123"));
    }
}

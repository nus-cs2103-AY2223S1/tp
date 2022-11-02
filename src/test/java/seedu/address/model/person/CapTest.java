package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CapTest {
    // null parameters of Cap() will be tested upon parsing Cap values, as double cannot be null.
    @Test
    void isValidCap() {
        // invalid cap
        assertFalse(Cap.isValidCap(-5.0, 5.0)); // negative cap value
        assertFalse(Cap.isValidCap(4.0, -4.0)); // negative maximum cap value
        assertFalse(Cap.isValidCap(10.0, 5.0)); // cap value greater than its maximum
        assertFalse(Cap.isValidCap(0.0, 4.0));
        assertFalse(Cap.isValidCap(0, 5));

        // valid cap
        assertTrue(Cap.isValidCap(4.99, 5.0)); // max value can be of any value greater than or equal to cap value
        assertTrue(Cap.isValidCap(5.0, 5.0));
        assertTrue(Cap.isValidCap(1.0, 4.0));
        assertTrue(Cap.isValidCap(2, 3)); // both cap value and maximum cap value can be an integer
        assertTrue(Cap.isValidCap(5, 5));
    }
}

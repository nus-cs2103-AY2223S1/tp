package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CapTest {
    // null parameters of Cap() will be tested upon parsing Cap values, as double
    // cannot be null.
    @Test
    void isValidCapValues() {
        // invalid cap values
        assertFalse(Cap.isValidCapValues(-5.0, 5.0)); // negative cap value
        assertFalse(Cap.isValidCapValues(4.0, -4.0)); // negative maximum cap value
        assertFalse(Cap.isValidCapValues(10.0, 5.0)); // cap value greater than its maximum
        assertFalse(Cap.isValidCapValues(0.0, 4.0));
        assertFalse(Cap.isValidCapValues(0, 5));
        assertFalse(Cap.isValidCapValues(101, 102)); // maximum cap value > 100

        // valid cap values
        assertTrue(Cap.isValidCapValues(4.99, 5.0)); // max value can be of any value greater than or equal to cap value
        assertTrue(Cap.isValidCapValues(5.0, 5.0));
        assertTrue(Cap.isValidCapValues(1.0, 4.0));
        assertTrue(Cap.isValidCapValues(2, 3)); // both cap value and maximum cap value can be an integer
        assertTrue(Cap.isValidCapValues(5, 5));
        assertTrue(Cap.isValidCapValues(90, 100));
    }

    @Test
    void isValidCapInput() {
        String[] validCapInputs = {"2.0", "3.0"}; // 2 numeric values
        String[] secondValidCapInputs = {"3", "5"}; // 2 integers
        String[] invalidCapInputs = {"1.0", "2.0", "3.0"}; // more than 2 values provided
        String[] secondInvalidCapInputs = {"1.0", "2.0", "3.0"}; // more than 2 values provided
        String[] thirdInvalidCapInputs = {"1.#", "2.0"}; // contains symbol
        String[] fourthInvalidCapInputs = {"2.0"}; // only one value provided

        // invalid cap inputs
        assertFalse(Cap.isValidCapInput(invalidCapInputs));
        assertFalse(Cap.isValidCapInput(secondInvalidCapInputs));
        assertFalse(Cap.isValidCapInput(thirdInvalidCapInputs));
        assertFalse(Cap.isValidCapInput(fourthInvalidCapInputs));

        // valid cap inputs
        assertTrue(Cap.isValidCapInput(validCapInputs));
        assertTrue(Cap.isValidCapInput(secondValidCapInputs));
    }

    @Test
    void isValidCapFormat() {
        // invalid cap format
        assertFalse(Cap.isValidCapFormat("2.0f")); // contains alphabet
        assertFalse(Cap.isValidCapFormat("3.0@")); // contains symbol

        // valid cap format
        assertTrue(Cap.isValidCapFormat("2.0")); // double
        assertTrue(Cap.isValidCapFormat("2")); // integer
        assertTrue(Cap.isValidCapFormat("2 . 0")); // with spaces in between
        assertTrue(Cap.isValidCapFormat(" 2.0")); // with leading space
        assertTrue(Cap.isValidCapFormat("2.0 ")); // with trailing space
    }
}

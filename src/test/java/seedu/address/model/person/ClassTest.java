package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ClassTest {

    @Test
    public void isValidClassString() {
        assertTrue(Class.isValidClassString("2022-05-05 1200-1300"));
        assertTrue(Class.isValidClassString("2022-12-05 1330-1400"));
    }

    @Test
    public void isInvalidClassString() {
        assertFalse(Class.isValidClassString("2022-05-05 1200 1300"));
        assertFalse(Class.isValidClassString(""));
    }

}

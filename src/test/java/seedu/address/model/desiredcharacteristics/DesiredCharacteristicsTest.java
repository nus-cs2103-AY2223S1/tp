package seedu.address.model.desiredcharacteristics;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;

public class DesiredCharacteristicsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidDesiredCharacteristics_throwsIllegalArgumentException() {
        String invalidDesiredCharacteristics = "";
        assertThrows(IllegalArgumentException.class, () -> new DesiredCharacteristics(invalidDesiredCharacteristics));
    }

    @Test
    public void isValidDesiredCharacteristics() {
        // null name
        assertThrows(NullPointerException.class, () -> DesiredCharacteristics.isValidDesiredCharacteristics(null));

        // invalid desired characteristics
        assertFalse(DesiredCharacteristics.isValidDesiredCharacteristics("")); // empty string
        assertFalse(DesiredCharacteristics.isValidDesiredCharacteristics(" ")); // spaces only

        // valid desired characteristics
        assertTrue(DesiredCharacteristics.isValidDesiredCharacteristics("Bright"));
        assertTrue(DesiredCharacteristics.isValidDesiredCharacteristics("Multiple words"));
        assertTrue(DesiredCharacteristics.isValidDesiredCharacteristics("Words; Separated; "));
    }
}

package seedu.address.model.characteristics;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.buyer.Name;

public class CharacteristicsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidDesiredCharacteristics_throwsIllegalArgumentException() {
        String invalidDesiredCharacteristics = "";
        assertThrows(IllegalArgumentException.class, () -> new Characteristics(invalidDesiredCharacteristics));
    }

    @Test
    public void isValidDesiredCharacteristics() {
        // null name
        assertThrows(NullPointerException.class, () -> Characteristics.isValidCharacteristics(null));

        // invalid desired characteristics
        assertFalse(Characteristics.isValidCharacteristics("")); // empty string
        assertFalse(Characteristics.isValidCharacteristics(" ")); // spaces only

        // valid desired characteristics
        assertTrue(Characteristics.isValidCharacteristics("Bright"));
        assertTrue(Characteristics.isValidCharacteristics("Multiple words"));
        assertTrue(Characteristics.isValidCharacteristics("Words; Separated; "));
    }
}

package seedu.address.model.pricerange;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;

public class PriceRangeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidPriceRange_throwsIllegalArgumentException() {
        String invalidPriceRange = "200";
        assertThrows(IllegalArgumentException.class, () -> new PriceRange(invalidPriceRange));
        String invalidEmptyPriceRange = "";
        assertThrows(IllegalArgumentException.class, () -> new PriceRange(invalidEmptyPriceRange));
    }

    @Test
    public void isValidName() {
        // null price range
        assertThrows(NullPointerException.class, () -> PriceRange.isValidPriceRange(null));

        // invalid price range
        assertFalse(PriceRange.isValidPriceRange("")); // empty string
        assertFalse(PriceRange.isValidPriceRange(" ")); // spaces only
        assertFalse(PriceRange.isValidPriceRange("200")); // only one value
        assertFalse(PriceRange.isValidPriceRange("500-200")); // low before high
        assertFalse(PriceRange.isValidPriceRange("200;300")); // wrong separator

        // valid price range
        assertTrue(PriceRange.isValidPriceRange("200 - 500"));
        assertTrue(PriceRange.isValidPriceRange("200-500")); // no spacing
    }
}

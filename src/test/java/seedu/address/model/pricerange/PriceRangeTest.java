package seedu.address.model.pricerange;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.buyer.Name;
import seedu.address.model.property.Price;

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
    public void isValidPriceRange() {
        // null price range
        assertThrows(NullPointerException.class, () -> PriceRange.isValidPriceRange(null));

        // invalid price range
        assertFalse(PriceRange.isValidPriceRange("200")); // only one value
        assertFalse(PriceRange.isValidPriceRange("500-200")); // low before high
        assertFalse(PriceRange.isValidPriceRange("200;300")); // wrong separator

        // valid price range
        assertTrue(PriceRange.isValidPriceRange("")); // empty string
        assertTrue(PriceRange.isValidPriceRange(" ")); // spaces only
        assertTrue(PriceRange.isValidPriceRange("200 - 500"));
        assertTrue(PriceRange.isValidPriceRange("200 - 200")); // upper = lower
        assertTrue(PriceRange.isValidPriceRange("200-500")); // no spacing
        assertTrue(PriceRange.isValidPriceRange("200-  500")); // multiple spaces
    }

    @Test
    public void isWithinPriceRange() {

        PriceRange targetPriceRange = new PriceRange("200 - 500");

        //null price
        assertThrows(NullPointerException.class, () -> targetPriceRange.isWithinPriceRange(null));

        //price out of bound
        assertFalse(targetPriceRange.isWithinPriceRange(new Price("0")));

        //price within range
        assertTrue(targetPriceRange.isWithinPriceRange(new Price("300")));

        //price on range upperbound
        assertTrue(targetPriceRange.isWithinPriceRange(new Price("500")));

        //price on range lowerbound
        assertTrue(targetPriceRange.isWithinPriceRange(new Price("200")));

    }
}

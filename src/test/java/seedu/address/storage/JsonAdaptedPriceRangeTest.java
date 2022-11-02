package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Price;
import seedu.address.model.order.PriceRange;

public class JsonAdaptedPriceRangeTest {
    private static final Double INVALID_UPPER_BOUND = -10.0758;
    private static final Double INVALID_LOWER_BOUND = -7.890486;

    private static final Double VALID_UPPER_BOUND = 150.75864;
    private static final Double VALID_LOWER_BOUND = 40.8396803;

    @Test
    public void toModelType_validLowerBound_returnsPriceRange() throws Exception {
        Double lowerBound = 40.988;
        PriceRange expected = new PriceRange(new Price(lowerBound), new Price(VALID_UPPER_BOUND));

        JsonAdaptedPriceRange priceRange = new JsonAdaptedPriceRange(VALID_UPPER_BOUND, lowerBound);
        assertEquals(priceRange.toModelType(), expected);
    }

    @Test
    public void toModelType_invalidLowerBound_throwsIllegalValueException() {
        JsonAdaptedPriceRange priceRange = new JsonAdaptedPriceRange(VALID_UPPER_BOUND, INVALID_LOWER_BOUND);
        String expectedMessage = PriceRange.MESSAGE_USAGE;
        assertThrows(IllegalValueException.class, expectedMessage, priceRange::toModelType);
    }

    @Test
    public void toModelType_nullLowerBound_throwsIllegalValueException() {
        JsonAdaptedPriceRange priceRange = new JsonAdaptedPriceRange(VALID_UPPER_BOUND, null);
        String expectedMessage = PriceRange.MESSAGE_USAGE;
        assertThrows(IllegalValueException.class, expectedMessage, priceRange::toModelType);
    }

    @Test
    public void toModelType_validUpperBound_returnsPriceRange() throws Exception {
        Double upperBound = 108.89;
        PriceRange expected = new PriceRange(new Price(VALID_LOWER_BOUND), new Price(upperBound));

        JsonAdaptedPriceRange priceRange = new JsonAdaptedPriceRange(upperBound, VALID_LOWER_BOUND);
        assertEquals(priceRange.toModelType(), expected);
    }

    @Test
    public void toModelType_invalidUpperBound_throwsIllegalValueException() {
        JsonAdaptedPriceRange priceRange = new JsonAdaptedPriceRange(INVALID_UPPER_BOUND, VALID_LOWER_BOUND);
        String expectedMessage = PriceRange.MESSAGE_USAGE;
        assertThrows(IllegalValueException.class, expectedMessage, priceRange::toModelType);
    }

    @Test
    public void toModelType_nullUpperBound_throwsIllegalValueException() {
        JsonAdaptedPriceRange priceRange = new JsonAdaptedPriceRange(null, VALID_LOWER_BOUND);
        String expectedMessage = PriceRange.MESSAGE_USAGE;
        assertThrows(IllegalValueException.class, expectedMessage, priceRange::toModelType);
    }
}

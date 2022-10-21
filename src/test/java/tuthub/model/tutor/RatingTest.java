package tuthub.model.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class RatingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rating(null));
    }

    @Test
    public void constructor_invalidRating_throwsIllegalArgumentException() {
        String invalidRating1 = "";
        assertThrows(IllegalArgumentException.class, () -> new Rating(invalidRating1));

        String invalidRating2 = "-1.0";
        assertThrows(IllegalArgumentException.class, () -> new Rating(invalidRating2));

        String invalidRating3 = "6.19";
        assertThrows(IllegalArgumentException.class, () -> new Rating(invalidRating3));

        String invalidRating4 = "1.111";
        assertThrows(IllegalArgumentException.class, () -> new Rating(invalidRating4));
    }

    @Test
    void isValidRating() {
        // null rating
        assertThrows(NullPointerException.class, () -> Rating.isValidRating(null));

        // blank rating
        assertFalse(Rating.isValidRating("")); // empty string
        assertFalse(Rating.isValidRating(" ")); // spaces only

        // invalid rating
        assertFalse(Rating.isValidRating("1.111")); // more than 2 decimal points
        assertFalse(Rating.isValidRating("-0.01")); // boundary value less than min
        assertFalse(Rating.isValidRating("-1")); // negative rating
        assertFalse(Rating.isValidRating("5.01")); // boundary value greater than max
        assertFalse(Rating.isValidRating("11")); // greater than max
        assertFalse(Rating.isValidRating(" 2.0")); // leading space
        assertFalse(Rating.isValidRating("3 ")); // trailing space

        // valid rating
        assertTrue(Rating.isValidRating("0")); // min value
        assertTrue(Rating.isValidRating("0.0")); // min value with a decimal
        assertTrue(Rating.isValidRating("0.00")); // min value with 2 decimals
        assertTrue(Rating.isValidRating("0.01")); // boundary value of min
        assertTrue(Rating.isValidRating("0.1")); // closest 1 decimal number to min
        assertTrue(Rating.isValidRating("0.10")); // closest 1 decimal number to min in 2 decimal form
        assertTrue(Rating.isValidRating("1")); // whole number
        assertTrue(Rating.isValidRating("1.0")); // whole number with a decimal
        assertTrue(Rating.isValidRating("1.00")); // whole number with 2 decimals
        assertTrue(Rating.isValidRating("2.5")); // between max and min
        assertTrue(Rating.isValidRating("2.50")); // between max and min with 2 decimals
        assertTrue(Rating.isValidRating("4")); // whole number
        assertTrue(Rating.isValidRating("4.0")); // whole number with a decimal
        assertTrue(Rating.isValidRating("4.00")); // whole number with 2 decimals
        assertTrue(Rating.isValidRating("4.9")); // closest 1 decimal number to max
        assertTrue(Rating.isValidRating("4.99")); // boundary value of max
        assertTrue(Rating.isValidRating("5")); // max value
        assertTrue(Rating.isValidRating("5.0")); // max value with a decimal
        assertTrue(Rating.isValidRating("5.00")); // max value with 2 decimals
    }
}

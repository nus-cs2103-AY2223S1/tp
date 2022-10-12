package foodwhere.model.review;

import static foodwhere.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RatingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rating(null));
    }

    @Test
    public void constructor_invalidRating_throwsIllegalArgumentException() {
        Integer invalidRating = 6;
        assertThrows(IllegalArgumentException.class, () -> new Rating(invalidRating));
    }

    @Test
    public void isValidRating() {
        // null content
        assertThrows(NullPointerException.class, () -> Rating.isValidRating(null));

        // invalid contents
        assertFalse(Rating.isValidRating(6)); // invalid number
        assertFalse(Rating.isValidRating(7)); // invalid number

        // valid contents
        assertTrue(Rating.isValidRating(0));
        assertTrue(Rating.isValidRating(3)); // one character
    }
}

package seedu.clinkedin.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RatingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rating(null));
    }

    @Test
    public void constructor_validRating_success() {
        String validRating = "4";
        assertEquals(new Rating(validRating).getClass(), Rating.class);
    }

    @Test
    public void equals_sameRating_success() {
        String rating = "4";
        assertEquals(new Rating(rating), new Rating(rating));
    }

    @Test
    public void equals_differentRating_success() {
        String rating1 = "3";
        String rating2 = "5";
        assertEquals(new Rating(rating1).equals(new Rating(rating2)), false);
    }

    @Test
    public void equals_differentRatingSameValues_success() {
        String rating1 = "6";
        String rating2 = "6";
        assertEquals(new Rating(rating1).equals(new Rating(rating2)), true);
    }

    @Test
    public void value_success() {
        String rating = "1";
        assertEquals(new Rating(rating).toString(), rating);
    }

    @Test
    public void validRating() {

        assertTrue(Rating.isValidRatingStr("0"));
        assertTrue(Rating.isValidRatingStr("1"));
        assertTrue(Rating.isValidRatingStr("2"));
        assertTrue(Rating.isValidRatingStr("3"));
        assertTrue(Rating.isValidRatingStr("9"));
        assertTrue(Rating.isValidRatingStr("10"));
        assertFalse(Rating.isValidRatingStr("-129"));
        assertFalse(Rating.isValidRatingStr("9875845639"));
        assertFalse(Rating.isValidRatingStr("happy monkey"));
        assertFalse(Rating.isValidRatingStr("-----"));
        assertFalse(Rating.isValidRatingStr("0.1"));
        assertFalse(Rating.isValidRatingStr("89+0138"));
    }

    @Test
    public void hashcodeTest() {
        Rating rating = new Rating("4");
        Rating equalRating = new Rating("4");
        Rating smallerRating = new Rating("1");
        Rating largerRating = new Rating("9");

        assertEquals(rating.hashCode(), rating.hashCode());
        assertEquals(rating.hashCode(), equalRating.hashCode());
        assertFalse(rating.hashCode() == largerRating.hashCode());
        assertFalse(rating.hashCode() == smallerRating.hashCode());
    }

    @Test
    public void compareRatingTest() {
        Rating rating = new Rating("4");
        Rating equalRating = new Rating("4");
        Rating smallerRating = new Rating("1");
        Rating largerRating = new Rating("9");

        assertEquals(rating.compare(equalRating), 0);
        assertEquals(rating.compare(smallerRating), 1);
        assertEquals(rating.compare(largerRating), -1);
    }
}

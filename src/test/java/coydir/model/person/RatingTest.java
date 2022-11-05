package coydir.model.person;

import static coydir.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import coydir.logic.parser.exceptions.ParseException;

public class RatingTest {
    private Rating rating1;
    private Rating rating2;
    private Rating rating3;
    private Rating rating4;
    private Rating rating5;
    
    public RatingTest() {
        try {
            rating1 = new Rating("3", "05-01-2022");
            rating2 = new Rating("3");
            rating3 = new Rating();
            rating4 = new Rating("3", "05-01-2022");
            rating5 = new Rating("3", "05-01-2022");
        } catch (ParseException ignored) {}
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rating(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidRating = "";
        assertThrows(IllegalArgumentException.class, () -> new Rating(invalidRating));
    }

    @Test
    public void isValidRating() {
        // null rating
        assertThrows(NullPointerException.class, () -> Rating.isValidRating(null));

        // blank rating
        assertFalse(Rating.isValidRating("")); // empty string
        assertFalse(Rating.isValidRating(" ")); // spaces only
        
        // invalid rating
        assertFalse(Rating.isValidRating("0")); // rating smaller than minimum integer
        assertFalse(Rating.isValidRating("02")); // rating not an integer format
        assertFalse(Rating.isValidRating("6")); // rating larger than maximum integer
        assertFalse(Rating.isValidRating("-2")); // rating value negative
        assertFalse(Rating.isValidRating("2.5")); // rating value is a float
        assertFalse(Rating.isValidRating("string")); // rating value is a string

        // valid rating
        assertTrue(Rating.isValidRating("1")); 
        assertTrue(Rating.isValidRating("2"));
        assertTrue(Rating.isValidRating("3"));
        assertTrue(Rating.isValidRating("4"));
        assertTrue(Rating.isValidRating("5"));
    }

    @Test
    public void getRating() {
        assertEquals("3", rating1.toString());
    }
    
}

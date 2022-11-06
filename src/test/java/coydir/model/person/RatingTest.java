package coydir.model.person;

import static coydir.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import coydir.logic.parser.exceptions.ParseException;

public class RatingTest {
    private Rating rating1;
    private Rating rating2;
    private Rating rating3;
    private Rating rating4;
    private Rating rating5;
    private Rating rating6;
    private Rating rating7;

    public RatingTest() {
        try {
            rating1 = new Rating("1", "05-01-2022");
            rating2 = new Rating("2", "06-02-2006");
            rating3 = new Rating("3", "07-07-1999");
            rating4 = new Rating("4", "12-11-2020");
            rating5 = new Rating("5", "29-06-2019");
            rating6 = new Rating("3");
            rating7 = new Rating();
        } catch (ParseException ignored) {
            // ignored
        }
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
        assertEquals("1", rating1.toString());
        assertEquals("2", rating2.toString());
        assertEquals("3", rating3.toString());
        assertEquals("4", rating4.toString());
        assertEquals("5", rating5.toString());
        assertEquals("3", rating6.toString());
        assertEquals("N/A", rating7.toString());
    }

    @Test
    public void getTimestamp() {
        assertEquals("2022-01-05", rating1.getTime().toString());
        assertEquals("2006-02-06", rating2.getTime().toString());
        assertEquals("1999-07-07", rating3.getTime().toString());
        assertEquals("2020-11-12", rating4.getTime().toString());
        assertEquals("2019-06-29", rating5.getTime().toString());
        assertEquals(LocalDate.now().toString(), rating6.getTime().toString());
        assertEquals(LocalDate.now().toString(), rating7.getTime().toString());
    }
}

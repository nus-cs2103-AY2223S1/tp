package foodwhere.model.review;

import static foodwhere.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RatingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rating(null));
    }

    @Test
    public void constructor_invalidRating_throwsIllegalArgumentException() {
        Integer invalidRating = 6;
        assertThrows(IllegalArgumentException.class, () -> new Rating(String.valueOf(invalidRating)));
    }

    @Test
    public void isValidRating() {
        // null content
        assertThrows(NullPointerException.class, () -> Rating.isValidRating(null));

        // invalid contents
        assertFalse(Rating.isValidRating("asdf")); // invalid number
        assertFalse(Rating.isValidRating("7")); // invalid number
        assertFalse(Rating.isValidRating("8")); // invalid number
        assertFalse(Rating.isValidRating("")); // invalid number
        assertFalse(Rating.isValidRating("abcd")); // invalid number
        assertFalse(Rating.isValidRating("   ")); // invalid number
        assertFalse(Rating.isValidRating("3.")); // invalid number
        assertFalse(Rating.isValidRating("5.")); // invalid number
        assertFalse(Rating.isValidRating("3.0")); // invalid number
        assertFalse(Rating.isValidRating("4.0")); // invalid number
        assertFalse(Rating.isValidRating("4.2")); // invalid number
        assertFalse(Rating.isValidRating("1.0")); // invalid number
        assertFalse(Rating.isValidRating("4a")); // invalid number
        assertFalse(Rating.isValidRating("a3")); // invalid number
        assertFalse(Rating.isValidRating("-3")); // invalid number
        assertFalse(Rating.isValidRating(String.format("%s", Integer.MAX_VALUE)));
        assertFalse(Rating.isValidRating(String.format("%s", Integer.MAX_VALUE + 1)));
        assertFalse(Rating.isValidRating(String.format("%s", Integer.MIN_VALUE)));
        assertFalse(Rating.isValidRating(String.format("%s", Integer.MIN_VALUE - 1)));
        assertFalse(Rating.isValidRating("-10000000000000000000"));
        assertFalse(Rating.isValidRating("10000000000000000000"));
        assertFalse(Rating.isValidRating("1180591620717411303427"));
        assertFalse(Rating.isValidRating(String.valueOf(Double.MAX_VALUE)));
        assertFalse(Rating.isValidRating(String.valueOf(Double.MIN_VALUE)));

        // valid contents
        assertTrue(Rating.isValidRating("0"));
        assertTrue(Rating.isValidRating("3")); // one character
        assertTrue(Rating.isValidRating("03"));
        assertTrue(Rating.isValidRating("04"));
        assertTrue(Rating.isValidRating("004"));
        assertTrue(Rating.isValidRating("00004"));
    }

    @Test
    public void getTagString_generalTesting_success() {
        String testString = "test";
        Review testReviewNoTag = new ReviewBuilder().withName(testString).withAddress(testString).build();
        assertEquals("", testReviewNoTag.getTagString());

        Review testReviewWOneTag = new ReviewBuilder(testReviewNoTag).withTags("tag").build();
        assertEquals("tag", testReviewWOneTag.getTagString());

        String[] tags = new String[] {"tag", "tag2", "tag3"};
        Review testReviewWManyTag = new ReviewBuilder(testReviewWOneTag).withTags(tags).build();
        assertEquals("tag, tag2, tag3", testReviewWManyTag.getTagString());
    }
}

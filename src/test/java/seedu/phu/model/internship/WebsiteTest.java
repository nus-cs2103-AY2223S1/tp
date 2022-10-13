package seedu.phu.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.phu.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class WebsiteTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Website(null));
    }

    @Test
    public void constructor_invalidWebsite_throwsIllegalArgumentException() {
        String invalidWebsite = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidWebsite));
    }

    @Test
    public void isValidWebsite() {
        // null website
        assertThrows(NullPointerException.class, () -> Website.isValidWebsite(null));

        // invalid website
        assertFalse(Website.isValidWebsite("")); // empty string
        assertFalse(Website.isValidWebsite(" ")); // spaces only
        assertFalse(Website.isValidWebsite("https:"));
        assertFalse(Website.isValidWebsite("https://"));
        assertFalse(Website.isValidWebsite("www.google.com/jobs/results"));

        // valid websites
        assertTrue(Website.isValidWebsite("http://careers.google.com/jobs/results")); // unsafe url
        assertTrue(Website.isValidWebsite("https://careers.google.com/jobs/results/115402744447017670/"));
        assertTrue(Website.isValidWebsite("https://example.com/web/jobs"));
        assertTrue(Website.isValidWebsite("https://example.sg/careers/swe"));
    }
}

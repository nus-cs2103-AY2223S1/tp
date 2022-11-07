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
        assertFalse(Website.isValidWebsite("https:////"));
        assertFalse(Website.isValidWebsite("file://careers.google.com/jobs/results")); // wrong protocol
        assertFalse(Website.isValidWebsite("www.google.com/jobs/results")); // missing protocol
        assertFalse(Website.isValidWebsite("https://example")); // missing top level domain
        assertFalse(Website.isValidWebsite("https://example.c/careers")); // top level domain length less than 2
        assertFalse(Website.isValidWebsite("https://example.11/careers")); // top level domain numbers only
        assertFalse(Website.isValidWebsite("https://example.a1/careers")); // top level domain end with number
        assertFalse(Website.isValidWebsite("https://example..eg.cc/careers")); // consecutive period
        assertFalse(Website.isValidWebsite("https://example-.eg.cc/careers")); // consecutive special char
        assertFalse(Website.isValidWebsite("https://example-.cc/careers")); // label ends with dash
        assertFalse(Website.isValidWebsite("https://example-cc")); // dash before top level domain
        assertFalse(Website.isValidWebsite("https://-123.com")); // start with dash
        assertFalse(Website.isValidWebsite("https://test.com-")); // ends with dash
        assertFalse(Website.isValidWebsite("https://example-cc/careers")); // dash before top level domain
        assertFalse(Website.isValidWebsite("https://example@.cc/careers")); // illegal special char

        // valid websites
        assertTrue(Website.isValidWebsite("http://careers.google.com")); // unsafe url
        assertTrue(Website.isValidWebsite("https://careers.google.com")); // safe url
        assertTrue(Website.isValidWebsite("https://www.amazon.jobs/results/115402744447017670/"));
        assertTrue(Website.isValidWebsite("https://example.com/web/jobs"));
        assertTrue(Website.isValidWebsite("https://example.com/")); // ends with slash
        assertTrue(Website.isValidWebsite("https://example.com")); // no paths
        assertTrue(Website.isValidWebsite("https://123.com")); // numbers label
        assertTrue(Website.isValidWebsite("https://123.12m")); // top domain label with numbers
        assertTrue(Website.isValidWebsite("https://e.oo/results/115402744447017670/")); // minimum domain labels
        assertTrue(Website.isValidWebsite("https://exam123ple.sg/careers.swe")); // domain name with numbers
    }
}

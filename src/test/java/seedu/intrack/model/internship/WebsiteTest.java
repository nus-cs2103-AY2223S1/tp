package seedu.intrack.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class WebsiteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Website(null));
    }

    @Test
    public void constructor_invalidWebsite_throwsIllegalArgumentException() {
        String invalidWebsite = "";
        assertThrows(IllegalArgumentException.class, () -> new Website(invalidWebsite));
    }

    @Test
    public void isValidWebsite() {
        // null website
        assertThrows(NullPointerException.class, () -> Website.isValidWebsite(null));

        // invalid websites
        assertFalse(Website.isValidWebsite("")); // empty string
        assertFalse(Website.isValidWebsite(" ")); // spaces only

        // valid websites
        assertTrue(Website.isValidWebsite("https://www.apple.com")); // url with https
        assertTrue(Website.isValidWebsite("http://www.apple.com/careers")); // url with https and directory
        assertTrue(Website.isValidWebsite("https://www.apple.com/careers/#/sg")); // url with multiple directory
        assertTrue(Website.isValidWebsite("https://apple.com")); // url without www
        assertTrue(Website.isValidWebsite("http://microsoft.com")); // url with http
        assertTrue(Website.isValidWebsite("http://google.com"));
        assertTrue(Website.isValidWebsite("http://www.apple.com/xyz?abc=dkd&p=q&c=2")); // url with directory path
        assertTrue(Website.isValidWebsite("http://www.apple.gov.bd")); // url with subdomain
        assertTrue(Website.isValidWebsite("http://www.apple.com.en"));
        assertTrue(Website.isValidWebsite("http://www.apple.vu"));
        assertTrue(Website.isValidWebsite("https://www.apple.u/")); // url with subdomain ending with /
        assertTrue(Website.isValidWebsite("ftp://www.apple.com")); // url with ftp
        assertTrue(Website.isValidWebsite("http://google.com ")); // url with spaces
        assertTrue(Website.isValidWebsite("https://www.google.com/search?q=test ."));
    }
}

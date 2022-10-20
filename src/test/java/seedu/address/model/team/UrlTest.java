package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UrlTest {
    @Test
    public void isValidUrl() {
        assertTrue(Url.isValidUrl("https://google.com"));
        assertTrue(Url.isValidUrl("https://www.google.com"));
        assertTrue(Url.isValidUrl("http://google.com"));

        assertFalse(Url.isValidUrl("google.com"));
        assertFalse(Url.isValidUrl("https://google"));
    }
}

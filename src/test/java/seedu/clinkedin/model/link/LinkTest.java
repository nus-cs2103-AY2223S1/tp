package seedu.clinkedin.model.link;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;

public class LinkTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Link(null));
    }

    @Test
    public void constructor_validLink_success() throws MalformedURLException {
        URL validUrl = new URL("https://google.com");
        assertEquals(new Link(validUrl).getClass(), Link.class);
    }

    @Test
    public void generatePlatform_knownPlatform_success() throws MalformedURLException {
        URL url = new URL("https://github.com");
        Link link = new Link(url);
        String generatedPlatform = link.platform;
        String expectedPlatform = "github";
        assertTrue(generatedPlatform.equals(expectedPlatform));
    }

    @Test
    public void generatePlatform_unknownPlatform_success() throws MalformedURLException {
        URL url = new URL("https://google.com");
        Link link = new Link(url);
        String generatedPlatform = link.platform;
        String expectedPlatform = "general";
        assertTrue(generatedPlatform.equals(expectedPlatform));
    }

    @Test
    public void equals_sameLink_success() throws MalformedURLException {
        URL url = new URL("https://google.com");
        assertTrue(new Link(url).equals(new Link(url)));
    }

    @Test
    public void equals_differentLinkSameValues_success() throws MalformedURLException {
        URL url1 = new URL("https://github.com");
        URL url2 = new URL("https://github.com");
        assertTrue(new Link(url1).equals(new Link(url2)));
    }

    @Test
    public void equals_differentLinkDifferentValues_failure() throws MalformedURLException {
        URL url1 = new URL("https://google.com");
        URL url2 = new URL("https://github.com");
        assertFalse(new Link(url1).equals(new Link(url2)));
    }

    @Test
    public void value_success() throws MalformedURLException {
        URL url = new URL("https://github.com");
        assertEquals(new Link(url).link, url);
    }
}

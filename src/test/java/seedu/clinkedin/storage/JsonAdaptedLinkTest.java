package seedu.clinkedin.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.clinkedin.testutil.Assert.assertThrows;

import java.net.URL;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.commons.exceptions.IllegalValueException;
import seedu.clinkedin.model.link.Link;



public class JsonAdaptedLinkTest {
    private static final String VALID_LINK_STRING = "https://linkedin.com";
    private static final String INVALID_LINK_STRING = "google.com";

    @Test
    public void toModelType_validLink_returnsLink() throws Exception {
        URL validUrl = new URL(VALID_LINK_STRING);
        Link validLink = new Link(validUrl);
        JsonAdaptedLink link = new JsonAdaptedLink(validLink);
        assertEquals(validLink, link.toModelType());
    }

    @Test
    public void toModelType_invalidLink_throwsIllegalValueException() {
        JsonAdaptedLink link = new JsonAdaptedLink(INVALID_LINK_STRING);
        String expectedMessage = "no protocol: " + INVALID_LINK_STRING;
        assertThrows(IllegalValueException.class, expectedMessage, link::toModelType);
    }
}

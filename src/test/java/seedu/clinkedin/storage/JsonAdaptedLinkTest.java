package seedu.clinkedin.storage;

import org.junit.jupiter.api.Test;
import seedu.clinkedin.commons.exceptions.IllegalValueException;
import seedu.clinkedin.model.link.Link;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.clinkedin.testutil.Assert.assertThrows;

public class JsonAdaptedLinkTest {
    private static final String VALID_LINK_STRING = "https://linkedin.com";
    private static final String INVALID_LINK_STRING = "google.com";

    @Test
    public void toModelType_Link_returnsLink() throws Exception {
        URL VALID_URL = new URL(VALID_LINK_STRING);
        Link VALID_LINK = new Link(VALID_URL);
        JsonAdaptedLink link = new JsonAdaptedLink(VALID_LINK);
        assertEquals(VALID_LINK, link.toModelType());
    }

    @Test
    public void toModelType_invalidLink_throwsIllegalValueException() throws Exception {
        JsonAdaptedLink link = new JsonAdaptedLink(INVALID_LINK_STRING);
        String expectedMessage = "no protocol: " + INVALID_LINK_STRING;
        assertThrows(IllegalValueException.class, expectedMessage, link::toModelType);
    }
}

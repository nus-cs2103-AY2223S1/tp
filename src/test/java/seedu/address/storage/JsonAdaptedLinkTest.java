package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalLinks.LINK_GOOGLE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;


class JsonAdaptedLinkTest {

    @Test
    void toModelType_validLink_returnsLink() throws IllegalValueException {
        JsonAdaptedLink link = new JsonAdaptedLink("google", "https://google.com");
        assertEquals(LINK_GOOGLE, link.toModelType());
    }

    @Test
    void toModelType_invalidLink_throwsException() {
        JsonAdaptedLink link = new JsonAdaptedLink("validName" , "invalid_url");
        assertThrows(IllegalValueException.class, () -> link.toModelType());
    }
}

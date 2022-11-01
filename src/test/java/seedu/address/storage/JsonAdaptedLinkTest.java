package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_LINK_ALIAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLinks.VALID_LINK_1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.AddLinkCommandParser;
import seedu.address.model.module.link.Link;

public class JsonAdaptedLinkTest {

    @Test
    public void toModelType_invalidLinkAlias_throwsIllegalValueException() {
        JsonAdaptedLink invalidLink = new JsonAdaptedLink(INVALID_MODULE_LINK_ALIAS + ";" + VALID_MODULE_LINK_URL);
        String expectedMessage = Link.MESSAGE_CONSTRAINTS_ALIAS;
        assertThrows(IllegalValueException.class, expectedMessage, invalidLink::toModelType);
    }

    @Test
    public void toModelType_invalidLinkUrl_throwsIllegalValueException() {
        JsonAdaptedLink invalidLink = new JsonAdaptedLink(VALID_MODULE_LINK_ALIAS + ";" + "a");
        String expectedMessage = Link.MESSAGE_CONSTRAINTS_URL;
        assertThrows(IllegalValueException.class, expectedMessage, invalidLink::toModelType);
    }

    @Test
    public void toModelType_missingPartsLinkUrl_throwsIllegalValueException() {
        JsonAdaptedLink invalidLink = new JsonAdaptedLink(VALID_MODULE_LINK_ALIAS + ";");
        String expectedMessage = AddLinkCommandParser.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, invalidLink::toModelType);
    }

    @Test
    public void toModelType_extraPartsLinkUrl_throwsIllegalValueException() {
        JsonAdaptedLink invalidLink = new JsonAdaptedLink(VALID_MODULE_LINK_ALIAS + ";"
                + VALID_MODULE_LINK_URL + ";" + VALID_MODULE_LINK_URL);
        String expectedMessage = AddLinkCommandParser.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, invalidLink::toModelType);
    }

    @Test
    public void toModelType_missingDelimiter_throwsIllegalValueException() {
        JsonAdaptedLink invalidLink = new JsonAdaptedLink(VALID_MODULE_LINK_ALIAS + VALID_MODULE_LINK_URL);
        String expectedMessage = AddLinkCommandParser.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, invalidLink::toModelType);
    }

    @Test
    public void toModelType_validLinkAliasAndUrl_returnsLink() throws Exception {
        JsonAdaptedLink validLink = new JsonAdaptedLink(VALID_LINK_1);
        assertEquals(VALID_LINK_1, validLink.toModelType());
    }

    @Test
    public void toModelType_validLinkAliasAndUrlWithWhitespace_returnsLink() throws Exception {
        JsonAdaptedLink validLink = new JsonAdaptedLink(
                VALID_MODULE_LINK_ALIAS + "    ;    " + VALID_MODULE_LINK_URL);
        assertEquals(VALID_LINK_1, validLink.toModelType());
    }
}

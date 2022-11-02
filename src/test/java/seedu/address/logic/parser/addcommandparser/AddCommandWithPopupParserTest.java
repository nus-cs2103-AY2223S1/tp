package seedu.address.logic.parser.addcommandparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.addcommands.AddCommandWithPopup;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddCommandWithPopupParserTest {
    private AddCommandWithPopupParser parser = new AddCommandWithPopupParser();

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_emptyString_throwsParseException() {
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommandWithPopup.MESSAGE_USAGE);
        assertThrows(ParseException.class, expected, () -> parser.parse(""));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommandWithPopup.MESSAGE_USAGE);
        assertThrows(ParseException.class, expected, () -> parser.parse("dhbfneijfgn"));
    }

    @Test
    public void parse_deliverer_throwsParseException() {
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommandWithPopup.MESSAGE_USAGE);
        assertThrows(ParseException.class, expected, () -> parser.parse("deliverer"));
    }

    @Test
    public void parse_buyer_throwsParseException() {
        AddCommandWithPopup expectedCommand = new AddCommandWithPopup(AddCommandWithPopup.ADD_BUYER);
        try {
            // All caps -> success
            AddCommandWithPopup result = parser.parse(AddCommandWithPopup.ADD_BUYER);
            assertEquals(result, expectedCommand);

            // All lowercase -> success
            result = parser.parse("buyer");
            assertEquals(result, expectedCommand);

            // mixed case -> success
            result = parser.parse("BuYEr");
            assertEquals(result, expectedCommand);
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void parse_supplier_throwsParseException() {
        AddCommandWithPopup expectedCommand = new AddCommandWithPopup(AddCommandWithPopup.ADD_SUPPLIER);
        try {
            // All caps -> success
            AddCommandWithPopup result = parser.parse(AddCommandWithPopup.ADD_SUPPLIER);
            assertEquals(result, expectedCommand);

            // All lowercase -> success
            result = parser.parse("supplier");
            assertEquals(result, expectedCommand);

            // mixed case -> success
            result = parser.parse("SuPpLIEr");
            assertEquals(result, expectedCommand);
        } catch (ParseException e) {
            assert false;
        }
    }
}

package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CheckoutCommand;
import seedu.address.logic.parser.exceptions.ParseException;

class CheckoutCommandParserTest {
    private CheckoutCommandParser parser = new CheckoutCommandParser();

    @Test
    void parse_emptyString_throwsParseException() {
        assertThrows(ParseException.class, () -> new CheckoutCommandParser().parse(""));
    }

    @Test
    void parse_nullString_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CheckoutCommandParser().parse(null));
    }

    @Test
    void parse_invalidString_throwsParseException() {
        assertThrows(ParseException.class, () -> new CheckoutCommandParser().parse("*/?#"));
    }

    @Test
    void parse_validString_parseSuccessful() {
        String validString = "test";
        Path validPath = Paths.get("data", "test.json");
        assertParseSuccess(parser, validString, new CheckoutCommand(validPath));
    }

}

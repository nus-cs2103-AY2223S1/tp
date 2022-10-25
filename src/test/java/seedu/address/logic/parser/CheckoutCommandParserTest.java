package seedu.address.logic.parser;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;


class CheckoutCommandParserTest {

    @Test
    void parse_emptyString_throwsParseException() {
        assertThrows(ParseException.class, () -> new CheckoutCommandParser().parse(""));
    }

    @Test
    void parse_nullString_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CheckoutCommandParser().parse(null));
    }

}

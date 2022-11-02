package hobbylist.logic.parser;

import static hobbylist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static hobbylist.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import hobbylist.logic.commands.RateAboveCommand;

public class RateAboveCommandParserTest {
    private RateAboveCommandParser parser = new RateAboveCommandParser();

    @Test
    public void parse_emptyString_throwsParseException() {
        String expectedString = RateAboveCommandParser.INPUT_EMPTY;
        assertParseFailure(parser, "       ", expectedString);
    }

    @Test
    public void parse_nonInteger_throwsParseException() {
        String expectedString = RateAboveCommandParser.INPUT_FORMAT_WRONG;
        assertParseFailure(parser, "test", expectedString);
    }

    @Test
    public void parse_outOfBounds_throwsParseException() {
        String expectedString = RateAboveCommandParser.INPUT_OUT_OF_BOUND;
        assertParseFailure(parser, "200", expectedString);
    }

    @Test
    public void parse_success_returnsFindStatusCommand() {
        RateAboveCommand expectedCommand = new RateAboveCommand(1);
        assertParseSuccess(parser, "1", expectedCommand);
    }
}

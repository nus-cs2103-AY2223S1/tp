package longtimenosee.logic.parser;

import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static longtimenosee.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import longtimenosee.logic.commands.PinCommand;


public class PinCommandParserTest {
    private final PinCommandParser parser = new PinCommandParser();
    @Test
    public void parse_invalidIndex_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PinCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " what", expectedMessage);
        assertParseFailure(parser, "??", expectedMessage);
    }
    @Test
    public void parse_indexOutOfRange_throwsParseException() {
        String expectedMessage = "Invalid command format! \n" + "To use pin:\n"
                + "index should be indices shown on currently viewed list";
        assertParseFailure(parser, " -10", expectedMessage);
    }
}

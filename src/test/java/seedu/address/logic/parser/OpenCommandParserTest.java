package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.OpenCommand;



public class OpenCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, OpenCommand.MESSAGE_USAGE);

    private OpenCommandParser parser = new OpenCommandParser();

    @Test
    public void parse_missingParts_failure() {
        //no index specified
        assertParseFailure(parser, "s/WHATSAPP", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no socials specified
        assertParseFailure(parser, "1 #/hello", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5 s/WHATSAPP", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0 s/WHATSAPP", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }
}

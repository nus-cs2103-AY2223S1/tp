package soconnect.logic.parser.customise;

import static soconnect.logic.commands.customise.CustomiseCommand.MESSAGE_UNKNOWN_CUSTOMISE_COMMAND;
import static soconnect.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import soconnect.logic.commands.customise.CustomiseCommand;

class CustomiseCommandParserTest {

    private CustomiseCommandParser parser = new CustomiseCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_UNKNOWN_CUSTOMISE_COMMAND,
                CustomiseCommand.MESSAGE_USAGE));
    }
}

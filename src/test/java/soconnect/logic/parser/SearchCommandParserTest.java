package soconnect.logic.parser;

import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import soconnect.logic.commands.SearchCommand;

public class SearchCommandParserTest {
    private final String failureMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE);
    private final SearchCommandParser parser = new SearchCommandParser();

    @Test
    void parse_noValidArguments_failure() {
        assertParseFailure(parser, "", failureMessage);
        assertParseFailure(parser, "     ", failureMessage);
        assertParseFailure(parser, "abcd", failureMessage);
        assertParseFailure(parser, "an/namep/phone", failureMessage);
    }

}

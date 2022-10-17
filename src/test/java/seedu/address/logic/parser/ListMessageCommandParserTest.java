package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.ListMessageCommand;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

class ListMessageCommandParserTest {
    private ListMessageCommandParser parser = new ListMessageCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteMessageCommand() {
        assertParseSuccess(parser, "", new ListMessageCommand());
        assertParseSuccess(parser, "1920183", new ListMessageCommand());
        assertParseSuccess(parser, "Hello World!", new ListMessageCommand());
    }
}

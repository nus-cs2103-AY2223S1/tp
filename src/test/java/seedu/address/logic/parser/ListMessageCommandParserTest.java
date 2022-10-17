package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListMessageCommand;

class ListMessageCommandParserTest {
    private ListMessageCommandParser parser = new ListMessageCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteMessageCommand() {
        assertParseSuccess(parser, "", new ListMessageCommand());
        assertParseSuccess(parser, "1920183", new ListMessageCommand());
        assertParseSuccess(parser, "Hello World!", new ListMessageCommand());
    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GetIdCommand;

public class GetIdCommandParserTest {

    private GetIdCommandParser parser = new GetIdCommandParser();

    @Test
    public void parse_invalidArgs_throwsMissingNameException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetIdCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "n/ ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetIdCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsGetIdCommand() {
        GetIdCommand expectedGetIdCommand =
                new GetIdCommand("Alice");
        assertParseSuccess(parser, " n/Alice", expectedGetIdCommand);
    }

}

package seedu.address.logic.commands.client;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ClientCommandParser;

class SortClientCommandParserTest {

    private final ClientCommandParser parser = new ClientCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser,
                SortClientCommand.COMMAND_FLAG, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortClientCommand.MESSAGE_USAGE));
        assertParseFailure(parser,
                SortClientCommand.COMMAND_FLAG, "delete",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortClientCommand.MESSAGE_USAGE));
    }

}

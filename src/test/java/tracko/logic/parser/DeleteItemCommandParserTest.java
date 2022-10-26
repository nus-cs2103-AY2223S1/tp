package tracko.logic.parser;

import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tracko.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tracko.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static tracko.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import tracko.logic.commands.item.DeleteItemCommand;
import tracko.logic.parser.item.DeleteItemCommandParser;

public class DeleteItemCommandParserTest {
    private DeleteItemCommandParser parser = new DeleteItemCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteItemCommand() {
        assertParseSuccess(parser, "1", new DeleteItemCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteItemCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteItemCommand.MESSAGE_USAGE));
    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteMessageCommand;

class DeleteMessageCommandParserTest {
    private DeleteMessageCommandParser parser = new DeleteMessageCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteMessageCommand() {
        assertParseSuccess(parser, "1", new DeleteMessageCommand(Index.fromOneBased(1)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMessageCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "0", Messages.MESSAGE_INVALID_MESSAGE_INDEX);
        assertParseFailure(parser, "-5116101128118950845L", DeleteMessageCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "1.1", DeleteMessageCommand.MESSAGE_USAGE);
    }
}

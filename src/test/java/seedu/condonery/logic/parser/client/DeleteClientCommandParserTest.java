package seedu.condonery.logic.parser.client;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.condonery.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.condonery.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.condonery.logic.commands.client.DeleteClientCommand;

public class DeleteClientCommandParserTest {
    private final DeleteClientCommandParser parser = new DeleteClientCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteClientCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteClientCommand.MESSAGE_USAGE));
    }
}

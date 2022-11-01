package tuthub.logic.parser;

import static tuthub.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tuthub.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tuthub.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static tuthub.testutil.TypicalIndexes.INDEX_FIRST_TUTOR;

import org.junit.jupiter.api.Test;

import tuthub.logic.commands.DeleteCommand;

public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(INDEX_FIRST_TUTOR));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}

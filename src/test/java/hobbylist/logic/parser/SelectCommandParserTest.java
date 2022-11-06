package hobbylist.logic.parser;

import static hobbylist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import hobbylist.logic.commands.SelectCommand;
import hobbylist.testutil.TypicalIndexes;

public class SelectCommandParserTest {
    private SelectCommandParser parser = new SelectCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "1",
                new SelectCommand(TypicalIndexes.INDEX_FIRST_ACTIVITY));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE));
    }
}

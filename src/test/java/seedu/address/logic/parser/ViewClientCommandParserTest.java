package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ELEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewClientCommand;

public class ViewClientCommandParserTest {
    private ViewClientCommandParser parser = new ViewClientCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, " i/1", new ViewClientCommand(INDEX_FIRST_ELEMENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Invalid value after prefix
        assertParseFailure(parser, " i/a", ParserUtil.MESSAGE_INVALID_INDEX);

        // Negative integer
        assertParseFailure(parser, " i/-1", ParserUtil.MESSAGE_INVALID_INDEX);

        // Number with decimal
        assertParseFailure(parser, " i/1.5", ParserUtil.MESSAGE_INVALID_INDEX);

        // No prefix
        assertParseFailure(parser, "12",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewClientCommand.MESSAGE_USAGE));

        // Empty string
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewClientCommand.MESSAGE_USAGE));
    }
}

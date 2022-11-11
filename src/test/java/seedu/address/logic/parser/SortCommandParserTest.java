package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;

public class SortCommandParserTest {
    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_validAlpha_returnsSortCommand() {
        assertParseSuccess(parser, "alpha",
                new SortCommand(SortCommand.SortBy.ALPHA));
    }

    @Test
    public void parse_validReverse_returnsSortCommand() {
        assertParseSuccess(parser, "reverse",
                new SortCommand(SortCommand.SortBy.REVERSE));
    }

    @Test
    public void parse_validDefault_returnsSortCommand() {
        assertParseSuccess(parser, "default",
                new SortCommand(SortCommand.SortBy.DEFAULT));
    }

    @Test
    public void parse_validAlphaCapitalized_returnsSortCommand() {
        assertParseSuccess(parser, "ALPHA",
                new SortCommand(SortCommand.SortBy.ALPHA));
    }

    @Test
    public void parse_invalidArgs_throwParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.FEEDBACK_MESSAGE));
    }
}

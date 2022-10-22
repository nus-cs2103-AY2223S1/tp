package seedu.intrack.logic.parser;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.intrack.logic.commands.FilterCommand;
import seedu.intrack.model.internship.StatusIsKeywordPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_returnsFilterCommand() {
        // wrong letter
        assertParseFailure(parser, "Q", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.FILTER_COMMAND_CONSTRAINTS));
        // extra letter
        assertParseFailure(parser, "rr", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.FILTER_COMMAND_CONSTRAINTS));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        FilterCommand expectedFilterCommand =
                new FilterCommand(new StatusIsKeywordPredicate("Rejected"));
        // uppercase
        assertParseSuccess(parser, "R", expectedFilterCommand);
        // lowercase
        assertParseSuccess(parser, "r", expectedFilterCommand);
    }

}

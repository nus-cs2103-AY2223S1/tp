package tuthub.logic.parser;

import static tuthub.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tuthub.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tuthub.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import tuthub.logic.commands.SortCommand;
import tuthub.model.tutor.SortByRatingComparator;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortCommand() {
        // no leading and trailing whitespaces
        SortCommand expectedSortCommand =
                new SortCommand("a", new Prefix("r/"), new SortByRatingComparator("a"));
        assertParseSuccess(parser, "a r/", expectedSortCommand);

        // multiple whitespaces between input
        assertParseSuccess(parser, " \n a \n \t r/  \t", expectedSortCommand);
    }

}

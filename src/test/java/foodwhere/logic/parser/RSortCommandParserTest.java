package foodwhere.logic.parser;

import static foodwhere.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static foodwhere.logic.parser.CommandParserTestUtil.assertParseFailure;
import static foodwhere.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import foodwhere.logic.commands.RSortCommand;
import foodwhere.model.review.comparator.ReviewsComparatorList;

public class RSortCommandParserTest {

    private RSortCommandParser parser = new RSortCommandParser();

    @Test
    public void parse_validArgs_returnsRSortCommand() {
        assertParseSuccess(parser, "name", new RSortCommand(ReviewsComparatorList.valueOf("NAME")));
        assertParseSuccess(parser, "Name", new RSortCommand(ReviewsComparatorList.valueOf("NAME")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RSortCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "notSupportedCriterion",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RSortCommand.MESSAGE_USAGE));
    }
}

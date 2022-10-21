package foodwhere.logic.parser;

import static foodwhere.logic.parser.CommandParserTestUtil.assertParseFailure;
import static foodwhere.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import foodwhere.commons.core.Messages;
import foodwhere.logic.commands.RFindCommand;
import foodwhere.model.review.NameContainsStallPredicate;

public class RFindCommandParserTest {

    private RFindCommandParser parser = new RFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, RFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnRFindCommand() {
        // no leading and trailing whitespaces
        RFindCommand expectedRFindCommand =
                new RFindCommand(new NameContainsStallPredicate(Arrays.asList("John", "Eatery")));
        assertParseSuccess(parser, "John Eatery", expectedRFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n John \n \t Eatery  \t", expectedRFindCommand);
    }

}

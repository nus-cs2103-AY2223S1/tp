package foodwhere.logic.parser;

import static foodwhere.logic.parser.CommandParserTestUtil.assertParseFailure;
import static foodwhere.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import foodwhere.commons.core.Messages;
import foodwhere.logic.commands.SFindCommand;
import foodwhere.model.stall.NameContainsKeywordsPredicate;

public class SFindCommandParserTest {

    private SFindCommandParser parser = new SFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        SFindCommand expectedSFindCommand =
                new SFindCommand(new NameContainsKeywordsPredicate(Arrays.asList("John", "Eatery")));
        assertParseSuccess(parser, "John Eatery", expectedSFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n John \n \t Eatery  \t", expectedSFindCommand);
    }

}

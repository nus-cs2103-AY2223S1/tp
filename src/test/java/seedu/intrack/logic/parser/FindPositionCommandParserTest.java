package seedu.intrack.logic.parser;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.intrack.logic.commands.FindPositionCommand;
import seedu.intrack.model.internship.PositionContainsKeywordsPredicate;

public class FindPositionCommandParserTest {

    private FindPositionCommandParser parser = new FindPositionCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindPositionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindPositionCommand expectedFindPositionCommand =
                new FindPositionCommand(
                new PositionContainsKeywordsPredicate(Arrays.asList("Software", "Developer")));
        assertParseSuccess(parser, "Software Developer", expectedFindPositionCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Software \n \t Developer  \t", expectedFindPositionCommand);
    }

}

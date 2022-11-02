package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindStuCommand;
import seedu.address.model.student.StuNameContainsKeywordsPredicate;

public class FindStuCommandParserTest {

    private FindStuCommandParser parser = new FindStuCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT
                ,FindStuCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindStuCommand() {
        // no leading and trailing whitespaces
        FindStuCommand expectedFindStuCommand =
                new FindStuCommand(new StuNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindStuCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindStuCommand);
    }
}

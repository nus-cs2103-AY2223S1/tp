package seedu.intrack.logic.parser;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.intrack.logic.commands.FindNameCommand;
import seedu.intrack.model.internship.NameContainsKeywordsPredicate;

public class FindNameCommandParserTest {

    private FindNameCommandParser parser = new FindNameCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindNameCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindNameCommand expectedFindNameCommand =
                new FindNameCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindNameCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindNameCommand);
    }

}

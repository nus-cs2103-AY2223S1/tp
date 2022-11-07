package seedu.address.logic.parser.profile;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.profile.FindProfileCommandParser.MESSAGE_MISSING_KEYWORDS;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.profile.FindProfileCommand;
import seedu.address.model.profile.NameContainsKeywordsPredicate;

public class FindProfileCommandParserTest {

    private FindProfileCommandParser parser = new FindProfileCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "      ",
                String.format(MESSAGE_MISSING_KEYWORDS + FindProfileCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindProfileCommand expectedFindCommand =
                new FindProfileCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "     Alice   Bob  ", expectedFindCommand);
    }

}

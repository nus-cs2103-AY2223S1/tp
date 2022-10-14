package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.tag.TagsContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_tooManyPrefixArg_throwsParseException() {
        assertParseFailure(parser, "find n/John t/friend",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces for names
        FindCommand expectedFindCommand =
            new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " n/Alice n/Bob", expectedFindCommand);

        // multiple whitespaces between keywords for names
        assertParseSuccess(parser, " \n n/  Alice  \t n/  \n Bob  \t", expectedFindCommand);

        // no leading and trailing whitespaces for tags
        expectedFindCommand =
            new FindCommand(new TagsContainsKeywordsPredicate(Arrays.asList("friend", "school")));
        assertParseSuccess(parser, " t/friend t/school", expectedFindCommand);

        // multiple whitespaces between keywords for tags
        assertParseSuccess(parser, " \n t/friend \n \t t/school  \t", expectedFindCommand);
    }

}

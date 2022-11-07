package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.PersonMatchesKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        PersonMatchesKeywordsPredicate predicateOne = new PersonMatchesKeywordsPredicate();
        predicateOne.setKeywords("Alice Bob");
        FindCommand expectedFindCommandOne =
                new FindCommand(predicateOne);
        assertParseSuccess(parser, "Alice Bob", expectedFindCommandOne);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " Alice Bob ", expectedFindCommandOne);

        PersonMatchesKeywordsPredicate predicateTwo = new PersonMatchesKeywordsPredicate();
        predicateTwo.setKeywords("John");
        FindCommand expectedFindCommandTwo =
                new FindCommand(predicateTwo);
        assertParseSuccess(parser, "John", expectedFindCommandTwo);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " John  ", expectedFindCommandTwo);
    }

}

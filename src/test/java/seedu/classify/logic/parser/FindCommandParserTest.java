package seedu.classify.logic.parser;

import static seedu.classify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.classify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.classify.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.classify.logic.commands.FindCommand;
import seedu.classify.model.student.Id;
import seedu.classify.model.student.IdPredicate;
import seedu.classify.model.student.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsViewCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " nm/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " nm/\n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validIdArgs_returnsViewCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new IdPredicate(new Id("123A")));
        assertParseSuccess(parser, " id/123a", expectedFindCommand);

        assertParseSuccess(parser, " id/ \t 123a \n", expectedFindCommand);
    }

}

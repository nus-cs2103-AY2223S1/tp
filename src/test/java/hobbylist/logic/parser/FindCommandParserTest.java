package hobbylist.logic.parser;

import static hobbylist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static hobbylist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static hobbylist.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import hobbylist.logic.commands.FindCommand;
import hobbylist.model.activity.DateMatchesGivenDatePredicate;
import hobbylist.model.activity.NameOrDescContainsKeywordsPredicate;
import hobbylist.model.activity.RatingMatchesGivenValuePredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameOrDescContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        new DateMatchesGivenDatePredicate(""),
                        new RatingMatchesGivenValuePredicate(-1));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

}

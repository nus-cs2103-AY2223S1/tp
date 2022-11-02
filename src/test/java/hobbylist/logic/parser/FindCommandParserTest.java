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
    public void parse_invalidDateKeyword_throwsParseException() {
        assertParseFailure(parser, "date/20 02", FindCommand.MESSAGE_INVALID_DATE_VALUE);
    }

    @Test
    public void parse_invalidRatingKeyword_throwsParseException() {
        assertParseFailure(parser, "rate/abc", FindCommand.MESSAGE_INVALID_RATING_VALUE);
    }

    @Test
    public void parse_validArgsNoSpecialKeywords_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameOrDescContainsKeywordsPredicate(Arrays.asList("Battle", "Game")),
                        new DateMatchesGivenDatePredicate(""),
                        new RatingMatchesGivenValuePredicate(-1));
        assertParseSuccess(parser, "Battle Game", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Battle \n \t Game  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgsWithDateKeywords_returnFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(
                        new NameOrDescContainsKeywordsPredicate(Arrays.asList("soccer", "Final", "date/2022-12-20")),
                        new DateMatchesGivenDatePredicate("2022-12-20"),
                        new RatingMatchesGivenValuePredicate(-1));
        assertParseSuccess(parser, "soccer Final date/2022-12-20", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n soccer \n \t Final  \t  \t date/2022-12-20   \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgsWithRateKeywords_returnFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(
                        new NameOrDescContainsKeywordsPredicate(Arrays.asList("soccer", "Final", "rate/4")),
                        new DateMatchesGivenDatePredicate(""),
                        new RatingMatchesGivenValuePredicate(4));
        assertParseSuccess(parser, "soccer Final rate/4", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n soccer \n \t Final  \t  \t rate/4   \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgsAllKeywords_returnFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(
                        new NameOrDescContainsKeywordsPredicate(Arrays.asList("soccer", "date/2022-01-01", "rate/4")),
                        new DateMatchesGivenDatePredicate("2022-01-01"),
                        new RatingMatchesGivenValuePredicate(4));
        assertParseSuccess(parser, "soccer date/2022-01-01 rate/4", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n soccer \n   date/2022-01-01  \t  \t rate/4   \t", expectedFindCommand);
    }

}

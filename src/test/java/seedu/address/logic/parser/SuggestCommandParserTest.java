package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_TIME_OF_WEEK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KEYWORD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SuggestCommand;
import seedu.address.model.person.DayTimeInWeek;
import seedu.address.model.person.Keyword;
import seedu.address.model.person.PersonSuggestionPredicate;

public class SuggestCommandParserTest {

    private SuggestCommandParser parser = new SuggestCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SuggestCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSuggestCommand() {
        // no leading and trailing whitespaces
        Set<Keyword> keywordSet = new HashSet<>();
        Set<DayTimeInWeek> dayTimeInWeekSet = new HashSet<>();

        keywordSet.add(new Keyword("victoria"));
        keywordSet.add(new Keyword("12345"));

        dayTimeInWeekSet.add(new DayTimeInWeek("mon@2359"));
        dayTimeInWeekSet.add(new DayTimeInWeek("fri@0000"));

        SuggestCommand expected =
                new SuggestCommand(new PersonSuggestionPredicate(dayTimeInWeekSet, keywordSet));
        assertParseSuccess(parser,
                " " + PREFIX_DAY_TIME_OF_WEEK + "mon@2359 " + PREFIX_DAY_TIME_OF_WEEK + "fri@0000 "
                + PREFIX_KEYWORD + "12345 " + PREFIX_KEYWORD + "victoria", expected);
    }

    @Test
    public void parse_missingPrefixes_throwsParseException() {
        String message = "test";
        assertParseFailure(parser, message,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SuggestCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingPrefixForDayTimeInWeek_throwsParseException() {
        // no leading and trailing whitespaces
        String message = " mon@2359 " + PREFIX_KEYWORD + " test";
        assertParseFailure(parser, message,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SuggestCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingPrefixForKeywords_throwsParseException() {
        String message = " test_keyword " + PREFIX_DAY_TIME_OF_WEEK + " mon@2359";
        assertParseFailure(parser, message,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SuggestCommand.MESSAGE_USAGE));
    }

}

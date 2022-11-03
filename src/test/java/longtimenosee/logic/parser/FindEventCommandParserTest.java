package longtimenosee.logic.parser;

import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static longtimenosee.logic.parser.CommandParserTestUtil.assertParseFailure;
import static longtimenosee.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import longtimenosee.logic.commands.FindEventCommand;
import longtimenosee.model.event.Date;
import longtimenosee.model.event.Description;
import longtimenosee.model.event.predicate.DescriptionContainsKeywordsPredicate;
import longtimenosee.model.event.predicate.EventDateMatchesInputPredicate;
import longtimenosee.model.event.predicate.NameInEventContainsKeywordsPredicate;
import longtimenosee.model.person.Name;

public class FindEventCommandParserTest {
    private final FindEventCommandParser parser = new FindEventCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // EP: empty arguments
        assertParseFailure(parser, "   ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validDescriptionArgs_returnsFindEventCommand() {
        // EP: valid input no leading and trailing whitespaces
        FindEventCommand expectedFindEventCommand =
                new FindEventCommand(List.of(new DescriptionContainsKeywordsPredicate("meeting to discuss plans")));
        assertParseSuccess(parser, " desc/meeting to discuss plans", expectedFindEventCommand);

        // EP: valid input with whitespaces at edges
        assertParseSuccess(parser, " desc/ meeting to discuss plans  ", expectedFindEventCommand);

        // EP: valid alphanumeric input
        FindEventCommand alphanumericExpectedFindEventCommand =
                new FindEventCommand(List.of(new DescriptionContainsKeywordsPredicate("5th meeting")));
        assertParseSuccess(parser, " desc/ 5th meeting ", alphanumericExpectedFindEventCommand);
    }

    @Test
    public void parse_invalidDescriptionArgs_throwsParseException() {
        // EP: empty input
        assertParseFailure(parser, " desc/", Description.MESSAGE_CONSTRAINTS); // Boundary value

        // EP: non-alphanumeric input: symbols
        assertParseFailure(parser, " desc/!", Description.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validDateArgs_returnsFindEventCommand() {
        // EP: valid input
        FindEventCommand expectedFindEventCommand =
                new FindEventCommand(List.of(new EventDateMatchesInputPredicate("2019-05-12")));
        assertParseSuccess(parser, " date/2019-05-12", expectedFindEventCommand);
    }

    @Test
    public void parse_invalidDateArgs_throwsParseException() {
        // EP: empty input
        assertParseFailure(parser, " date/", Date.MESSAGE_FORMAT_CONSTRAINTS); // Boundary value

        // EP: non date input
        assertParseFailure(parser, " date/123", Date.MESSAGE_FORMAT_CONSTRAINTS);

        // EP: non numeric input
        assertParseFailure(parser, " date/abc", Date.MESSAGE_FORMAT_CONSTRAINTS);

        // EP: improper date format
        assertParseFailure(parser, " date/1980-123-20", Date.MESSAGE_FORMAT_CONSTRAINTS);
    }

    @Test
    public void parse_validNameInEventArgs_returnsFindEventCommand() {
        // EP: valid input no leading and trailing whitespaces
        FindEventCommand expectedFindEventCommand =
                new FindEventCommand(List.of(new NameInEventContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"))));
        assertParseSuccess(parser, " n/Alice Bob", expectedFindEventCommand);

        // EP: valid input with multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ Alice  Bob  ", expectedFindEventCommand);

        // EP: valid alphanumeric input
        FindEventCommand alphanumericExpectedFindEventCommand =
                new FindEventCommand(List.of(new NameInEventContainsKeywordsPredicate(Arrays.asList("Alice", "123"))));
        assertParseSuccess(parser, " n/ Alice 123 ", alphanumericExpectedFindEventCommand);
    }

    @Test
    public void parse_invalidNameArgs_throwsParseException() {
        // EP: empty input
        assertParseFailure(parser, " n/", Name.MESSAGE_CONSTRAINTS); // Boundary value

        // EP: non-alphanumeric input: symbols
        assertParseFailure(parser, " n/!", Name.MESSAGE_CONSTRAINTS);
    }
}

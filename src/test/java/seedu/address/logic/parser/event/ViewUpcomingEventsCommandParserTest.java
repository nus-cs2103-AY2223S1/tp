package seedu.address.logic.parser.event;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.event.ViewUpcomingEventsCommand;

public class ViewUpcomingEventsCommandParserTest {
    private ViewUpcomingEventsCommandParser parser = new ViewUpcomingEventsCommandParser();

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "      ",
                String.format(ViewUpcomingEventsCommand.MESSAGE_MISSING_DAYS,
                        ViewUpcomingEventsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsViewUpcomingEventsCommand() {
        // no leading and trailing whitespace
        ViewUpcomingEventsCommand expectedViewUpcomingEventsCommand = new ViewUpcomingEventsCommand(1);
        assertParseSuccess(parser, "1", expectedViewUpcomingEventsCommand);

        // multiple whitespaces
        assertParseSuccess(parser, "   1   ", expectedViewUpcomingEventsCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "asdfasdfa",
                String.format(ViewUpcomingEventsCommand.MESSAGE_INVALID_EVENT_UPCOMING_DAYS,
                        ViewUpcomingEventsCommand.MESSAGE_USAGE));
    }
}

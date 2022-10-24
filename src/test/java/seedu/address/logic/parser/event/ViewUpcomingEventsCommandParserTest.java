package seedu.address.logic.parser.event;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.event.ViewUpcomingEventsCommand;
import seedu.address.model.event.StartDateWithinTimeFramePredicate;

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
        final int days = 1;
        LocalDate currentDate = java.time.LocalDate.now();
        LocalDate endDate = currentDate.plusDays(days);
        ViewUpcomingEventsCommand expectedViewUpcomingEventsCommand =
                new ViewUpcomingEventsCommand(days, new StartDateWithinTimeFramePredicate(currentDate, endDate));
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

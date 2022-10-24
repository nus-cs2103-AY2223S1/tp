package seedu.address.logic.parser.event;

import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import java.time.LocalDate;

import seedu.address.logic.commands.event.ViewUpcomingEventsCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.StartDateWithinTimeFramePredicate;

/**
 * Parses input arguments and creates a new ViewUpcomingEventCommand object
 */
public class ViewUpcomingEventsCommandParser implements Parser<ViewUpcomingEventsCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewUpcomingEventCommand
     * and returns a ViewUpcomingEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewUpcomingEventsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_OPTION);
        String daysInput = argMultimap.getOptionArgs();

        if (daysInput.isEmpty()) {
            throw new ParseException(String.format(ViewUpcomingEventsCommand.MESSAGE_MISSING_DAYS,
                    ViewUpcomingEventsCommand.MESSAGE_USAGE));
        }

        try {
            int days = ParserUtil.parseDays(daysInput);
            LocalDate currentDate = java.time.LocalDate.now();
            LocalDate endDate = currentDate.plusDays(days);
            StartDateWithinTimeFramePredicate predicate = new StartDateWithinTimeFramePredicate(currentDate, endDate);
            return new ViewUpcomingEventsCommand(days, predicate);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(ViewUpcomingEventsCommand.MESSAGE_INVALID_EVENT_UPCOMING_DAYS,
                            ViewUpcomingEventsCommand.MESSAGE_USAGE), pe);
        }
    }
}

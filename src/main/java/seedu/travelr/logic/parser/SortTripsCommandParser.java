package seedu.travelr.logic.parser;

import static seedu.travelr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_REVERSE_ORDER;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_SORTBY;
import static seedu.travelr.model.trip.TripComparators.COMPARE_BY_LOCATION;
import static seedu.travelr.model.trip.TripComparators.COMPARE_BY_NUM_EVENTS;
import static seedu.travelr.model.trip.TripComparators.COMPARE_BY_TIME;
import static seedu.travelr.model.trip.TripComparators.COMPARE_BY_TITLE;

import java.util.Comparator;

import seedu.travelr.logic.commands.SortTripsCommand;
import seedu.travelr.logic.parser.exceptions.ParseException;
import seedu.travelr.model.trip.Trip;



/**
 * Parses input arguments and creates a new SortTripsCommand object
 */
public class SortTripsCommandParser implements Parser<SortTripsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortTripsCommand
     * and returns an SortTripsCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortTripsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SORTBY, PREFIX_REVERSE_ORDER);

        String sortBy = argMultimap.getValue(PREFIX_SORTBY).orElse("").toLowerCase();
        boolean reverse = argMultimap.getValue(PREFIX_REVERSE_ORDER).map(x -> true).orElse(false);

        Comparator<Trip> comp;

        switch (sortBy) {
        case "":
        case "title":
            comp = COMPARE_BY_TITLE;
            break;
        case "time":
            comp = COMPARE_BY_TIME;
            break;
        case "location":
            comp = COMPARE_BY_LOCATION;
            break;
        case "eventcount":
            comp = COMPARE_BY_NUM_EVENTS;
            break;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortTripsCommand.MESSAGE_USAGE));
        }

        final Comparator<Trip> originalComp = comp;

        comp = reverse ? (x, y) -> -1 * originalComp.compare(x, y) : comp;

        return new SortTripsCommand(comp);
    }
}

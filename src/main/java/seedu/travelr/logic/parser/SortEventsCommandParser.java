package seedu.travelr.logic.parser;

import static seedu.travelr.logic.parser.CliSyntax.PREFIX_REVERSE_ORDER;

import java.util.Comparator;

import seedu.travelr.logic.commands.SortEventsCommand;
import seedu.travelr.model.event.Event;

/**
 * Parses input arguments and creates a new SortEventsCommand object.
 */
public class SortEventsCommandParser implements Parser<SortEventsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortTripsCommand
     * and returns an SortTripsCommand object for execution.
     */
    public SortEventsCommand parse(String args) {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_REVERSE_ORDER);
        boolean reverse = argMultimap.getValue(PREFIX_REVERSE_ORDER).map(x -> true).orElse(false);
        Comparator<Event> comp = (x, y) -> (reverse ? -1 : 1) * x.compareTo(y);
        return new SortEventsCommand(comp);
    }

}

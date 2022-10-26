package seedu.travelr.logic.parser;

import seedu.travelr.logic.commands.SortEventsCommand;
import seedu.travelr.model.event.Event;

import java.util.Comparator;

import static seedu.travelr.logic.parser.CliSyntax.PREFIX_REVERSE_ORDER;

public class SortEventsCommandParser implements Parser<SortEventsCommand> {
    
    public SortEventsCommand parse(String args) {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_REVERSE_ORDER);
        boolean reverse = argMultimap.getValue(PREFIX_REVERSE_ORDER).map(x -> true).orElse(false);
        Comparator<Event> comp = (x, y) -> (reverse ? -1 : 1) * x.compareTo(y);
        return new SortEventsCommand(comp);
    }
    
}

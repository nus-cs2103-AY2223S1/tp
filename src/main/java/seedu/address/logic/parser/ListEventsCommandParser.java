package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT;

import seedu.address.logic.commands.ListEventsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventSortField;

/**
 * Parses input arguments and creates and new ListEventsCommand object.
 */
public class ListEventsCommandParser implements Parser<ListEventsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListEventsCommand
     * and returns a {@code ListEventsCommand} for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ListEventsCommand parse(String args) throws ParseException {

        EventSortField sortField;
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_SORT);

        // If sort field is missing
        if (!argMultiMap.getValue(PREFIX_SORT).isPresent()) {
            // Don't perform any sorting
            sortField = EventSortField.sortByNoField();

        // Else sort field is present
        } else {
            sortField = ParserUtil.parseEventSortField(argMultiMap.getValue(PREFIX_SORT).get());
        }

        return new ListEventsCommand(sortField);
    }

}

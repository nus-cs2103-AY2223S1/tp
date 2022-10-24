package seedu.address.logic.parser;

import seedu.address.logic.commands.ListTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.SortByDeadlineComparator;

/**
 * Parses input arguments and creates a new ListTaskCommand object
 */
public class ListTaskCommandParser implements Parser<ListTaskCommand>{

    private static final String LIST_BY_DEADLINE_KEYWORD = "time";

    /**
     * Parses the given {@code String} of arguments in the context of the ListTaskCommand
     * and returns a ListTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListTaskCommand parse(String args) throws ParseException {
        if (args.trim().equals(LIST_BY_DEADLINE_KEYWORD)) {
            return new ListTaskCommand(new SortByDeadlineComparator());
        } else {
            return new ListTaskCommand(null);
        }
    }
}

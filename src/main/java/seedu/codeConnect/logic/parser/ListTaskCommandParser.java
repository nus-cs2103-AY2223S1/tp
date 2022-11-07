package seedu.codeConnect.logic.parser;

import seedu.codeConnect.logic.commands.ListTaskCommand;
import seedu.codeConnect.model.task.DeadlineComparator;
import seedu.codeConnect.model.task.DefaultComparator;

/**
 * Parses input arguments and creates a new ListTaskCommand object
 */
public class ListTaskCommandParser implements Parser<ListTaskCommand> {

    private static final String LIST_BY_DEADLINE_KEYWORD = "time";

    /**
     * Parses the given {@code String} of arguments in the context of the ListTaskCommand
     * and returns a ListTaskCommand object for execution.
     */
    public ListTaskCommand parse(String args) {
        if (args.trim().equals(LIST_BY_DEADLINE_KEYWORD)) {
            return new ListTaskCommand(new DeadlineComparator());
        } else {
            return new ListTaskCommand(new DefaultComparator());
        }
    }
}

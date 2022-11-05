package tracko.logic.parser.order;

import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import tracko.logic.commands.order.SortOrdersCommand;
import tracko.logic.parser.Parser;
import tracko.logic.parser.exceptions.ParseException;
import tracko.model.order.OrderDateTimeComparator;

/**
 * Parses input arguments and creates a new SortOrdersCommand object
 */
public class SortOrdersCommandParser implements Parser<SortOrdersCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortOrdersCommand
     * and returns a SortOrdersCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortOrdersCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()
                || (!trimmedArgs.equalsIgnoreCase("new")
                    && !trimmedArgs.equalsIgnoreCase("old"))) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortOrdersCommand.MESSAGE_USAGE));
        }

        String compareKeyword = trimmedArgs;

        OrderDateTimeComparator comparator = new OrderDateTimeComparator(compareKeyword);

        return new SortOrdersCommand(comparator);
    }

}

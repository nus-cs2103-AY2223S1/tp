package tracko.logic.parser.order;

import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import tracko.logic.commands.order.SortOrderCommand;
import tracko.logic.parser.Parser;
import tracko.logic.parser.exceptions.ParseException;
import tracko.model.order.OrderDateTimeComparator;

/**
 * Parses input arguments and creates a new SortOrderCommand object
 */
public class SortOrderCommandParser implements Parser<SortOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortOrderCommand
     * and returns a SortOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortOrderCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()
                || (!trimmedArgs.equalsIgnoreCase("new")
                    && !trimmedArgs.equalsIgnoreCase("old"))) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortOrderCommand.MESSAGE_USAGE));
        }

        String compareKeyword = trimmedArgs;

        OrderDateTimeComparator comparator = new OrderDateTimeComparator(compareKeyword);

        return new SortOrderCommand(comparator);
    }

}

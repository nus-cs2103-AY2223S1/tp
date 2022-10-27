package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.SortCommand.MESSAGE_UNKNOWN_ORDER_KEYWORD;
import static seedu.address.logic.commands.SortCommand.MESSAGE_UNKNOWN_TYPE_KEYWORD;
import static seedu.address.logic.commands.SortCommand.MESSAGE_USAGE;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortCommand.Order;
import seedu.address.logic.commands.SortCommand.Type;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {
    private static final String GENERAL_DEFAULT_SORT_ORDER = "asc";
    private static final String OWED_DEFAULT_SORT_ORDER = "desc";

    /**
     * Parses the given {@code String args} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] splitArguments = args.trim().split(" ");
        String typeString = splitArguments[0];
        if (splitArguments.length > 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        Type type;
        try {
            type = ParserUtil.parseSortType(typeString);
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_UNKNOWN_TYPE_KEYWORD), pe);
        }

        String orderString;
        if (splitArguments.length == 1) {
            if (type.equals(Type.OWED)) {
                orderString = OWED_DEFAULT_SORT_ORDER;
            } else {
                orderString = GENERAL_DEFAULT_SORT_ORDER;
            }
        } else if (splitArguments.length == 2) {
            orderString = splitArguments[1];
        } else {
            // this block will never be reached as the logic has been shifted above.
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        Order order;
        try {
            order = ParserUtil.parseSortOrder(orderString);
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_UNKNOWN_ORDER_KEYWORD), pe);
        }
        return new SortCommand(type, order);
    }

}

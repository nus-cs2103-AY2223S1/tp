package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.SortCommand.MESSAGE_USAGE;
import static seedu.address.logic.commands.SortCommand.MESSAGE_UNKNOWN_ORDER_KEYWORD;
import static seedu.address.logic.commands.SortCommand.MESSAGE_UNKNOWN_TYPE_KEYWORD;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortCommand.ORDER;
import seedu.address.logic.commands.SortCommand.TYPE;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {
    private static final String DEFAULT_SORT_ORDER = "asc";

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] splitArguments = args.trim().split(" ");
        String typeString = splitArguments[0];
        String orderString;
        if (splitArguments.length == 1) {
            orderString = DEFAULT_SORT_ORDER;
        } else if (splitArguments.length == 2) {
            orderString = splitArguments[1];
        } else {
            // todo: error message to be updated
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
        TYPE type;
        ORDER order;
        try {
            type = ParserUtil.parseSortType(typeString);
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_UNKNOWN_TYPE_KEYWORD), pe);
        }
        try {
            order = ParserUtil.parseSortOrder(orderString);
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_UNKNOWN_ORDER_KEYWORD), pe);
        }
        return new SortCommand(type, order);
    }

}

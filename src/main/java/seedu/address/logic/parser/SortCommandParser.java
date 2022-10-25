package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SortCommand;
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
            throw new ParseException(SortCommand.MESSAGE_USAGE);
        }
        try {
            SortCommand.TYPE type = ParserUtil.parseSortType(typeString);
            SortCommand.ORDER order = ParserUtil.parseSortOrder(orderString);
            return new SortCommand(type, order);
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE), pe);
        }
    }

}

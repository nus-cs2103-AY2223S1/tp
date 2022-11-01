package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AvailCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.timerange.TimeRange;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AvailCommandParser implements Parser<AvailCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AvailCommand
     * and returns a AvailCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AvailCommand parse(String args) throws ParseException {
        if (!areValuesPresent(args)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AvailCommand.MESSAGE_USAGE));
        }

        TimeRange timeRange = ParserUtil.parseTimeRange(args);
        return new AvailCommand(timeRange);
    }

    /**
     * Returns true if all the values are present
     * {@code ArgumentMultimap}.
     */
    private static boolean areValuesPresent(String args) {
        return args.split(" ").length == 3;
    }
}
